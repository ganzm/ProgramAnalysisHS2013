package ch.ethz.pa.pairs;

import soot.Value;
import soot.jimple.internal.JimpleLocal;
import ch.ethz.pa.IntegerExpressionAnalyzer;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.intervals.IntervalPerVar;

/**
 * @see Pair
 */
public class PairLowerEqual extends Pair {

	public PairLowerEqual(Value a1, Value a2, IntervalPerVar current) {

		super();

		Interval i1 = IntegerExpressionAnalyzer.tryGetIntervalForValue(current, a1);
		Interval i2 = IntegerExpressionAnalyzer.tryGetIntervalForValue(current, a2);

		if (a1 instanceof JimpleLocal) {
			String name1 = ((JimpleLocal) a1).getName();
			leftArgumentRestriction = new Restriction(name1, i1.limitToLowerEqual(i2));
		}

		if (a2 instanceof JimpleLocal) {
			String name2 = ((JimpleLocal) a2).getName();
			rightArgumentRestriction = new Restriction(name2, i2.limitToGreater(i1));
		}
	}
}
