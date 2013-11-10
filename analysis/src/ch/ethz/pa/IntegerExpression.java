package ch.ethz.pa;

import soot.Value;
import soot.jimple.AddExpr;
import soot.jimple.BinopExpr;
import soot.jimple.DivExpr;
import soot.jimple.MulExpr;
import soot.jimple.NegExpr;
import soot.jimple.SubExpr;
import soot.jimple.UnopExpr;

/**
 * Evaluates small step integer expressions.
 */
public class IntegerExpression {

	public static Interval evalBinop(BinopExpr binop, IntervalPerVar current) {
		
		Value r1 = binop.getOp1();
		Value r2 = binop.getOp2();
	
		Interval i1 = StoreHelper.tryGetIntervalForValue(current, r1);
		Interval i2 = StoreHelper.tryGetIntervalForValue(current, r2);
	
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

	static public Interval evalUnop(UnopExpr unop, IntervalPerVar current) {
		Interval result;
		Value r1 = unop.getOp();
		Interval i1 = StoreHelper.tryGetIntervalForValue(current, r1);
	
		if (i1 == null) 
			throw new NullPointerException();
		
		if (unop instanceof NegExpr) {
			result = i1.negate();
		}
		else throw new RuntimeException("unsupported expression "+unop);
		return result;
	}

}
