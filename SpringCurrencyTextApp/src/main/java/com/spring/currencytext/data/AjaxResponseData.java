package com.spring.currencytext.data;


/**
 * The POJO class bind the result to ajax response 
 * @author asingaram
 *
 */
public class AjaxResponseData {
    
	//the attribute will hold error response message
    String msg;
    
    //the attribute will hold the amount in word format
    String result;
    
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
