package ch.ethz.pa;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AirCraftRefPerVar {

	private final Logger logger = Logger.getLogger(AirCraftRefPerVar.class.getSimpleName());

	private final Map<String, AirCraftControlRef> aircraftcontrolRefs = new HashMap<String, AirCraftControlRef>();

	@Override
	public String toString() {
		return aircraftcontrolRefs.toString();
	}

	public void newVariable(String variableName) {
		logger.fine("New AirCraftControl reference " + variableName);
		aircraftcontrolRefs.put(variableName, new AirCraftControlRef());
	}

	public AirCraftControlRef tryGetExistingRef(String name) {
		return aircraftcontrolRefs.get(name);
	}

	public AirCraftControlRef getExistingRef(String name) {
		AirCraftControlRef result = aircraftcontrolRefs.get(name);
		if (result == null) {
			throw new RuntimeException("Unknown AirCraftControlRef " + name);
		}
		return result;
	}

	public void copyFrom(AirCraftRefPerVar refPerVar) {
		aircraftcontrolRefs.clear();
		for (Map.Entry<String, AirCraftControlRef> entry : refPerVar.aircraftcontrolRefs.entrySet()) {
			aircraftcontrolRefs.put(entry.getKey(), entry.getValue());
		}
	}

	public void mergeWith(AirCraftRefPerVar other) {
		for (Map.Entry<String, AirCraftControlRef> entry : other.aircraftcontrolRefs.entrySet()) {

			String otherName = entry.getKey();
			AirCraftControlRef otherRef = entry.getValue();

			AirCraftControlRef ownRef = aircraftcontrolRefs.get(otherName);
			AirCraftControlRef newRef = ownRef != null ? ownRef.join(otherRef) : otherRef;

			aircraftcontrolRefs.put(otherName, newRef);
		}
	}

	/**
	 * call this when the following happens:
	 * 
	 * variableBeingAssigned = variableBeingAliased
	 * 
	 * 
	 * now both References share the same object
	 * 
	 * @param variableBeingAssigned
	 * @param variableBeingAliased
	 */
	public void doAliasing(String variableBeingAssigned, String variableBeingAliased) {
		logger.fine("Variable aliasing: " + variableBeingAssigned + " = " + variableBeingAliased);
		AirCraftControlRef tmp = getExistingRef(variableBeingAliased);
		aircraftcontrolRefs.put(variableBeingAssigned, tmp);
	}
}
