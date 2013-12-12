package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class WhileTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testGoodSimpleWhile1() {
		testAnyProgram("GoodSimpleWhile1", SAFE);
	}

	@Test(timeout = 10000)
	public void testBadSimpleWhile2() {
		testAnyProgram("BadSimpleWhile2", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testGoodSimpleWhile3() {
		testAnyProgram("GoodSimpleWhile3", SAFE);
	}

	@Test(timeout = 10000)
	public void testBadSimpleWhile4() {
		testAnyProgram("BadSimpleWhile4", UNSAFE);
	}

	@Test(timeout = 10000)
	public void testGoodSimpleWhile5() {
		testAnyProgram("GoodSimpleWhile5", SAFE);
	}

	@Test(timeout = 10000)
	public void testGoodLoopTest1() {
		testAnyProgram("GoodLoopTest1", SAFE);
	}

	@Test(timeout = 10000)
	public void testGoodLoopTest2() {
		testAnyProgram("GoodLoopTest2", SAFE);
	}

}
