package ch.ethz.pa;

import java.util.logging.Logger;

import soot.Local;
import soot.Value;
import soot.jimple.AddExpr;
import soot.jimple.AndExpr;
import soot.jimple.BinopExpr;
import soot.jimple.DivExpr;
import soot.jimple.IntConstant;
import soot.jimple.MulExpr;
import soot.jimple.NegExpr;
import soot.jimple.OrExpr;
import soot.jimple.RemExpr;
import soot.jimple.ShlExpr;
import soot.jimple.ShrExpr;
import soot.jimple.SubExpr;
import soot.jimple.UnopExpr;
import soot.jimple.UshrExpr;
import soot.jimple.XorExpr;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.intervals.IntervalPerVar;

/**
 * Evaluates small step integer expressions.
 */
public class IntegerExpressionAnalyzer {
	private final Logger logger = Logger.getLogger(IntegerExpressionAnalyzer.class.getSimpleName());

	private final ProblemReport problemReport;

	public IntegerExpressionAnalyzer(ProblemReport problemReport) {
		this.problemReport = problemReport;
	}

	public Interval evalBinop(BinopExpr binop, IntervalPerVar current) {

		Value r1 = binop.getOp1();
		Value r2 = binop.getOp2();

		Interval i1 = IntegerExpressionAnalyzer.tryGetIntervalForValue(current, r1);
		Interval i2 = IntegerExpressionAnalyzer.tryGetIntervalForValue(current, r2);

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
				boolean[] divisionByZero = new boolean[] { false };
				result = Interval.divide(i1, i2, divisionByZero);
				if (divisionByZero[0]) {
					problemReport.addProblem(binop, "division by zero");
				}
			}

			else if (binop instanceof RemExpr) {
				boolean[] divisionByZero = new boolean[] { false };
				result = Interval.remainder(i1, i2, divisionByZero);
				if (divisionByZero[0]) {
					problemReport.addProblem(binop, "division by zero");
				}
			} else if (binop instanceof XorExpr) {
				result = Interval.xor(i1, i2);
			} else if (binop instanceof OrExpr) {
				result = Interval.or(i1, i2);
			} else if (binop instanceof AndExpr) {
				result = Interval.and(i1, i2);
			} else if (binop instanceof ShlExpr) {
				result = Interval.shiftLeft(i1, i2);
			} else if (binop instanceof ShrExpr) {
				result = Interval.shiftRight(i1, i2);
			} else if (binop instanceof UshrExpr) {
				result = Interval.shiftUnsignedRight(i1, i2);
			} else {
				// go to top if there is an unknown binary operation
				logger.severe("Unexpected binary operation " + binop);
				result = Interval.TOP_INTERVAL;
			}
		}
		return result;
	}

	static public Interval evalUnop(UnopExpr unop, IntervalPerVar current) {
		Interval result;
		Value r1 = unop.getOp();
		Interval i1 = IntegerExpressionAnalyzer.tryGetIntervalForValue(current, r1);

		if (i1 == null)
			throw new NullPointerException();

		if (unop instanceof NegExpr) {
			result = i1.negate();
		} else
			throw new RuntimeException("unsupported expression " + unop);
		return result;
	}

	public static Interval tryGetIntervalForValue(IntervalPerVar currentState, Value v) {
		if (v instanceof IntConstant) {
			IntConstant c = ((IntConstant) v);
			return new Interval(c.value, c.value);
		} else if (v instanceof Local) {
			Local l = ((Local) v);
			return currentState.getIntervalForVar(l.getName());
		}
		return null;
	}

	public static Interval getIntervalForValue(IntervalPerVar intervalPerVar, Value value) {
		Interval result = tryGetIntervalForValue(intervalPerVar, value);

		if (result == null) {
			throw new RuntimeException("Interval not found for Value " + value);
		}

		return result;
	}

}
