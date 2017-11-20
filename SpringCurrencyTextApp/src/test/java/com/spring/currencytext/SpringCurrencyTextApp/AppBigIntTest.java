package com.spring.currencytext.SpringCurrencyTextApp;

import com.spring.currencytext.service.CurrencyConvertorBIServiceImpl;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * The class is used to validate Big integer input amount value
 * 
 * @author asingaram
 */

public class AppBigIntTest extends TestCase {
	/**
	 * Test Case to validate conversion of amount to word
	 *
	 * @param testName
	 */
	public AppBigIntTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppBigIntTest.class);
	}

	/**
	 * Test case to validate amount with zero fraction
	 * 
	 * @throws Exception
	 */
	public void testDecimalNumberWithZeroFraction() throws Exception {
		CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("85.00");
		assertEquals(currText, "Eighty five dollars only");
	}

	/**
	 * Test case to validate amount with non-zero fraction
	 * 
	 * @throws Exception
	 */
	public void testTwoDecimalNumber() throws Exception {
		CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("123.12");
		assertEquals(currText, "One hundred twenty three dollars and 12/100");
	}

	/**
	 * Test case to validate one digit number
	 * 
	 * @throws Exception
	 */

	public void testOneDigitWholeNumber() throws Exception {
		CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("6");
		assertEquals(currText, "Six dollars only");
	}

	/**
	 * Negative Test case to validate amount without only word
	 * 
	 * @throws Exception
	 */
	public void testNegativeDecimalNumberWithZeroFraction() throws Exception {
		CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("85.00");
		assertNotSame(currText, "Eighty five dollars");
	}

	/**
	 * Test case to validate number greater than 4 byte Not throws Exception
	 * 
	 * @throws Exception
	 */
	public void testNumberGreaterThanLargerValue() throws Exception {
		boolean expnthrown = false;
		try {
			CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
			ccServiceImpl.findCurrencyText("2147483648");
		} catch (NumberFormatException expected) {
			expnthrown = true;
		}
		assertFalse(expnthrown);
	}

	/**
	 * Negative Test case to validate first test case is captial one
	 * 
	 * @throws Exception
	 */
	public void testNegativeCapitalCharacterValidation() throws Exception {
		CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
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
			CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
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
			CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();

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
			ccServiceImpl.findCurrencyText("10000000000");
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
		CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
		String currText = ccServiceImpl.findCurrencyText(" 12.00");
		assertEquals(currText, "Twelve dollars only");
	}

	/**
	 * Test case to validate Trailing white space
	 * 
	 * @throws Exception
	 */
	public void testTrailingSpaceTest() throws Exception {
		CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("12.00 ");
		assertEquals(currText, "Twelve dollars only");
	}

	/**
	 * Test case to validate Leading and Trailing white space
	 * 
	 * @throws Exception
	 */
	public void testTrailingandLeadingSpaceTest() throws Exception {
		CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
		String currText = ccServiceImpl.findCurrencyText(" 12.00 ");
		assertEquals(currText, "Twelve dollars only");
	}

	/**
	 * Test case to strip the dollar sign
	 * 
	 * @throws Exception
	 */
	public void testDollarSignTest() throws Exception {
		CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("$12.00");
		assertEquals(currText, "Twelve dollars only");
	}

	/**
	 * Test case to validate Comma seperated values
	 * 
	 * @throws Exception
	 */
	public void testCommaSeperationTest() throws Exception {
		CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
		String currText = ccServiceImpl.findCurrencyText("1,112.00");
		assertEquals(currText, "One thousand one hundred twelve dollars only");
	}
	
	/**
	 * Test case to validate maximum input value.
	 * 
	 * @throws Exception
	 */
	public void testMaxInputValue() throws Exception {
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
	 * Test case to validate  input value greater than maximum input
	 * 
	 * @throws Exception
	 */
	public void testNumberGreaterThanMaxInputValue() throws Exception {
		boolean expnthrown = false;
		try {
			CurrencyConvertorBIServiceImpl ccServiceImpl = new CurrencyConvertorBIServiceImpl();
			ccServiceImpl.findCurrencyText("10000000000011111");
		} catch (NumberFormatException expected) {
			expnthrown = true;
		}
		assertTrue(expnthrown);
	}


}
