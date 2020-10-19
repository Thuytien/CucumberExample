package handler;

import stepDefinition.BaseDef;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {
	

	public static Browser pageBrowser = BaseDef.browser;
	public static long waitElement = 5;
	
	public static void waitBySleepEverything(long waitingTimeInMilliSecond) {
		try {
			Thread.sleep(waitingTimeInMilliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitPageLoad() {
	
		long waitingTimeInMilliSecond = 10000;
		int previousElementNumber;
		int currentElementNumber = pageBrowser.driver.findElements(By.xpath("//*")).size();
		long timeSlice = 500;
		long totalWaitTime = 0;
		long waitMask = 30000;
		do {
			if (totalWaitTime >= waitingTimeInMilliSecond) {
				Assert.assertTrue("Page load timeout", false);
			}
			previousElementNumber = currentElementNumber;
			waitBySleepEverything(timeSlice);
			totalWaitTime += timeSlice;
			currentElementNumber = pageBrowser.driver.findElements(By.xpath("//*")).size();
		} while (currentElementNumber > previousElementNumber);
		
		waitLoadingMaskDismissed(waitMask);
	}
	
	private static void waitLoadingMaskDismissed(long loadingMaskWaitingTimeInMilliSecond) {
		Boolean loadingMaskDismissed = false;
		String loadingMaskXPATH = "//div[contains(@class,'sc-bdVaJa eSmrHg')]";
		long timeSlice = 500;
		long totalWaitTime = 0;
		do {
			waitBySleepEverything(timeSlice);
			totalWaitTime += timeSlice;
			try {
				pageBrowser.driver.findElement(By.xpath(loadingMaskXPATH));
			} catch (NoSuchElementException e) {
				loadingMaskDismissed = true;
				break;
			} catch (ElementNotVisibleException e) {
				loadingMaskDismissed = true;
				break;
			}
		} while (totalWaitTime < loadingMaskWaitingTimeInMilliSecond);
		if (loadingMaskDismissed == false) {
			Assert.assertTrue(
					"Loading mask not dismissed after: '" + loadingMaskWaitingTimeInMilliSecond + "' milliseconds",
					loadingMaskDismissed);
		}
	}
	
	public static String getPageSource() {
		String pageSource = pageBrowser.driver.getPageSource();
		return pageSource;
	}
	
	public static WebElement waitElement(String element) {
		long seconds = waitElement;
		WebElement returnedElement = null;
		try {
			WebDriverWait driverWait = new WebDriverWait(pageBrowser.driver, seconds);
			returnedElement = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
		} catch (TimeoutException e) {
			Assert.assertFalse("[waitForElement] failed: " + e.getMessage(), true);
		} catch (NoSuchElementException e) {
			Assert.assertFalse("[waitForElement] failed: " + e.getMessage(), true);
		} catch (ElementNotVisibleException e) {
			Assert.assertFalse("[waitForElement] failed: " + e.getMessage(), true);
		}
		return returnedElement;
	}

	public static List<WebElement> waitForAllElements(String element) {
		long seconds = waitElement;
		List<WebElement> returnedElementsList = null;
		try {
			WebDriverWait driverWait = new WebDriverWait(pageBrowser.driver, seconds);
			returnedElementsList = driverWait
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(element)));
		} catch (TimeoutException e) {
			Assert.assertFalse("[waitForAllElements] failed: " + e.getMessage(), true);
		} catch (NoSuchElementException e) {
			Assert.assertFalse("[waitForAllElements] failed: " + e.getMessage(), true);
		} catch (ElementNotVisibleException e) {
			Assert.assertFalse("[waitForAllElements] failed: " + e.getMessage(), true);
		}
		return returnedElementsList;
	}
	
	public static WebElement findElementNoWait(String element) {
		WebElement returnedElement = null;
		try {
			returnedElement = pageBrowser.driver.findElement(By.xpath(element));
		} catch (TimeoutException e) {
			Assert.assertFalse("[findElementNoWait] failed: " + e.getMessage(), true);
		} catch (NoSuchElementException e) {
			Assert.assertFalse("[findElementNoWait] failed: " + e.getMessage(), true);
		} catch (ElementNotVisibleException e) {
			Assert.assertFalse("[findElementNoWait] failed: " + e.getMessage(), true);
		}
		return returnedElement;
	}

	public static List<WebElement> findAllElementsNoWait(String element) {
		List<WebElement> returnedAllElements = null;
		try {
			returnedAllElements = pageBrowser.driver.findElements(By.xpath(element));
		} catch (TimeoutException e) {
			Assert.assertFalse("[findAllElementsNoWait] failed: " + e.getMessage(), true);
		} catch (NoSuchElementException e) {
			Assert.assertFalse("[findAllElementsNoWait] failed: " + e.getMessage(), true);
		} catch (ElementNotVisibleException e) {
			Assert.assertFalse("[findAllElementsNoWait] failed: " + e.getMessage(), true);
		}
		return returnedAllElements;
	}
	
	public static boolean elementIsNotAvailable(String element) {
		boolean isNotAvailable = true;
		try {
			pageBrowser.driver.findElement(By.xpath(element));
			isNotAvailable = false;
		} catch (TimeoutException e) {
		} catch (NoSuchElementException e) {
		} catch (ElementNotVisibleException e) {
		}
		return isNotAvailable;
	}
	
	public static void checkElementIsReadOnly(String elementXpath)
	{
		WebElement element = waitElement(elementXpath);
		String readonly = element.getAttribute("readonly");
		Assert.assertNotNull(readonly);
	}
	
	
	// ------------------------------------------------------------//
	
	// ACTION relating to MOUSE / KEYBOARD / INPUTTING DATA

	
	public static Actions doAction() {
		Actions action = new Actions(pageBrowser.driver);
		return action;
	}

	public static Actions hoverElement(String elementXpath) {
		WebElement hoverElement = waitElement(elementXpath);
		return hoverElement(hoverElement);
	}

	public static Actions hoverElement(WebElement hoverElement) {
		Actions action = new Actions(pageBrowser.driver);
		try {
			action.moveToElement(hoverElement).build().perform();
		} catch (Exception e) {
			Assert.assertFalse("hoverElement failed: " + e.getMessage(), true);
		}
		return action;
	}

	public static Actions clickThenTypeOnElement(String elementXpath, String typingContent) {
		Actions action = new Actions(pageBrowser.driver);
		WebElement theElement = waitElement(elementXpath);
		try {
			action.moveToElement(theElement).click().sendKeys(typingContent).build().perform();
		} catch (Exception e) {
			Assert.assertFalse("clickThenTypeOnElement failed: " + e.getMessage(), true);
		}
		return action;
	}

	public static void waitThenClickElement(String elementXpath) {
		WebElement theElement = waitElement(elementXpath);
		try {
			theElement.click();
		} catch (Exception e) {
			Assert.assertFalse("waitThenClickElement failed: " + e.getMessage(), true);
		}
	}
	
	public static void clickElement (String elementXpath) {
		WebElement theElement = findElementNoWait(elementXpath);
		try {
			theElement.click();
		} catch (Exception e) {
			Assert.assertFalse("waitThenClickElement failed: " + e.getMessage(), true);
		}
	}

	public static boolean doesElementExist(String elementXpath) {

		try {
			int totalElement = pageBrowser.driver.findElements(By.xpath(elementXpath)).size();
			if (totalElement != 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			Assert.assertFalse("fineElementExistOrNot action is failed: " + e.getMessage(), true);
			return false;
		}
	}
	
	public static void checkElementNotDisplayed(String elementXpath) {
		boolean isExist = doesElementExist(elementXpath);
		if (isExist == false)
			Assert.assertTrue(true);
		else
			Assert.assertTrue("checkElementNotDisplayed action is failed", false);
	}

	public static String getElementAttribute(String elementXpath, String attributeType) {
		WebElement element = waitElement(elementXpath);
		return element.getAttribute(attributeType);
	}
	
	public static void checkElementAttribute(String elementXpath, String attributeType, String expected) {
		String currentValue = getElementAttribute(elementXpath, attributeType);
		Assert.assertTrue("checkElementAttribute action is failed: =====> 'Current value: " + currentValue
				+ "' and 'Expected value: " + expected + "'", currentValue.equals(expected));
	}

	public static void checkTitleDisplayed(String expectedTitle) {
		waitPageLoad();
		String currentTitle = pageBrowser.driver.getTitle();
		Assert.assertTrue("checkTitleDisplayed action is failed: =====> 'Current Title: " + currentTitle
				+ "' and 'Expected Title: " + expectedTitle + "'", currentTitle.equals(expectedTitle));
	}

	public static String getElementText(String elementXpath) {
		WebElement theElement = waitElement(elementXpath);
		return theElement.getText();
	}

	public static void checkElementTextDisplayed(String elementXpath, String expectedText) {
		waitElement(elementXpath);
		String currentText = getElementText(elementXpath);
		Assert.assertTrue("checkElementTextDisplayed action is failed: =====> 'Element Text: " + currentText
				+ "' and 'Expected: " + expectedText + "'", currentText.equals(expectedText));
	}
	
	public static void checkTextContainsOrNot(String elementText, String expectedText, boolean isContain) {
		if (isContain) {
			Assert.assertTrue("checkTextContainsOrNot action is failed: =====> 'Element Text: " + elementText
					+ "' and 'Expected: " + expectedText + "'", elementText.contains(expectedText));
		} else
			Assert.assertFalse("checkTextContainsOrNot action is failed: =====> 'Element Text: " + elementText
					+ "' and 'Expected: " + expectedText + "'", elementText.contains(expectedText));
	}
	
	public static void clearThenTextOnElement(String elementXpath, String typingContent) {
		WebElement theElement = waitElement(elementXpath);
		theElement.clear();
		theElement.sendKeys(typingContent);
	}
	
	
}
