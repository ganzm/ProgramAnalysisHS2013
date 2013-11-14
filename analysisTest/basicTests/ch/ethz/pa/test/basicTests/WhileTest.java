package ch.ethz.pa.test.basicTests;

import org.junit.Test;

import ch.ethz.pa.test.ValidationTestBase;

public class WhileTest extends ValidationTestBase {

	@Test(timeout = 10000)
	public void testSimpleWhile() {
		testAnyProgram("SimpleWhile", SAFE, NO_DEBUG);
	}

	@Test(timeout = 10000)
	public void testSimpleSimpleWhileDebug() {
		testAnyProgram("SimpleWhile", SAFE, DEBUG);
	}
}
