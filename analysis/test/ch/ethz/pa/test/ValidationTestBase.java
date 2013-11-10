package ch.ethz.pa.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;

import ch.ethz.pa.Verifier;

public class ValidationTestBase {

	private PrintStream originalOut;
	private ByteArrayOutputStream redirectedOutStream;

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
	
		if (expectedToBeSafe) {
			Assert.assertEquals("Program is SAFE", output);
		} else {
			Assert.assertEquals("Program is UNSAFE", output);
		}
	}

}