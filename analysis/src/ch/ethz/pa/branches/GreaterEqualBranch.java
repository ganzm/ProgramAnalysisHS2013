package ch.ethz.pa.branches;

import soot.Value;
import soot.jimple.GeExpr;
import soot.jimple.internal.JimpleLocal;
import ch.ethz.pa.IntegerExpression;
import ch.ethz.pa.Interval;
import ch.ethz.pa.IntervalPerVar;

public class GreaterEqualBranch extends Branch {
	
	public static Branch createFrom(GeExpr geExpr, IntervalPerVar current) {
		
		Value r1 = geExpr.getOp1();
		Value r2 = geExpr.getOp2();
	
		Interval i1 = IntegerExpression.tryGetIntervalForValue(current, r1);
		Interval i2 = IntegerExpression.tryGetIntervalForValue(current, r2);

		GreaterEqualBranch result = new GreaterEqualBranch();
		
		if (r1 instanceof JimpleLocal) {
			String name1 = ((JimpleLocal)r1).getName();
			result.restrictBranchOut(name1, i1.limitToGreaterEqual(i2));
			result.restrictFallout(name1, i1.limitToLower(i2));
		}
		
		if (r2 instanceof JimpleLocal) {
			String name2 = ((JimpleLocal)r2).getName();
			result.restrictFallout(name2, i2.limitToGreater(i1));
			result.restrictBranchOut(name2, i2.limitToLowerEqual(i1));
		}

		return result;
	}

}
