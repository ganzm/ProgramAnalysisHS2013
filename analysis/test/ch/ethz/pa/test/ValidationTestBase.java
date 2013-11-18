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

	protected final static boolean DEBUG = true;
	protected final static boolean NO_DEBUG = false;

	protected boolean defaultDebugFlag = false;

	public ValidationTestBase() {
		super();
	}

	@BeforeClass
	public static void beforeClass() {
		LoggerUtil.iniDebug();
	}

	protected void testAnyProgram(String name, boolean expectedToBeSafe) {
		List<String> problemList = Verifier.intMain(new String[] { name });

		for (String problem : problemList) {
			logger.info("Problem " + problem);
		}

		if (expectedToBeSafe) {
			Assert.assertEquals(0, problemList.size());
		} else {
			Assert.assertTrue(0 < problemList.size());
		}
	}
}