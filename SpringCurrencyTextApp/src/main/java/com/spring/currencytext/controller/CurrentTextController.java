package com.spring.currencytext.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.spring.currencytext.data.AjaxResponseData;
import com.spring.currencytext.data.InputValidation;
import com.spring.currencytext.service.CurrencyConvertorBIService;
import com.spring.currencytext.service.CurrencyConvertorService;

@Controller
/**
 * The Controller will handle Ajax Post request for currency to text application 
 * @author asingaram
 */
public class CurrentTextController {	
	
	@Autowired
	CurrencyConvertorService currConverterService;
	
	@Autowired
	CurrencyConvertorBIService currConverterBIService; 	
	
	
	/**
	 * The method will return currInputForm page 
	 * where user can enter amount and convert the value
	 * @return main Page
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String getAjaxCurrTxtPage() {
        return "currInputForm";
    }
	
	
	
	/**
	 * The method will return currBigInputForm page 
	 * where user can enter large amount data to be converted into text
	 * @return main Page
	 */
	@RequestMapping(value="/largeAmountConversion", method=RequestMethod.GET)
    public String getAjaxCurrTxtBIPage() {
        return "currBigInputForm";
    }	
	
	/**
	 * The method will accept user input amount and invoke service to
	 * convert the amount into word
	 * 
	 * @param inputNo
	 * @param errors
	 * @return ajaxResponse
	 */
	
	@PostMapping("/getAjaxCurrTxt")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody InputValidation inputNo, Errors errors) {
		
		AjaxResponseData result = new AjaxResponseData();
		
		//bind the validation data to result object.
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }
        
        String currText ="";
		
		try {
			 currText = currConverterService.findCurrencyText(inputNo.getAmount());
		}
		catch(Exception exp) {				
			result.setMsg(exp.getMessage());
			return ResponseEntity.badRequest().body(result);
		}		

        result.setResult(currText);
            
        return ResponseEntity.ok(result);
        
    }
	
	/**
	 * The method can accept large amount as user input and invoke service to
	 * convert the amount into word
	 * 
	 * @param inputNo
	 * @param errors
	 * @return ajaxResponse
	 */
	
	@PostMapping("/getBIAjaxCurrTxt")
    public ResponseEntity<?> getSearchBIResultViaAjax(@Valid @RequestBody InputValidation inputNo, Errors errors) {
		
		AjaxResponseData result = new AjaxResponseData();
		
		//bind the validation data to result object.
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }
        
        String currText ="";
		
		try {
			 currText = currConverterBIService.findCurrencyText(inputNo.getAmount());
		}
		catch(Exception exp) {				
			result.setMsg(exp.getMessage());
			return ResponseEntity.badRequest().body(result);
		}		

        result.setResult(currText);
            
        return ResponseEntity.ok(result);
        
    }
	
}
