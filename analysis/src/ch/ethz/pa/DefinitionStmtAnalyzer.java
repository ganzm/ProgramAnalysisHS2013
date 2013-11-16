package ch.ethz.pa;

import java.util.logging.Logger;

import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.intervals.IntervalPerVar;

import soot.IntegerType;
import soot.Local;
import soot.RefLikeType;
import soot.RefType;
import soot.SootMethod;
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

public class DefinitionStmtAnalyzer {

	private final Logger logger = Logger.getLogger(DefinitionStmtAnalyzer.class.getSimpleName());

	private final ProblemReport problemReport;

	public DefinitionStmtAnalyzer(ProblemReport problemReport) {
		this.problemReport = problemReport;
	}

	public void analyze(IntervalPerVar current, DefinitionStmt sd, IntervalPerVar fallState) {

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
				logger.warning("ignoring assignments to complex type: " + sd);
			}

			else if (right instanceof IntConstant) {
				IntConstant c = ((IntConstant) right);
				fallState.putIntervalForVar(varName, new Interval(c.value, c.value));
			}

			else if (right instanceof Local) {
				Local l = ((Local) right);
				if (l.getType() instanceof RefLikeType) {
					logger.warning("ignore right side " + l.getType());
				} else {
					fallState.putIntervalForVar(varName, current.getIntervalForVar(l.getName()));
					throw new RuntimeException("hit unexpected type " + l.getType());
				}
			}

			else if (right instanceof BinopExpr) {
				Interval result = IntegerExpression.evalBinop((BinopExpr) right, current);
				fallState.putIntervalForVar(varName, result);
			}

			else if (right instanceof VirtualInvokeExpr) {
				VirtualInvokeExpr expr = (VirtualInvokeExpr) right;
				SootMethod method = expr.getMethodRef().resolve();
				if (method.getName().equals("readSensor")) {
					if (method.getDeclaringClass().getName().equals("AircraftControl")) {
						problemReport.checkInterval(expr.getArg(0), Config.legalSensorInterval, current, sd);
						fallState.putIntervalForVar(varName, new Interval(-999, 999));
					}
				}
			}

			else if (right instanceof UnopExpr) {
				Interval result = IntegerExpression.evalUnop((UnopExpr) right, current);
				fallState.putIntervalForVar(varName, result);
			}

			else if (right instanceof ParameterRef) {

				ParameterRef param = (ParameterRef) right;

				if (param.getType() instanceof IntegerType) {
					fallState.putIntervalForVar(varName, Interval.TOP_INTERVAL);
				}

				else
					throw new RuntimeException("hit unexpected type " + param.getType());

			}

			else {
				throw new RuntimeException("unhandled JimpleLocal " + left + " at " + sd);
			}
			// ...
		}

		else {
			throw new RuntimeException("unhandled lhs " + left);
		}
		// ...
	}

	static void unhandled(String what) {
		System.err.println("Can't handle " + what);
		System.exit(1);
	}

}
