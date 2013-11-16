package ch.ethz.pa.intervals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class IntervalPerVar {

	public static class Pair {
		public final String name;
		public final Interval interval;

		public Pair(String name, Interval interval) {
			this.name = name;
			this.interval = interval;
		}
	}

	public IntervalPerVar() {
		values = new HashMap<String, Interval>();
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (Map.Entry<String, Interval> entry : values.entrySet()) {
			if (b.length() != 0)
				b.append(", ");
			b.append(entry.getKey());
			b.append("=");
			b.append(entry.getValue().toString());
		}
		return b.toString();
	}

	// This does deep copy of values as opposed to shallow copy, but feel free to optimize.
	public void copyFrom(IntervalPerVar other) {
		values.clear();
		for (Map.Entry<String, Interval> entry : other.values.entrySet()) {
			Interval n = entry.getValue();
			values.put(entry.getKey(), n);
		}
	}

	public void mergeWith(IntervalPerVar other) {

		for (Map.Entry<String, Interval> entry : other.values.entrySet()) {

			String otherName = entry.getKey();
			Interval otherInterval = entry.getValue();

			Interval ownInterval = values.get(otherName);
			Interval newInterval = ownInterval != null ? ownInterval.join(otherInterval) : otherInterval;

			values.put(otherName, newInterval);
		}
	}

	public void putIntervalForVar(String var, Interval i) {
		if (i == null) {
			throw new NullPointerException();
		}
		values.put(var, i);
	}

	public Interval getIntervalForVar(String var) {
		Interval interval = values.get(var);
		if (interval == null) {
			throw new NullPointerException(String.format("No interval for [%s]", var));
		}
		return interval;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof IntervalPerVar))
			return false;
		return ((IntervalPerVar) o).values.equals(values);
	}

	/**
	 * Return {@link Pair}s of this store that are different in the {@link last} store.
	 * 
	 * @param last
	 *            reference store, may be null (considered empty)
	 * @return
	 */
	public List<Pair> getDelta(IntervalPerVar last) {

		List<Pair> result;
		if (last == null) {
			result = getEntries();
		} else {
			result = new LinkedList<Pair>();
			for (Entry<String, Interval> entry : values.entrySet()) {
				if (!entry.getValue().equals(last.getIntervalForVar(entry.getKey()))) {
					result.add(new Pair(entry.getKey(), entry.getValue()));
				}
			}
		}
		return result;
	}

	/**
	 * Produce a flat list of all entries of this store.
	 * 
	 * @param result
	 * @return
	 */
	private List<Pair> getEntries() {
		List<Pair> result = new ArrayList<Pair>(values.size());
		for (Entry<String, Interval> entry : values.entrySet()) {
			result.add(new Pair(entry.getKey(), entry.getValue()));
		}
		return result;
	}

	/**
	 * Tell whether any of the stored intervals are empty. This is an indicator for unreachable
	 * code.
	 * 
	 * @return
	 */
	public boolean hasEmptyIntervals() {
		for (Interval interval : values.values()) {
			if (interval == Interval.EMPTY_INTERVAL)
				return true;
		}
		return false;
	}

	private HashMap<String, Interval> values;

}
