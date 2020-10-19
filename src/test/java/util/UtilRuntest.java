package util;

import org.openqa.selenium.WebDriver;
import handler.Browser;

public class UtilRuntest {
	public static Boolean testWasInitiated = false;

	// ----------------------------------------------

	public static void initiate() {
		
		testWasInitiated = true;

	}
	
	public static void finalize (Browser myBrowser) {

		WebDriver driver = myBrowser.driver;

		if (driver == null) {
			return;
		}
		myBrowser.close();

	}
}
