package ch.ethz.pa.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

import ch.ethz.pa.Verifier;

public class ValidationTest {

	private PrintStream originalOut;

	private ByteArrayOutputStream redirectedOutStream;

	@Test
	public void testExample() {
		testAnyProgram("ExampleTest", true);
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

	private void testAnyProgram(String name, boolean expectedToBeSafe) {
		String output = null;

		String[] args = new String[] { name };
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
