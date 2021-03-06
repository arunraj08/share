package com.spring.currencytext.service;


public interface CurrencyConvertorService {
	
	/**
	 * The method will accept the input amount and return amount in words
	 * @param id
	 * @return
	 * @throws Exception
	 */
	String findCurrencyText(String id) throws Exception;	
}
