package ch.ethz.pa.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;

import ch.ethz.pa.Verifier;

public class ValidationTestBase {

	private PrintStream originalOut;
	private ByteArrayOutputStream redirectedOutStream;

	protected final static boolean SAFE = true;
	protected final static boolean UNSAFE = false;

	protected boolean defaultDebugFlag = false;

	public ValidationTestBase() {
		super();
	}

	private void redirectOutputStream() {
		// backup output stream
		originalOut = System.out;

		redirectedOutStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(redirectedOutStream));

	}

	private String restoreOutputStream() {
		System.out.close();
		System.setOut(originalOut);
		return new String(redirectedOutStream.toByteArray());
	}

	protected void testAnyProgram(String name, boolean expectedToBeSafe) {
		testAnyProgram(name, expectedToBeSafe, defaultDebugFlag);
	}

	protected void testAnyProgram(String name, boolean expectedToBeSafe, boolean isDebug) {
		String output = null;

		String[] args = null;
		if (isDebug) {
			args = new String[] { name, "-d" };
		} else {
			args = new String[] { name };
		}
		redirectOutputStream();
		try {
			int retVal = Verifier.intMain(args);
			Assert.assertEquals(0, retVal);
		} finally {
			output = restoreOutputStream();
			System.out.println("Verifier StdOut:\n" + output);
		}

		String resultText = isDebug ? lastLineOf(output) : output;

		if (expectedToBeSafe) {
			Assert.assertEquals(Verifier.PROGRAM_IS_SAFE, resultText);
		} else {
			Assert.assertEquals(Verifier.PROGRAM_IS_UNSAFE, resultText);
		}
	}

	private String lastLineOf(String text) {
		String lines[] = text.split("\n");
		return lines.length > 0 ? lines[lines.length - 1] : "";
	}
}