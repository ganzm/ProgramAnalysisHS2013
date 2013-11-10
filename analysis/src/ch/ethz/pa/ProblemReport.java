package ch.ethz.pa;

import java.util.LinkedList;
import java.util.List;

import soot.PrimType;
import soot.Value;
import soot.jimple.IntConstant;

public class ProblemReport {

	private final List<String> problems = new LinkedList<String>();
	
	/**
	 * Assert the given value is in the given range, otherwise
	 * report a problem.
	 * @param value
	 * @param range
	 * @param current 
	 */
	public void checkInterval(Value value, Interval range, IntervalPerVar current) {
		
		if (value instanceof IntConstant) {
			IntConstant c = ((IntConstant) value);
			if(!range.covers(c.value)) {
				// TODO this is not very expressive, we can improve that
				problems.add("sensor index out of range");
			}
		}
		
		else if (value.getType() instanceof PrimType){
			
			Interval interval = IntegerExpression.tryGetIntervalForValue(current, value);
			if (interval == null) {
				throw new RuntimeException("unhandled case: no value for "+value);
			}
			
			if (!range.covers(interval)) {
				problems.add(String.format("sensor index [%s] out of range", interval));				
			}
		}
		
		else {
			// TODO probably there are other cases as well
			throw new RuntimeException("hit unexpected type "+value.getType());
		}
	}
	
	/**
	 * Register another problem found during analysis.
	 * @param line
	 */
	public void addProblem(String line) {
		problems.add(line);
	}

	/**
	 * Returns a list with problems found during analysis.
	 * @return
	 */
	public List<String> getProblems() {
		return problems;
	}


}
