package ch.ethz.pa.branches;

import ch.ethz.pa.IntegerExpression;
import ch.ethz.pa.Interval;
import ch.ethz.pa.IntervalPerVar;
import ch.ethz.pa.TriState;
import soot.Value;
import soot.jimple.GeExpr;
import soot.jimple.internal.JimpleLocal;

public class GreaterEqualBranch extends Branch {

	public GreaterEqualBranch(TriState conditionResult) {
		super(conditionResult);
	}

	public static Branch createFrom(GeExpr geExpr, IntervalPerVar current) {
		
		Value r1 = geExpr.getOp1();
		Value r2 = geExpr.getOp2();
	
		Interval i1 = IntegerExpression.tryGetIntervalForValue(current, r1);
		Interval i2 = IntegerExpression.tryGetIntervalForValue(current, r2);
		
		
		TriState conditionResult = Interval.greaterEqual(i1, i2);
		
		if (r1 instanceof JimpleLocal) {

			Interval i1BranchOut;
			Interval i1FallOut;

			String name1 = ((JimpleLocal)r1).getName();
			i1BranchOut = i1.limitToGreaterEqual(i2);
			i1FallOut = i1.limitToLower(i2);
			
		}
		
		if (r2 instanceof JimpleLocal) {
			
			Interval i2BranchOut;
			Interval i2FallOut;

			String name2 = ((JimpleLocal)r2).getName();
			i2BranchOut = i2.limitToLowerEqual(i1);
			i2FallOut = i2.limitToGreater(i1);
			
		}

		return new GreaterEqualBranch(conditionResult);
	}

	@Override
	public void restrictBranchState(IntervalPerVar branchState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restrictFallstate(IntervalPerVar fallState) {
		// TODO Auto-generated method stub
		
	}

}
