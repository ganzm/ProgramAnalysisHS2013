package ch.ethz.pa;

public class Problem {

	private final Object atStatement;

	private final String line;

	public Problem(Object atStatement, String line) {
		this.atStatement = atStatement;
		this.line = line;
	}

	@Override
	public String toString() {
		return String.format("%s (at %s)", atStatement.toString(), line);
	}
}
