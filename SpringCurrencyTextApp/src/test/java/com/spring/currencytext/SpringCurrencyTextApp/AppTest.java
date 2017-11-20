package com.spring.currencytext.SpringCurrencyTextApp;

import com.spring.currencytext.service.CurrencyConvertorBIServiceImpl;
import com.spring.currencytext.service.CurrencyConvertorServiceImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * The class is used to validate int input amount value
 * @author asingaram
 *
 */
public class AppTest extends TestCase {
	/**
	 * Test Case to validate conversion of amount to word
	 *
	 * @param testName
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Test case to validate amount with zero fraction
	 * 
	 * @throws Exception
	 */
	public void testDecimalNumberWithZeroFraction() throws Exception {
		CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("85.00");
		assertEquals(currText, "Eighty five dollars only");
	}

	/**
	 * Test case to validate amount with non-zero fraction
	 * 
	 * @throws Exception
	 */
	public void testTwoDecimalNumber() throws Exception {
		CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("123.12");
		assertEquals(currText, "One hundred twenty three dollars and 12/100");
	}

	/**
	 * Test case to validate one digit number
	 * 
	 * @throws Exception
	 */
	public void testOneDigitWholeNumber() throws Exception {
		CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("6");
		assertEquals(currText, "Six dollars only");
	}

	/**
	 * Negative Test case to validate amount without only word
	 * 
	 * @throws Exception
	 */
	public void testNegativeDecimalNumberWithZeroFraction() throws Exception {
		CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("85.00");
		assertNotSame(currText, "Eighty five dollars");
	}

	/**
	 * Test case to validate number greater than 4 byte throws Exception
	 * 
	 * @throws Exception
	 */

	public void testNumberGreaterThanLargerValue() throws Exception {
		boolean expnthrown = false;
		try {
			CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
			ccServiceImpl.findCurrencyText("100000001111111");
		} catch (NumberFormatException expected) {
			expnthrown = true;
		}
		assertTrue(expnthrown);
	}

	/**
	 * Negative Test case to validate first test case is captial one
	 * 
	 * @throws Exception
	 */
	public void testNegativeCapitalCharacterValidation() throws Exception {
		CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("69");
		assertNotSame(currText, "sixty nine dollars only");
	}

	/**
	 * Negative test case to validate non-numeric string throws exception
	 * 
	 * @throws Exception
	 */
	public void testCaseNumberFormatException() throws Exception {
		boolean thrown = false;
		try {
			CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
			ccServiceImpl.findCurrencyText("12Test");
		} catch (NumberFormatException expected) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	/**
	 * Test case to validate negative Number
	 * 
	 * @throws Exception
	 */
	public void testCaseNegativeNumberException() throws Exception {
		try {
			CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();

			ccServiceImpl.findCurrencyText("-1");

		} catch (NumberFormatException expected) {

			assertEquals(expected.getMessage(), "Error: Enter Positive Number");

		}

	}

	/**
	 * Test case to validate NUmber greater than Max Integer Value
	 * 
	 * @throws Exception
	 */
	public void testNumberGreaterThanIntegerValue() throws Exception {
		boolean expnthrown = false;
		try {
			CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
			ccServiceImpl.findCurrencyText("100000000000");
		} catch (NumberFormatException expected) {
			expnthrown = true;
		}
		assertFalse(expnthrown);
	}

	/**
	 * Test case to validate Leading white space
	 * 
	 * @throws Exception
	 */
	public void testLeadingSpaceTest() throws Exception {
		CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
		String currText = ccServiceImpl.findCurrencyText(" 12.00");
		assertEquals(currText, "Twelve dollars only");
	}

	/**
	 * Test case to validate Trailing white space
	 * 
	 * @throws Exception
	 */
	public void testTrailingSpaceTest() throws Exception {
		CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("12.00 ");
		assertEquals(currText, "Twelve dollars only");
	}

	/**
	 * Test case to validate Leading and Trailing white space
	 * 
	 * @throws Exception
	 */
	public void testTrailingandLeadingSpaceTest() throws Exception {
		CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
		String currText = ccServiceImpl.findCurrencyText(" 12.00 ");
		assertEquals(currText, "Twelve dollars only");
	}

	/**
	 * Test case to validate Comma seperated values
	 * 
	 * @throws Exception
	 */
	public void testCommaSeperationTest() throws Exception {
		CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("1,112.00");
		assertEquals(currText, "One thousand one hundred twelve dollars only");
	}

	/**
	 * Test case to strip the dollar sign
	 * 
	 * @throws Exception
	 */
	public void testDollarSignTest() throws Exception {
		CurrencyConvertorServiceImpl ccServiceImpl = new CurrencyConvertorServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("$12.00");
		assertEquals(currText, "Twelve dollars only");
	}

}
