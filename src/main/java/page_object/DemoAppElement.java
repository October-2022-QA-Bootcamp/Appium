package page_object;

public interface DemoAppElement {

	void clickBoltShirt();
	void validateTitle(String expected);
	void clickAdd2Cart();
	void clickCart();
	void clickProceed2Checkout();
	void sendUserName(String user);
	void sendPass(String pass);
	void validateErrorMsg(String errorMsg);
}
