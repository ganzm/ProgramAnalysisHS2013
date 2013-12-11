package ch.ethz.pa.test.frameworkTests;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ethz.pa.BinaryUtil;
import ch.ethz.pa.ProblemException;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.logging.LoggerUtil;

public class IntervalBitUnsignedShiftRightTest {

	private final Logger logger = Logger.getLogger(IntervalBitUnsignedShiftRightTest.class.getSimpleName());

	@BeforeClass
	public static void beforeClass() {
		LoggerUtil.iniDebug();
	}

	@Test
	public void testUnsignedShiftRight() {
		Interval iRes = null;

		Interval i1024 = new Interval(1024);
		Interval i512_1024 = new Interval(512, 1024);
		Interval i1 = new Interval(1);
		Interval i2 = new Interval(2);
		Interval i1_2 = new Interval(1, 2);

		// Default Case
		iRes = Interval.shiftUnsignedRight(i1024, i1);
		Assert.assertEquals(new Interval(512), iRes);

		// Easy Case
		iRes = Interval.shiftUnsignedRight(i1024, i2);
		Assert.assertEquals(new Interval(256), iRes);

		// Other Case
		iRes = Interval.shiftUnsignedRight(i1024, i1_2);
		Assert.assertEquals(new Interval(256, 512), iRes);

		// Case
		iRes = Interval.shiftUnsignedRight(i512_1024, i1);
		Assert.assertEquals(new Interval(256, 512), iRes);

		// Case
		iRes = Interval.shiftUnsignedRight(i512_1024, i1_2);
		Assert.assertEquals(new Interval(128, 512), iRes);
	}

	@Test
	public void testNegativeUnsignedShiftRight() {
		Interval iRes = null;
		String expectedString = null;
		int expected1;
		int expected2;

		// -1024 =0b11111111111111111111110000000000
		// -512 = 0b11111111111111111111111000000000
		//
		Interval i1024 = new Interval(-1024);

		Interval i512_1024 = new Interval(-1024, -512);
		Interval i1 = new Interval(1);
		Interval i2 = new Interval(2);
		Interval i1_2 = new Interval(1, 2);

		// no shift
		expectedString = "10000000000000000000000000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		expectedString = "10000000000000000000000000000000";
		expected2 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftUnsignedRight(new Interval(expected1), new Interval(0));
		Assert.assertEquals(new Interval(expected2), iRes);

		// Default Case
		expectedString = "01111111111111111111111000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftUnsignedRight(i1024, i1);
		Assert.assertEquals(new Interval(expected1), iRes);

		// Easy Case
		expectedString = "00111111111111111111111100000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftUnsignedRight(i1024, i2);
		Assert.assertEquals(new Interval(expected1), iRes);

		// Other Case
		expectedString = "01111111111111111111111000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		expectedString = "00111111111111111111111100000000";
		expected2 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftUnsignedRight(i1024, i1_2);
		Assert.assertEquals(new Interval(expected2, expected1), iRes);

		// Case
		expectedString = "01111111111111111111111000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		expectedString = "01111111111111111111111100000000";
		expected2 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftUnsignedRight(i512_1024, i1);
		Assert.assertEquals(new Interval(expected1, expected2), iRes);

		// Case
		expectedString = "01111111111111111111111000000000";
		expected1 = BinaryUtil.fromBinString(expectedString);
		expectedString = "00111111111111111111111110000000";
		expected2 = BinaryUtil.fromBinString(expectedString);
		iRes = Interval.shiftUnsignedRight(i512_1024, i1_2);
		Assert.assertEquals(new Interval(expected2, expected1), iRes);
	}

	@Test
	public void testException() {
		Interval i1 = new Interval(1024);
		Interval i2 = new Interval(-1024, -1);

		try {
			Interval.shiftUnsignedRight(i1, i2);
			Assert.fail("Expected ProblemException");
		} catch (ProblemException ex) {
			// Expected that
			logger.info("Got ProblemException " + ex);
		} catch (Exception ex) {
			Assert.fail("Expected ProblemException " + ex);
		}
	}
}
