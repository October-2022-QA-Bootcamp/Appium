package appium;

import org.testng.annotations.Test;
import base.TestBase;

public class InstalledAppTests extends TestBase{

	@Test(enabled = false)
	public void installedAppTest() {
		calculatorObj.tapDigit4();
		calculatorObj.tapPlus();
		calculatorObj.tapDigit9();
		calculatorObj.tapEqual();
		calculatorObj.getResult();
	}
	
	@Test
	public void doSumTest() {
		calculatorObj.doSum(5,62, 111, -1234);
	}
}
