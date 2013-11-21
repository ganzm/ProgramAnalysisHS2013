package ch.ethz.pa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import soot.Value;
import soot.jimple.Stmt;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.intervals.IntervalPerVar;

public class ProblemReport {

	private static final Logger logger = Logger.getLogger(ProblemReport.class.getSimpleName());

	private final Set<Problem> problems = new HashSet<Problem>();

	/**
	 * Assert the given value is in the given range, otherwise report a problem.
	 * 
	 * @param value
	 * @param range
	 * @param current
	 * @param atStatement
	 */
	public void checkInterval(Value value, Interval range, IntervalPerVar current, Stmt atStatement) {
		Interval interval = IntegerExpressionAnalyzer.getIntervalForValue(current, value);
		if (interval == null) {
			throw new RuntimeException("unhandled case: no value for " + value);
		}

		if (!range.covers(interval)) {
			addProblem(atStatement, String.format("sensor index %s out of range", interval));
		}
	}

	/**
	 * Register another problem found during analysis.
	 * 
	 * @param message
	 */
	public void addProblem(Object atStatement, String message) {
		if (!problems.add(new Problem(atStatement, message))) {
			logger.fine("Problem already reported for Statement " + atStatement);
		}
	}

	/**
	 * Returns a list with problems found during analysis.
	 * 
	 * @return
	 */
	public List<String> getProblems() {
		List<String> result = new ArrayList<String>(problems.size());
		for (Problem p : problems) {
			result.add(p.toString());
		}
		return result;
	}

}
