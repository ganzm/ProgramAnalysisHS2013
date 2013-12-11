package ch.ethz.pa.test.frameworkTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ethz.pa.BinaryUtil;
import ch.ethz.pa.intervals.Interval;
import ch.ethz.pa.logging.LoggerUtil;

public class IntervalBitShiftLeftTest {

	@BeforeClass
	public static void beforeClass() {
		LoggerUtil.iniDebug();
	}

	@Test
	public void testShiftLeft() {
		Interval iRes = null;
		String str = null;
		int expected;
		int expected2;
		int input;
		int input2;

		// shift by 0
		str = "00000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000001";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(0));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift by 1
		str = "00000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000010";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(1));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift by 32 = 0
		str = "00000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000001";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(32));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift interval by 1
		str = "00000000000000000000000000000010";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000100";
		input2 = BinaryUtil.fromBinString(str);

		str = "00000000000000000000000000000100";
		expected = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000001000";
		expected2 = BinaryUtil.fromBinString(str);

		iRes = Interval.shiftLeft(new Interval(input, input2), new Interval(1));
		Assert.assertEquals(new Interval(expected, expected2), iRes);

		// shift interval by 1 or 2
		str = "00000000000000000000000000000010";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000100";
		input2 = BinaryUtil.fromBinString(str);

		str = "00000000000000000000000000000100";
		expected = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000010000";
		expected2 = BinaryUtil.fromBinString(str);

		iRes = Interval.shiftLeft(new Interval(input, input2), new Interval(1, 2));
		Assert.assertEquals(new Interval(expected, expected2), iRes);

	}

	@Test
	public void testNegativeShiftLeft() {
		Interval iRes = null;
		String str = null;
		int expected;
		int expected2;
		int input;
		int input2;

		// shift by 0
		str = "10000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "10000000000000000000000000000001";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(0));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift by 1
		str = "10000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000000010";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(1));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift by 32 = 0
		str = "10000000000000000000000000000001";
		input = BinaryUtil.fromBinString(str);
		str = "10000000000000000000000000000001";
		expected = BinaryUtil.fromBinString(str);
		iRes = Interval.shiftLeft(new Interval(input), new Interval(32));
		Assert.assertEquals(new Interval(expected), iRes);

		// shift interval by 1
		str = "10000000000000000000000000000010";
		input = BinaryUtil.fromBinString(str);
		str = "10000000000000000000000000000100";
		input2 = BinaryUtil.fromBinString(str);

		str = "00000000000000000000000000000100";
		expected = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000001000";
		expected2 = BinaryUtil.fromBinString(str);

		iRes = Interval.shiftLeft(new Interval(input, input2), new Interval(1));
		Assert.assertEquals(new Interval(expected, expected2), iRes);

		// shift interval by 1 or 2
		str = "10000000000000000000000000000010";
		input = BinaryUtil.fromBinString(str);
		str = "10000000000000000000000000000100";
		input2 = BinaryUtil.fromBinString(str);

		str = "00000000000000000000000000000100";
		expected = BinaryUtil.fromBinString(str);
		str = "00000000000000000000000000010000";
		expected2 = BinaryUtil.fromBinString(str);

		iRes = Interval.shiftLeft(new Interval(input, input2), new Interval(1, 2));
		Assert.assertEquals(new Interval(expected, expected2), iRes);

	}

	@Test
	public void testSomeFunnyBitShifts() {

		System.out.println(BinaryUtil.toBinString(-1) + "= -1");
		System.out.println(BinaryUtil.toBinString(1 << -1) + "= 1 << -1");
		System.out.println(BinaryUtil.toBinString(1 << -2) + "= 1 << -2");
		System.out.println(BinaryUtil.toBinString(1 << 31) + "= 1 << 31");
		System.out.println(BinaryUtil.toBinString(1 << -31) + "= 1 << -31");
		System.out.println(BinaryUtil.toBinString(1 << 32) + "= 1 << 32");
		System.out.println(BinaryUtil.toBinString(1 << 33) + "= 1 << 33");
		System.out.println(BinaryUtil.toBinString(1 << -32) + "= 1 << -32");
		System.out.println(BinaryUtil.toBinString(1 << 0) + "= 1 << 32");
		System.out.println(BinaryUtil.toBinString(127 << -1) + "= 127 << -1");

		System.out.println(BinaryUtil.toBinString(-1 >>> -1) + "= -1 >>> -1");
		System.out.println(BinaryUtil.toBinString(-1 >>> -2) + "= -1 >>> -2");
		System.out.println(BinaryUtil.toBinString(-1 >>> 31) + "= -1 >>> 31");
		System.out.println(BinaryUtil.toBinString(-1 >>> 32) + "= -1 >>> 32");
		System.out.println(BinaryUtil.toBinString(-1 >>> -32) + "= -1 >>> -32");
		System.out.println(BinaryUtil.toBinString(-1 >>> 0) + "= -1 >>> 32");
	}

}
