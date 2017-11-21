package com.spring.currencytext.SpringCurrencyTextApp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.currencytext.data.AjaxResponseData;
import com.spring.currencytext.data.InputValidation;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppBigIntIntegrationTest {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/";
	 
    @Autowired
    TestRestTemplate restTemplate;

    String ajaxLargeAmtReq;

    @Before
    public void setUp() {
        ajaxLargeAmtReq="/getBIAjaxCurrTxt";
    }
    
    @Test
    public void testPostiveCasesForIntAmount() {
    	getSuccessResponseForPostURI(ajaxLargeAmtReq,"85.00","Eighty five dollars only");
    	getSuccessResponseForPostURI(ajaxLargeAmtReq,"123.12","One hundred twenty three dollars and 12/100");
    	getSuccessResponseForPostURI(ajaxLargeAmtReq,"6","Six dollars only");
    	getSuccessResponseForPostURI(ajaxLargeAmtReq,"1,112.00","One thousand one hundred twelve dollars only");
    	getSuccessResponseForPostURI(ajaxLargeAmtReq,"$12.00","Twelve dollars only"); 
     }
    
    @Test
    public void testNegativeCasesForIntAmount() {    	
    	getNotRqualsResponseForPostURI(ajaxLargeAmtReq,"85.00","Eighty five dollars");
     }
    
   @Test
    public void testToValidateInvalidInputRequest() {
    	getBadRequestResponseForPostURI(ajaxLargeAmtReq,"123Test","Error: Enter Valid Number");
    	getBadRequestResponseForPostURI(ajaxLargeAmtReq,"-1","Error: Enter Positive Number");
    	getBadRequestResponseForPostURI(ajaxLargeAmtReq,"10000000000011111","Error: Max Amount - One hundred billion");
    }
    
    private void getSuccessResponseForPostURI(String url, String inputAmount, String expectedResult) {    	
    	InputValidation inputValidation= new InputValidation();
    	inputValidation.setAmount(inputAmount);    	
    	ResponseEntity<AjaxResponseData> responseEntity =
                restTemplate.postForEntity(REST_SERVICE_URI+url, inputValidation, AjaxResponseData.class);
    	AjaxResponseData response = responseEntity.getBody();    	
    	Assert.assertEquals(expectedResult, response.getResult());
    }
    
    private void getNotRqualsResponseForPostURI(String url, String inputAmount, String expectedResult) {
    	
    	InputValidation inputValidation= new InputValidation();
    	inputValidation.setAmount(inputAmount);    	
    	ResponseEntity<AjaxResponseData> responseEntity =
                restTemplate.postForEntity(REST_SERVICE_URI+url, inputValidation, AjaxResponseData.class);
    	AjaxResponseData response = responseEntity.getBody();    	
    	Assert.assertNotEquals(expectedResult, response.getResult());
    }
    
    private void getBadRequestResponseForPostURI(String url, String inputAmount, String errorMsg) {
    	
    	InputValidation inputValidation= new InputValidation();
    	inputValidation.setAmount(inputAmount);    	
    	ResponseEntity<AjaxResponseData> responseEntity =
                restTemplate.postForEntity(REST_SERVICE_URI+url, inputValidation, AjaxResponseData.class);
    	AjaxResponseData response = responseEntity.getBody();    	
    	Assert.assertEquals(errorMsg, response.getMsg());
    	Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
   
}
