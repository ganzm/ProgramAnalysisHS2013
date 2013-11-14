package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class WhileTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testGoodSimpleWhile1() {
		testAnyProgram("GoodSimpleWhile1", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodSimpleWhile1Debug() {
		testAnyProgram("GoodSimpleWhile1", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadSimpleWhile2() {
		testAnyProgram("BadSimpleWhile2", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadSimpleWhile2Debug() {
		testAnyProgram("BadSimpleWhile2", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodSimpleWhile3() {
		testAnyProgram("GoodSimpleWhile3", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodSimpleWhile3Debug() {
		testAnyProgram("GoodSimpleWhile3", SAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadSimpleWhile4() {
		testAnyProgram("BadSimpleWhile4", UNSAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testBadSimpleWhile4Debug() {
		testAnyProgram("BadSimpleWhile4", UNSAFE, DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodSimpleWhile5() {
		testAnyProgram("GoodSimpleWhile5", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testGoodSimpleWhile5Debug() {
		testAnyProgram("GoodSimpleWhile5", SAFE, DEBUG);
	}
}
