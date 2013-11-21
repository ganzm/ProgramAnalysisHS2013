package ch.ethz.pa;

import java.util.HashSet;
import java.util.Set;

import ch.ethz.pa.intervals.Interval;

/**
 * Represents an AircraftControl instance
 */
public class AirCraftControlRef {

	private final Set<Interval> adjustValueMethodCalls;
	private final Set<Interval> readSensorMethodCalls;

	public AirCraftControlRef() {
		this(new HashSet<Interval>(), new HashSet<Interval>());
	}

	@Override
	public String toString() {
		return "Adjusted" + adjustValueMethodCalls + " Reads" + readSensorMethodCalls;
	}

	public AirCraftControlRef(Set<Interval> adjustValueMethodCalls, Set<Interval> readSensorMethodCalls) {
		this.adjustValueMethodCalls = adjustValueMethodCalls;
		this.readSensorMethodCalls = readSensorMethodCalls;
	}

	/**
	 * @return false if method was already called with that argument
	 */
	public boolean adjustValueMethodCalled(Interval interval) {
		adjustValueMethodCalls.add(interval);
		return null == check(adjustValueMethodCalls);
	}

	public boolean readSensorMethodCalled(Interval interval) {
		readSensorMethodCalls.add(interval);
		return null == check(readSensorMethodCalls);
	}

	public String check(Set<Interval> intervalSet) {

		for (Interval interval : intervalSet) {
			int numbers = interval.getNumberOfNumbersInInterval();

			int numberOfCovers = 0;
			for (Interval subInterval : intervalSet) {
				if (subInterval.covers(interval)) {
					numberOfCovers++;
				}
			}

			if (numbers < numberOfCovers) {
				return "There is a Problem with Interval " + interval + " number of Intervals which cover this is " + numberOfCovers;
			}
		}
		return null;
	}

	public AirCraftControlRef join(AirCraftControlRef other) {
		Set<Interval> newAdjustSet = new HashSet<Interval>();
		newAdjustSet.addAll(this.adjustValueMethodCalls);
		newAdjustSet.addAll(other.adjustValueMethodCalls);

		Set<Interval> newReadSet = new HashSet<Interval>();
		newReadSet.addAll(this.readSensorMethodCalls);
		newReadSet.addAll(other.readSensorMethodCalls);

		return new AirCraftControlRef(newAdjustSet, newReadSet);
	}

}
