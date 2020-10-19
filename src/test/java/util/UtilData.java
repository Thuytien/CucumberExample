package util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;

//import org.apache.commons.lang.RandomStringUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;

public class UtilData {
	public static String depositAmount = "";

	public UtilData() {
	}

	public static List<List<String>> parseTableIntoString(DataTable inputTable) {

		List<List<String>> inputTableWithHeader = inputTable.raw();
		List<List<String>> returnedList = new ArrayList<List<String>>();

		for (int i = 1; i < inputTableWithHeader.size(); i++) {
			returnedList.add(inputTableWithHeader.get(i));
		}

		return returnedList;

	}

	public static String convertDateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		String dateN = formatter.format(date).replace("/", "_").replace(":", "_");
		return dateN;
	}

	public static String convertInputStreamToString(InputStream inStream) {

		String convertedString = null;

		try {
			convertedString = IOUtils.toString(inStream, StandardCharsets.UTF_8.name());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return convertedString;
		// Read more:
		// http://java67.blogspot.com/2014/05/3-examples-to-read-inputstream-as-String-Java-Guava-Commons.html#ixzz49dDV08xt

	}

	public static String removeEnterNewLineChar(String inputString) {
		return inputString.replaceAll("\\r\\n|\\n|\\r", "");
	}

	public static String removeTextAfter(String inputString, String beginningString) {
		return inputString.replaceAll("(?m)(?<=\\b" + beginningString + ").*$", "");
	}

	public static String removeTextBefore(String inputString, String endingString) {
		throw new PendingException();
	}

	// Start Action of Phung Nguyen

	public static String generateAlphaString(int length) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public static String generateNumericString(int length) {

		String AlphaNumericString = "0123456789";
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public static String generateAlphaNumericString(int length) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public static int randInt(int min, int max) {
		Random ran = new Random();
		int randomNum = ran.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public static String randIpAddress() {
		Random r = new Random();
		return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
	}

	public static String[] splitString(String parentString, String text, int number) {
		return parentString.split(text, number);
	}

	public static int countSubStringInString(String parentString, String subString) {
		int count = 0, fromIdex = 0;
		while ((fromIdex = parentString.indexOf(subString, fromIdex)) != -1) {
			count++;
			fromIdex++;
		}
		return count;
	}

	public static String removeAllSpecialCharacter(String originalString) {
		String newString = "";
		newString = originalString.replaceAll("/", "");
		newString = originalString.replaceAll("-", "");
		newString = newString.replaceAll(":", "");
		newString = newString.replaceAll(" ", "");
		newString = newString.replaceAll("\n", "");
		return newString;

	}

	public static String generateAlphabeticString(int count) {
		return RandomStringUtils.randomAlphabetic(count);
	}
}
