package ch.ethz.pa.pairs;

import soot.Value;
import soot.jimple.internal.JimpleLocal;
import ch.ethz.pa.IntegerExpression;
import ch.ethz.pa.Interval;
import ch.ethz.pa.IntervalPerVar;

/**
 * @see Pair
 */
public class PairGreaterThan extends Pair {

	public PairGreaterThan(Value a1, Value a2, IntervalPerVar current) {

		super();

		Interval i1 = IntegerExpression.tryGetIntervalForValue(current, a1);
		Interval i2 = IntegerExpression.tryGetIntervalForValue(current, a2);

		if (a1 instanceof JimpleLocal) {
			String name1 = ((JimpleLocal) a1).getName();
			leftArgumentBranchOut = new Restriction(name1, i1.limitToGreater(i2));
			leftArgumentFallOut = new Restriction(name1, i1.limitToLowerEqual(i2));
		}

		if (a2 instanceof JimpleLocal) {
			String name2 = ((JimpleLocal) a2).getName();
			rightArgumentBranchOut = new Restriction(name2, i2.limitToLowerEqual(i1));
			rightArgumentFallOut = new Restriction(name2, i2.limitToGreater(i1));
		}
	}
}
