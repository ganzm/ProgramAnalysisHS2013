package ch.ethz.pa.test.frameworkTests;

import org.junit.Assert;
import org.junit.Test;

import soot.jbco.util.Rand;
import ch.ethz.pa.BinaryUtil;

public class BinUtilTest {

	@Test
	public void testBinUtil() {
		for (int i = 0; i < 10000; i++) {
			int toTest = Rand.getInt();

			String resultString = BinaryUtil.toBinString(toTest);
			int result = BinaryUtil.fromBinString(resultString);

			Assert.assertEquals("Failed with " + toTest, toTest, result);
		}
	}

	@Test
	public void testBinUtil1() {
		Assert.assertEquals(1, BinaryUtil.fromBinString("00000000000000000000000000000001"));
		Assert.assertEquals(0, BinaryUtil.fromBinString("00000000000000000000000000000000"));
		Assert.assertEquals(-1, BinaryUtil.fromBinString("11111111111111111111111111111111"));
		Assert.assertEquals(-512, BinaryUtil.fromBinString("11111111111111111111111000000000"));
	}
}
