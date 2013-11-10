package ch.ethz.pa;

import soot.Value;
import soot.jimple.AddExpr;
import soot.jimple.BinopExpr;
import soot.jimple.DivExpr;
import soot.jimple.IntConstant;
import soot.jimple.MulExpr;
import soot.jimple.SubExpr;
import soot.jimple.internal.JimpleLocal;

/**
 * Evaluates small step integer expressions.
 */
public class IntegerExpression {

	private static Interval tryGetIntervalForValue(IntervalPerVar currentState, Value v) {
		if (v instanceof IntConstant) {
			IntConstant c = ((IntConstant) v);
			return new Interval(c.value, c.value);
		} else if (v instanceof JimpleLocal) {
			JimpleLocal l = ((JimpleLocal) v);
			return currentState.getIntervalForVar(l.getName());
		}
		return null;
	}

	public static Interval evalIntervalForBinop(BinopExpr binop, IntervalPerVar current) {
		
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

}
