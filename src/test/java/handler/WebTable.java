package handler;

import org.openqa.selenium.By;

import stepDefinition.BaseDef;
import util.UtilData;
import handler.Test;

public class WebTable {
public static Browser pageBrowser = BaseDef.browser;
	
	public static int getTotalCols(String tableXpath) {
		// Get total columns in table
		Test.waitElement(tableXpath);
		String tableElement = tableXpath + "/thead/tr/th";
		return pageBrowser.driver.findElements(By.xpath(tableElement)).size();
	}

	public static int getTotalRows(String tableXpath) {
		// Get total rows in table
		Test.waitElement(tableXpath);
		String totalRowsXpath = tableXpath + "/tbody/tr";
		return pageBrowser.driver.findElements(By.xpath(totalRowsXpath)).size();
	}

	public static int getRowIndex(String tableXpath, String cellValue) {
		// Get row index by input cell value
		Test.waitElement(tableXpath);
		String allTableText = Test.getElementAttribute(tableXpath, "innerHTML");
		String allinnerHTML = allTableText.replaceAll("(\\<\\w+)(.*?>)", "$1>");
		String text = "<td>" + cellValue + "</td>";
		boolean isExist = allinnerHTML.contains(text);
		int rowIdex = 0;
		if (isExist) {
			String[] listString = UtilData.splitString(allinnerHTML, text, 2);
			String firstString = listString[0];
			rowIdex = UtilData.countSubStringInString(firstString, "<tr>")-1;
		}
		return rowIdex;
	}

	public static int getColumnIndex(String tableXpath, String headerName) {
		// Get row index by input header name
		Test.waitElement(tableXpath);
		int col, columnIndex = 0;
		int totalCols = getTotalCols(tableXpath);
		for (col = 1; col <= totalCols; col++) {
			String cellXpath = tableXpath + "/thead/tr/th[" + col + "]";
			String cellText = Test.getElementText(cellXpath);
			if (cellText.equals(headerName)) {
				columnIndex = col;
				break;
			}
		}
		return columnIndex;
	}

	public static void clickTableCell(String tableXpath, int row, int col) {
		// Click cell per row & col
		Test.waitElement(tableXpath);
		String elementXPATH = tableXpath + "/tbody/tr[" + row + "]/td[" + col + "]";
		Test.waitThenClickElement(elementXPATH);
		Test.waitPageLoad();
	}

	public static String getTableCell(String tableXpath, int row, int col) {
		// Get cell value per row & col
		Test.waitElement(tableXpath);
		String elementXPATH = tableXpath + "/tbody/tr[" + row + "]/td[" + col + "]";
		return Test.getElementText(elementXPATH);
	}

	
	public static void checkTableCell(String tableXpath, int row, int col, String expectedCellValue,
			boolean isExact) {
		// Check cell value per row & col
		Test.waitElement(tableXpath);
		String cellXpath = tableXpath + "/tbody/tr[" + row + "]/td[" + col + "]";
		String elementText = Test.getElementText(cellXpath);
		if (isExact)
			Test.checkElementTextDisplayed(cellXpath, expectedCellValue);
		else
			Test.checkTextContainsOrNot(elementText, expectedCellValue, true);
	}
	
	public static void clickOnAction(String tableXpath, String header, String actionXpath, int row)
	// Click on an action on specified row
	{
		Test.waitElement(tableXpath);
		String xpath = tableXpath + "/tbody/tr[" + row + "]" + actionXpath;
		Test.waitThenClickElement(xpath);
	}
}
