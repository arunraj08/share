package com.spring.currencytext.SpringCurrencyTextApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The class will bootstrap check amount convertor application
 */
@SpringBootApplication(scanBasePackages={"com.spring.currencytext"})
public class SpringCurrenyTextApp 
{
	public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringCurrenyTextApp.class, args);           
    }
}
