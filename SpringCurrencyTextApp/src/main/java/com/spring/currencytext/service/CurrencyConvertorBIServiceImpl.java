package com.spring.currencytext.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

/**
 * This service class will helps to convert the large amount into the word
 * format.
 * 
 * @author asingaram
 *
 */
@Service("currConverterBIService")
public class CurrencyConvertorBIServiceImpl implements CurrencyConvertorBIService {

	public static final String[] units = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
			"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };

	public static final String[] tens = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
			"ninenty" };

	/**
	 * The method will accept big integer number as input and convert into text
	 * 
	 * @param num
	 * @return
	 */
	public static String convert(final BigInteger num) {

		if (num.compareTo(new BigInteger("20")) == -1) {
			return units[num.intValue()];
		}

		else if (num.compareTo(new BigInteger("100")) == -1) {

			String firstPart = tens[num.divide(BigInteger.valueOf(10)).intValue()];

			BigInteger numReminder = num.remainder(BigInteger.valueOf(10));

			String secondPart = (numReminder.compareTo(BigInteger.ZERO) == 0) ? "" : " ";

			String thirdPart = units[numReminder.intValue()];

			return firstPart + secondPart + thirdPart;
		}

		else if (num.compareTo(new BigInteger("1000")) == -1) {

			String firstPart = units[num.divide(BigInteger.valueOf(100)).intValue()];

			BigInteger numReminder = num.remainder(BigInteger.valueOf(100));

			String secondPart = (numReminder.compareTo(BigInteger.ZERO) == 0) ? "" : " ";

			String thirdPart = convert(numReminder);

			return firstPart + " hundred" + secondPart + thirdPart;
		}

		else if (num.compareTo(new BigInteger("1000000")) == -1) {

			String firstPart = convert(num.divide(BigInteger.valueOf(1000)));

			BigInteger numReminder = num.remainder(BigInteger.valueOf(1000));

			String secondPart = (numReminder.compareTo(BigInteger.ZERO) == 0) ? "" : " ";

			String thirdPart = convert(num.remainder(BigInteger.valueOf(1000)));

			return firstPart + " thousand" + secondPart + thirdPart;
		}

		if (num.compareTo(new BigInteger("1000000000")) == -1) {

			String firstPart = convert(num.divide(BigInteger.valueOf(1000000)));

			BigInteger numReminder = num.remainder(BigInteger.valueOf(1000000));

			String secondPart = (numReminder.compareTo(BigInteger.ZERO) == 0) ? "" : " ";

			String thirdPart = convert(num.remainder(BigInteger.valueOf(1000000)));

			return firstPart + " million" + secondPart + thirdPart;

		}

		else {
			String firstPart = convert(num.divide(BigInteger.valueOf(1000000000)));

			BigInteger numReminder = num.remainder(BigInteger.valueOf(1000000000));

			String secondPart = (numReminder.compareTo(BigInteger.ZERO) == 0) ? "" : " ";

			String thirdPart = convert(num.remainder(BigInteger.valueOf(1000000000)));

			return firstPart + " billion" + secondPart + thirdPart;
		}

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
	public static String convertCurrrencyValue(BigInteger a, int b) {

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

		// The string utils to strip the $ sign from input parameter
		String realNumber = StringUtils.strip(inputString, "$").trim();

		// The variable is used to hold value for seperator and split paramter.
		String currencySplit = "\\.";
		String currencySeperator = ",";

		// This condition will replace comma from the input value
		String origialNumberNoComma = realNumber.trim();
		if (realNumber.contains(currencySeperator)) {
			origialNumberNoComma = realNumber.replace(currencySeperator, "").trim();
		}

		String[] ipSplit = origialNumberNoComma.trim().split(currencySplit);

		// String[] mySplit = realNumber.split("\\.");

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

		BigInteger decInt = decimal.toBigInteger();
		
		//The condition to check the input amount less than 1 hundred billion
		BigInteger maxAmt = new BigInteger("100000000000");
	 	int compResult = decInt.compareTo(maxAmt);
	 	if( compResult == 1 ) {
 			throw new NumberFormatException("Error: Max Amount - One hundred billion");
	 	}
	 	
	 	
		int fractInt = Integer.parseInt(reminder.toString());

		System.out.println("Formated Decimal :" + decInt);
		System.out.println("Formated Faction Int :" + fractInt);

		System.out.println(
				NumberFormat.getInstance().format(decInt) + "='" + convertCurrrencyValue(decInt, fractInt) + "'");

		return convertCurrrencyValue(decInt, fractInt);
	}

}
