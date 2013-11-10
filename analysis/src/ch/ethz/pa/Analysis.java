package ch.ethz.pa;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import soot.PrimType;
import soot.RefLikeType;
import soot.RefType;
import soot.SootMethod;
import soot.Unit;
import soot.Value;
import soot.jimple.AddExpr;
import soot.jimple.BinopExpr;
import soot.jimple.DefinitionStmt;
import soot.jimple.DivExpr;
import soot.jimple.IfStmt;
import soot.jimple.IntConstant;
import soot.jimple.InvokeExpr;
import soot.jimple.MulExpr;
import soot.jimple.NegExpr;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.StaticFieldRef;
import soot.jimple.Stmt;
import soot.jimple.SubExpr;
import soot.jimple.UnopExpr;
import soot.jimple.internal.JArrayRef;
import soot.jimple.internal.JInstanceFieldRef;
import soot.jimple.internal.JInvokeStmt;
import soot.jimple.internal.JVirtualInvokeExpr;
import soot.jimple.internal.JimpleLocal;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardBranchedFlowAnalysis;

/**
 * Implement your numerical analysis here.
 */
public class Analysis extends ForwardBranchedFlowAnalysis<IntervalPerVar> {

	private final Logger logger = Logger.getLogger(Analysis.class.getSimpleName());
	
	private final List<String> problems = new LinkedList<String>();
	
	private final Interval legalSensorInterval = new Interval(0, 15);
	private final Interval legalValueInterval = new Interval(-999,999);
	
	public Analysis(UnitGraph g) {
		super(g);
		logger.info("UnitGraph: " + g.toString());
	}

	void run() {
		doAnalysis();
	}

	static void unhandled(String what) {
		System.err.println("Can't handle " + what);
		System.exit(1);
	}

	@Override
	protected void flowThrough(IntervalPerVar current, Unit op, List<IntervalPerVar> fallOut, List<IntervalPerVar> branchOuts) {
		// TODO: This can be optimized.
		logger.info("Operation: " + op + "   - " + op.getClass().getName() + "\n      state: " + current);

		Stmt s = (Stmt) op;
		IntervalPerVar fallState = new IntervalPerVar();
		fallState.copyFrom(current);
		IntervalPerVar branchState = new IntervalPerVar();
		branchState.copyFrom(current);

		if (s instanceof DefinitionStmt) {
			flowThroughDefinitionStmt(current, (DefinitionStmt) s, fallState);
		} 
		
		else if (s instanceof JInvokeStmt) {
			flowThroughJInvokeStmt(current, (JInvokeStmt) s);
		}
		
		else if (s instanceof IfStmt) {
			
			IfStmt is = (IfStmt) s;
			
			Value condition = is.getCondition();
			
			throw new RuntimeException("unhandled if statement: "+op);
		}
		
		else if (s instanceof ReturnVoidStmt) {
			logger.warning("ignoring return void statement: "+op);
		}
		
		else {
			throw new RuntimeException("unhandled statement: "+op);
		}

		// TODO: Maybe avoid copying objects too much. Feel free to optimize.
		for (IntervalPerVar fnext : fallOut) {
			if (fallState != null) {
				fnext.copyFrom(fallState);
			}
		}
		for (IntervalPerVar fnext : branchOuts) {
			if (branchState != null) {
				fnext.copyFrom(branchState);
			}
		}
	}

	private void flowThroughJInvokeStmt(IntervalPerVar current, JInvokeStmt s) {
		// A method is called. e.g. AircraftControl.adjustValue

		// You need to check the parameters here.
		InvokeExpr expr = s.getInvokeExpr();
		if (expr.getMethod().getName().equals("adjustValue")) {
			// Check that is the method from the AircraftControl class.
			if (expr.getMethod().getDeclaringClass().getName().equals("AircraftControl"))
			{
				// TODO: Check that the values are in the allowed range (we do this while computing fixpoint).
				checkInterval(expr.getArg(0), legalSensorInterval, current);
				checkInterval(expr.getArg(1), legalValueInterval, current);
			}
		}
	}

	private void flowThroughDefinitionStmt(IntervalPerVar current,
			DefinitionStmt sd, IntervalPerVar fallState) {

		Value left = sd.getLeftOp();
		Value right = sd.getRightOp();
		logger.info(left.getClass().getName() + " " + right.getClass().getName());

		// You do not need to handle these cases:
		if ((!(left instanceof StaticFieldRef)) && (!(left instanceof JimpleLocal)) && (!(left instanceof JArrayRef))
				&& (!(left instanceof JInstanceFieldRef)))
			unhandled("1: Assignment to non-variables is not handled.");
		
		else if ((left instanceof JArrayRef) && (!((((JArrayRef) left).getBase()) instanceof JimpleLocal)))
			unhandled("2: Assignment to a non-local array variable is not handled.");
		
		// TODO: Handle other cases. For example:

		else if (left instanceof JimpleLocal) {
			
			JimpleLocal jimpleLocalLeft = (JimpleLocal) left;
			String varName = jimpleLocalLeft.getName();
			

			if (jimpleLocalLeft.getType() instanceof RefType) {
				logger.warning("ignoring assignments to complex type: "+sd);
			}
			
			else if (right instanceof IntConstant) {
				IntConstant c = ((IntConstant) right);
				fallState.putIntervalForVar(varName, new Interval(c.value, c.value));
			} 
			
			else if (right instanceof JimpleLocal) {
				JimpleLocal l = ((JimpleLocal) right);
				if (l.getType() instanceof RefLikeType) {
					logger.warning("ignore right side "+l.getType());
				}
				else {
					fallState.putIntervalForVar(varName, current.getIntervalForVar(l.getName()));
					throw new RuntimeException("hit unexpected type "+l.getType());
				}
			} 
			
			else if (right instanceof BinopExpr) {

				Interval result = evalIntervalForBinop((BinopExpr) right, current);

				fallState.putIntervalForVar(varName, result);
			}
			
			else if (right instanceof JVirtualInvokeExpr) {
				JVirtualInvokeExpr expr = (JVirtualInvokeExpr)right;
				SootMethod method = expr.getMethodRef().resolve();
				if (method.getName().equals("readSensor")) {
					if (method.getDeclaringClass().getName().equals("AircraftControl")) {
						checkInterval(expr.getArg(0), legalSensorInterval, current);
						fallState.putIntervalForVar(varName, new Interval(-999, 999));
					}
				}
			}

			else if (right instanceof UnopExpr) {
				Value r1 = ((UnopExpr) right).getOp();
				Interval i1 = tryGetIntervalForValue(current, r1);

				if (i1 == null) 
					throw new NullPointerException();
				
				else {
					
					if (right instanceof NegExpr) {
						fallState.putIntervalForVar(varName, i1.negate());
					}
					
					else throw new RuntimeException("unsupported operation "+right+" at "+sd);
				}

			}

			else {
				throw new RuntimeException("unhandled JimpleLocal "+left+ " at "+sd);
			}
			// ...
		}
		
		else {
			throw new RuntimeException("unhandled lhs "+left);
		}
		// ...
	}

	private Interval evalIntervalForBinop(BinopExpr binop, IntervalPerVar current) {
		Value r1 = binop.getOp1();
		Value r2 = binop.getOp2();

		Interval i1 = tryGetIntervalForValue(current, r1);
		Interval i2 = tryGetIntervalForValue(current, r2);

		Interval result;

		if (i1 == null) 
			throw new NullPointerException();
		
		else if (i2 == null) 
			throw new NullPointerException();
		
		else {
			// Implement transformers.
			
			if (binop instanceof AddExpr) {
				result = Interval.plus(i1, i2);
			}
			
			else if (binop instanceof SubExpr) {
				result = Interval.subtract(i1, i2);
			} 
			
			else if (binop instanceof MulExpr) {
				result = Interval.multiply(i1, i2);
			} 
			
			else if (binop instanceof DivExpr) {
				result = Interval.divide(i1, i2);
			} 
			
			else throw new RuntimeException("unsupported expression "+binop);
			
		}
		return result;
	}

	/**
	 * Assert the given value is in the given range, otherwise
	 * report a problem.
	 * @param value
	 * @param range
	 * @param current 
	 */
	private void checkInterval(Value value, Interval range, IntervalPerVar current) {
		
		if (value instanceof IntConstant) {
			IntConstant c = ((IntConstant) value);
			if(!range.covers(c.value)) {
				// TODO this is not very expressive, we can improve that
				problems.add("sensor index out of range");
			}
		}
		
		else if (value.getType() instanceof PrimType){
			
			Interval interval = tryGetIntervalForValue(current, value);
			if (interval == null) {
				throw new RuntimeException("unhandled case: no value for "+value);
			}
			
			if (!range.covers(interval)) {
				problems.add(String.format("sensor index [%s] out of range", interval));				
			}
		}
		
		else {
			// TODO probably there are other cases as well
			throw new RuntimeException("hit unexpected type "+value.getType());
		}
	}

	Interval tryGetIntervalForValue(IntervalPerVar currentState, Value v) {
		if (v instanceof IntConstant) {
			IntConstant c = ((IntConstant) v);
			return new Interval(c.value, c.value);
		} else if (v instanceof JimpleLocal) {
			JimpleLocal l = ((JimpleLocal) v);
			return currentState.getIntervalForVar(l.getName());
		}
		return null;
	}

	@Override
	protected void copy(IntervalPerVar source, IntervalPerVar dest) {
		dest.copyFrom(source);
	}

	@Override
	protected IntervalPerVar entryInitialFlow() {
		// TODO: How do you model the entry point?
		return new IntervalPerVar();
	}

	@Override
	protected void merge(IntervalPerVar src1, IntervalPerVar src2, IntervalPerVar trg) {
		// TODO: join, widening, etc goes here.
		trg.copyFrom(src1);

		logger.info(String.format("Merge:\n    %s\n    %s\n    ============\n    %s\n", src1.toString(), src2.toString(), trg.toString()));
	}

	@Override
	protected IntervalPerVar newInitialFlow() {
		return new IntervalPerVar();
	}

	/**
	 * Register another problem found during analysis.
	 * @param line
	 */
	protected void addProblem(String line) {
		problems.add(line);
	}

	/**
	 * Returns a list with problems found during analysis.
	 * @return
	 */
	public List<String> getProblems() {
		return problems;
	}
}
