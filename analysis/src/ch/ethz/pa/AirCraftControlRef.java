package ch.ethz.pa;

import java.util.HashSet;
import java.util.Set;

import soot.Value;

/**
 * Represents an AircraftControl instance
 */
public class AirCraftControlRef {

	private final Set<Value> adjustValueMethodCalls;
	private final Set<Value> readSensorMethodCalls;

	public AirCraftControlRef() {
		this(new HashSet<Value>(), new HashSet<Value>());
	}

	public AirCraftControlRef(Set<Value> adjustValueMethodCalls, Set<Value> readSensorMethodCalls) {
		this.adjustValueMethodCalls = adjustValueMethodCalls;
		this.readSensorMethodCalls = readSensorMethodCalls;
	}

	/**
	 * @return false if method was already called with that argument
	 */
	public boolean adjustValueMethodCalled(Value arg) {
		return adjustValueMethodCalls.add(arg);
	}

	public boolean readSensorMethodCalled(Value arg) {
		return readSensorMethodCalls.add(arg);
	}

	public AirCraftControlRef join(AirCraftControlRef other) {
		Set<Value> newAdjustSet = new HashSet<Value>();
		newAdjustSet.addAll(this.adjustValueMethodCalls);
		newAdjustSet.addAll(other.adjustValueMethodCalls);

		Set<Value> newReadSet = new HashSet<Value>();
		newReadSet.addAll(this.readSensorMethodCalls);
		newReadSet.addAll(other.readSensorMethodCalls);

		return new AirCraftControlRef(newAdjustSet, newReadSet);
	}

}
