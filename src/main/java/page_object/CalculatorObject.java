package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static common.CommonAction.*;

public class CalculatorObject {

	WebDriver driver;

	public CalculatorObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(id = "com.google.android.calculator:id/digit_4")
	WebElement digit4;

	@FindBy(id = "com.google.android.calculator:id/digit_9")
	WebElement digit9;

	@FindBy(id = "com.google.android.calculator:id/op_add")
	WebElement plus;

	@FindBy(id = "com.google.android.calculator:id/op_sub")
	WebElement minus;

	@FindBy(id = "com.google.android.calculator:id/eq")
	WebElement equal;

	@FindBy(id = "com.google.android.calculator:id/result_final")
	WebElement result;

	public void tapDigit4() {
		click(digit4);
	}

	public void tapDigit9() {
		click(digit9);
	}

	public void tapPlus() {
		click(plus);
	}

	public void tapMinus() {
		click(minus);
	}

	public void tapEqual() {
		click(equal);
	}

	public void getResult() {
		getText(result);
	}

	public void doSum(int... arr) {
		for (int i : arr) {
			if (i < 10 && i > 0) {
				WebElement element = driver.findElement(By.id("com.google.android.calculator:id/digit_" + i));
				click(element);
				tapPlus();
			} else if (i >= 10) {
				for (char j : String.valueOf(i).toCharArray()) {
					WebElement element = driver.findElement(By.id("com.google.android.calculator:id/digit_" + j));
					click(element);
				}
				tapPlus();
			} else if (i < 0) {
				tapMinus();
				char [] charArr = String.valueOf(i).toCharArray();
				for (char j = 1; j < charArr.length; j++) {
					WebElement element = driver.findElement(By.id("com.google.android.calculator:id/digit_" + charArr[j]));
					click(element);
				}
			}
		}
		tapEqual();
		getResult();
	}
}
