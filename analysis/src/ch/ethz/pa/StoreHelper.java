package ch.ethz.pa;

import soot.Value;
import soot.jimple.IntConstant;
import soot.jimple.internal.JimpleLocal;

public class StoreHelper {

	public static Interval tryGetIntervalForValue(IntervalPerVar currentState, Value v) {
		if (v instanceof IntConstant) {
			IntConstant c = ((IntConstant) v);
			return new Interval(c.value, c.value);
		} else if (v instanceof JimpleLocal) {
			JimpleLocal l = ((JimpleLocal) v);
			return currentState.getIntervalForVar(l.getName());
		}
		return null;
	}

}
