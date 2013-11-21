package ch.ethz.pa.test;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;

import ch.ethz.pa.Verifier;
import ch.ethz.pa.logging.LoggerUtil;

public class ValidationTestBase {

	private final Logger logger = Logger.getLogger(ValidationTestBase.class.getSimpleName());

	protected final static boolean SAFE = true;
	protected final static boolean UNSAFE = false;

	public ValidationTestBase() {
		super();
	}

	@BeforeClass
	public static void beforeClass() {
		LoggerUtil.iniDebug();
	}

	protected void testAnyProgram(String name, boolean expectedToBeSafe) {

		long startTime = System.currentTimeMillis();
		List<String> problemList = Verifier.intMain(new String[] { name });
		long endTime = System.currentTimeMillis();

		for (String problem : problemList) {
			logger.info("Problem " + problem);
		}

		if (expectedToBeSafe) {
			Assert.assertEquals("Expected SAFE", 0, problemList.size());
		} else {
			Assert.assertTrue("Expected UNSAFE", 0 < problemList.size());
		}

		Assert.assertTrue("Violated Timing Constraints of 10s", endTime - startTime < 10000);
	}
}