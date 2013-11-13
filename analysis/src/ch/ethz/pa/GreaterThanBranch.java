package ch.ethz.pa;

import soot.Value;
import soot.jimple.GtExpr;
import soot.jimple.internal.JimpleLocal;
import ch.ethz.pa.branches.Branch;

public class GreaterThanBranch extends Branch {

	public static Branch createFrom(GtExpr geExpr, IntervalPerVar current) {

		Value r1 = geExpr.getOp1();
		Value r2 = geExpr.getOp2();
	
		Interval i1 = IntegerExpression.tryGetIntervalForValue(current, r1);
		Interval i2 = IntegerExpression.tryGetIntervalForValue(current, r2);

		GreaterThanBranch result = new GreaterThanBranch();
		
		if (r1 instanceof JimpleLocal) {
			String name1 = ((JimpleLocal)r1).getName();
			result.restrictBranchOut(name1, i1.limitToGreater(i2));
			result.restrictFallout(name1, i1.limitToLowerEqual(i2));
		}
		
		if (r2 instanceof JimpleLocal) {
			String name2 = ((JimpleLocal)r2).getName();
			result.restrictFallout(name2, i2.limitToGreater(i1));
			result.restrictBranchOut(name2, i2.limitToLowerEqual(i1));
		}

		return result;
	}

}
