package ch.ethz.pa;

public class Problem {

	private final Object atStatement;

	private final String line;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atStatement == null) ? 0 : atStatement.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Problem other = (Problem) obj;
		if (atStatement == null) {
			if (other.atStatement != null)
				return false;
		} else if (!atStatement.equals(other.atStatement))
			return false;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		return true;
	}

	public Problem(Object atStatement, String line) {
		this.atStatement = atStatement;
		this.line = line;
	}

	@Override
	public String toString() {
		return String.format("%s (at %s)", atStatement.toString(), line);
	}
}
