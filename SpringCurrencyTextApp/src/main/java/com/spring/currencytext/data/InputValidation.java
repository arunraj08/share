package com.spring.currencytext.data;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The class binds the input data and validate the input amount
 * @author asingaram
 *
 */
public class InputValidation {

    @NotBlank(message = "Amount can't empty!")
    String amount;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

    
}