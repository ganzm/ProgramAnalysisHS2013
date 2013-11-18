package ch.ethz.pa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import soot.PrimType;
import soot.Value;
import soot.jimple.IntConstant;
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

		if (value instanceof IntConstant) {
			IntConstant c = ((IntConstant) value);
			if (!range.covers(c.value)) {
				addProblem(atStatement, "index out of range");
			}
		}

		else if (value.getType() instanceof PrimType) {

			Interval interval = IntegerExpressionAnalyzer.tryGetIntervalForValue(current, value);
			if (interval == null) {
				throw new RuntimeException("unhandled case: no value for " + value);
			}

			if (!range.covers(interval)) {
				addProblem(atStatement, String.format("sensor index %s out of range", interval));
			}
		}

		else {
			// TODO probably there are other cases as well
			throw new RuntimeException("hit unexpected type " + value.getType());
		}
	}

	/**
	 * Register another problem found during analysis.
	 * 
	 * @param line
	 */
	public void addProblem(Object atStatement, String line) {
		if (!problems.add(new Problem(atStatement, line))) {
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
