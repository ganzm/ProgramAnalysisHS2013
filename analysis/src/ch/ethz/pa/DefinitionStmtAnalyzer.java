package ch.ethz.pa;

import java.util.logging.Logger;

import soot.ArrayType;
import soot.BooleanType;
import soot.IntegerType;
import soot.Local;
import soot.RefLikeType;
import soot.RefType;
import soot.SootMethod;
import soot.Type;
import soot.Value;
import soot.jimple.ArrayRef;
import soot.jimple.BinopExpr;
import soot.jimple.DefinitionStmt;
import soot.jimple.InstanceFieldRef;
import soot.jimple.IntConstant;
import soot.jimple.ParameterRef;
import soot.jimple.StaticFieldRef;
import soot.jimple.UnopExpr;
import soot.jimple.VirtualInvokeExpr;
import soot.jimple.internal.JNewArrayExpr;
import soot.jimple.internal.JimpleLocal;
import soot.jimple.internal.JimpleLocalBox;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.intervals.IntervalPerVar;

public class DefinitionStmtAnalyzer {

	private final Logger logger = Logger.getLogger(DefinitionStmtAnalyzer.class.getSimpleName());

	private final ProblemReport problemReport;
	private final IntegerExpressionAnalyzer integerExpressionAnalyzer;

	public DefinitionStmtAnalyzer(ProblemReport problemReport) {
		this.problemReport = problemReport;
		this.integerExpressionAnalyzer = new IntegerExpressionAnalyzer(problemReport);
	}

	public void analyze(StateContainer current, DefinitionStmt sd, StateContainer fallState) {

		IntervalPerVar currentInterval = current.getIntervalPerVar();
		IntervalPerVar fallStateInterval = fallState.getIntervalPerVar();

		Value left = sd.getLeftOp();
		Value right = sd.getRightOp();
		logger.info(left.getClass().getName() + " " + right.getClass().getName());

		// You do not need to handle these cases:
		if ((!(left instanceof StaticFieldRef)) && (!(left instanceof Local)) && (!(left instanceof ArrayRef)) && (!(left instanceof InstanceFieldRef)))
			unhandled("1: Assignment to non-variables is not handled.");

		else if ((left instanceof ArrayRef) && (!((((ArrayRef) left).getBase()) instanceof Local)))
			unhandled("2: Assignment to a non-local array variable is not handled.");

		else if (left instanceof Local) {

			Local jimpleLocalLeft = (Local) left;
			String varName = jimpleLocalLeft.getName();

			if (jimpleLocalLeft.getType() instanceof RefType) {

				String variableName = jimpleLocalLeft.getName();
				if (right.getType() instanceof RefType) {
					RefType rightRefType = ((RefType) right.getType());
					String className = rightRefType.getClassName();

					if ("AircraftControl".equals(className)) {

						boolean aliasedVarDetected = false;

						if (right instanceof JimpleLocal) {
							// check for local aliasing
							String rightName = ((JimpleLocal) right).getName();
							AirCraftControlRef existing = current.getRefPerVar().tryGetExistingRef(rightName);
							if (existing != null) {
								fallState.getRefPerVar().doAliasing(variableName, rightName);
								aliasedVarDetected = true;
							}
						}
						if (!aliasedVarDetected) {
							// no aliasing detected
							// unknown AirCraftControl variable is assigned
							fallState.getRefPerVar().newVariable(variableName);
						}
					}
				}
			}

			else if (right instanceof IntConstant) {
				IntConstant c = ((IntConstant) right);
				fallStateInterval.putIntervalForVar(varName, new Interval(c.value, c.value));
			}

			else if (right instanceof Local) {
				Local l = ((Local) right);
				if (l.getType() instanceof RefLikeType) {
					logger.warning("ignore right side " + l.getType());
				} else {
					fallStateInterval.putIntervalForVar(varName, currentInterval.getIntervalForVar(l.getName()));
				}
			}

			else if (right instanceof BinopExpr) {
				Interval result = integerExpressionAnalyzer.evalBinop((BinopExpr) right, currentInterval);
				fallStateInterval.putIntervalForVar(varName, result);
			}

			else if (right instanceof VirtualInvokeExpr) {
				VirtualInvokeExpr expr = (VirtualInvokeExpr) right;
				SootMethod method = expr.getMethodRef().resolve();
				if (method.getDeclaringClass().getName().equals("AircraftControl")) {

					// try to get name of the member variable which does the invoke statement
					JimpleLocalBox localBox = (JimpleLocalBox) expr.getUseBoxes().get(0);
					Value receiverValue = localBox.getValue();
					JimpleLocal receiverLocal = (JimpleLocal) receiverValue;

					AirCraftRefPerVar refPerVar = current.getRefPerVar();
					AirCraftControlRef acRef = refPerVar.getExistingRef(receiverLocal.getName());

					if (method.getName().equals("readSensor")) {
						Value sensorArg = expr.getArg(0);

						problemReport.checkInterval(sensorArg, Config.legalSensorInterval, currentInterval, sd);
						fallStateInterval.putIntervalForVar(varName, new Interval(-999, 999));

						Interval readIntArgument = IntegerExpressionAnalyzer.getIntervalForValue(currentInterval, sensorArg);

						if (!acRef.readSensorMethodCalled(readIntArgument)) {
							problemReport.addProblem(sd, "readSensor already called with " + readIntArgument);
						}

						problemReport.checkInterval(expr.getArg(0), Config.legalSensorInterval, currentInterval, sd);

					} else if (method.getName().equals("adjustSensor")) {
						Value sensorArg = expr.getArg(0);
						Interval readIntArgument = IntegerExpressionAnalyzer.getIntervalForValue(currentInterval, sensorArg);
						if (!acRef.adjustValueMethodCalled(readIntArgument)) {
							problemReport.addProblem(sd, "adjustSensor already called with " + readIntArgument);
						}

						problemReport.checkInterval(expr.getArg(0), Config.legalSensorInterval, currentInterval, sd);
						problemReport.checkInterval(expr.getArg(1), Config.legalValueInterval, currentInterval, sd);
					}
				}
			}

			else if (right instanceof UnopExpr) {
				Interval result = IntegerExpressionAnalyzer.evalUnop((UnopExpr) right, currentInterval);
				fallStateInterval.putIntervalForVar(varName, result);
			}

			else if (right instanceof ParameterRef) {

				ParameterRef param = (ParameterRef) right;

				if (param.getType() instanceof BooleanType) {
					// handle BooleanType before IntegerType because

					Analysis.uncoveredBranch("boolean type assignment not handled");

				} else if (param.getType() instanceof IntegerType) {
					fallStateInterval.putIntervalForVar(varName, Interval.TOP_INTERVAL);
				} else if (param.getType() instanceof ArrayType) {

					ArrayType arrayParam = (ArrayType) param.getType();

					Type elementType = arrayParam.getElementType();

					if ("AircraftControl".equals(arrayParam.getArrayElementType().toString())) {
						// TODO
						Analysis.uncoveredBranch("TODO");

					} else {
						logger.finest("Got Array of " + elementType);
					}
				}

				else {
					Analysis.uncoveredBranch("hit unexpected type " + param.getType());
				}

			} else if (right instanceof JNewArrayExpr) {
				JNewArrayExpr rightArrayExpr = (JNewArrayExpr) right;

				Type baseType = rightArrayExpr.getBaseType();
				if ("AircraftControl".equals(baseType.toString())) {
					Value sizeValue = rightArrayExpr.getSize();
					Interval sizeInterval = IntegerExpressionAnalyzer.getIntervalForValue(currentInterval, sizeValue);
					Analysis.uncoveredBranch("Arrays of AircraftControl not handled");
				} else {
					logger.fine("Ignore Arrays of " + baseType);
				}
			} else {
				Analysis.uncoveredBranch("unhandled JimpleLocal " + left + " at " + sd);
			}
			// ...
		}

		else {
			Analysis.uncoveredBranch("unhandled lhs " + left);
		}
		// ...
	}

	static void unhandled(String what) {
		System.err.println("Can't handle " + what);
		System.exit(1);
	}

}
