package com.spring.currencytext.service;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

/**
 * This service class will helps to convert the amount into the word format.
 * 
 * @author asingaram
 *
 */
@Service("currConverterService")
public class CurrencyConvertorServiceImpl implements CurrencyConvertorService {

	public static final String[] units = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
			"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };

	public static final String[] tens = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
			"ninenty" };

	/**
	 * The method will accept number as integer and convert into text
	 * 
	 * @param num
	 * @return
	 */
	public static String convert(final int num) {

		if (num > 0 && num < 20) {
			return units[num];
		}

		if (num < 100) {
			return tens[num / 10] + ((num % 10 != 0) ? " " : "") + units[num % 10];
		}

		if (num < 1000) {
			return units[num / 100] + " hundred" + ((num % 100 != 0) ? " " : "") + convert(num % 100);
		}

		if (num < 1000000) {
			return convert(num / 1000) + " thousand" + ((num % 1000 != 0) ? " " : "") + convert(num % 1000);
		}

		if (num < 1000000000) {
			return convert(num / 1000000) + " million" + ((num % 1000000 != 0) ? " " : "") + convert(num % 1000000);
		}

		return convert(num / 1000000000) + " billion" + ((num % 1000000000 != 0) ? " " : "")
				+ convert(num % 1000000000);
	}

	/**
	 * This method is used to make first character of String into upper case.
	 * 
	 * @param message
	 * @return
	 */
	public static String convertCase(String message) {
		message = message.toLowerCase();
		message = Character.toUpperCase(message.charAt(0)) + message.substring(1);
		return message;
	}

	/**
	 * The method will invoke the convert method for converting number and append
	 * string parameter - dollar and only based on fraction.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String convertCurrrencyValue(int a, int b) {

		StringBuffer stringBuffer = new StringBuffer();

		if (b != 0) {
			stringBuffer.append(convert(a)).append(" dollars").append(" and ").append(b).append("/100").toString();
		} else {
			stringBuffer.append(convert(a)).append(" dollars").append(" only");
		}
		return convertCase(stringBuffer.toString());

	}

	/**
	 * This method will accept the user input amount value and split value into real
	 * and fraction part.
	 */
	public String findCurrencyText(String inputString) throws Exception {

		// The stringutils to strip the $ sign from input parameter
		String realNumber = StringUtils.strip(inputString, "$").trim();

		// The variable is used to hold value for seperator and split paramter.
		String currencySplit = "\\.";
		String currencySeperator = ",";
		String origialNumberNoComma = realNumber.trim();

		// This method will replace comma from the input value
		if (realNumber.contains(currencySeperator)) {
			origialNumberNoComma = realNumber.replace(currencySeperator, "").trim();
		}

		String[] ipSplit = origialNumberNoComma.trim().split(currencySplit);

		// The condition checks whether its a valid number.
		if (!NumberUtils.isCreatable(ipSplit[0])) {
			throw new NumberFormatException("Error: Enter Valid Number");
		}

		// The condition check whether input amount greater than 0 and not a negative
		// number
		if (ipSplit[0].matches("-\\d+") || ipSplit[0].equals("0")) {
			throw new NumberFormatException("Error: Enter Positive Number");
		}

		BigDecimal decimal;
		BigDecimal real;
		BigDecimal reminder;
		try {
			decimal = new BigDecimal(ipSplit[0]);
			real = new BigDecimal(origialNumberNoComma);
			BigDecimal formatNumber = real.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			reminder = formatNumber.remainder(BigDecimal.ONE).movePointRight(formatNumber.scale());
		} catch (NumberFormatException exp) {
			throw new NumberFormatException("Error: Enter Valid Number");
		}

		int decInt;

		try {
			decInt = Integer.parseInt(decimal.toString());
		} catch (NumberFormatException exp) {
			throw new NumberFormatException("Error: Reached Maximum value");
		}

		int fractInt = Integer.parseInt(reminder.toString());

		System.out.println("Formated Decimal :" + decInt);
		System.out.println("Formated Faction Int :" + fractInt);

		System.out.println(
				NumberFormat.getInstance().format(decInt) + "='" + convertCurrrencyValue(decInt, fractInt) + "'");

		return convertCurrrencyValue(decInt, fractInt);
	}

}
