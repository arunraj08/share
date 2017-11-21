describe('check amount currency convertor', function() {
	
  var input = element(by.model('amount'));
  var submit = element(by.css('button.btn.btn-default'));
  var result = element(by.css('.alert.alert-success'));
  var error = element(by.css('.alert.alert-danger'));
	
  beforeEach(function() {
    browser.get('http://localhost:8080/');
  });
  
  it('success-test-cases', function() {
   input.sendKeys('121');
   submit.click();
   input.clear();
   expect((result).getText()).toBe('One hundred twenty one dollars only');	
   
   input.sendKeys('111.2');
   submit.click();
   input.clear();
   expect((result).getText()).toBe('One hundred eleven dollars and 20/100');
   
   input.sendKeys('6');
   submit.click();
   input.clear();
   expect((result).getText()).toBe('Six dollars only');

  });
  
  
   it('error-test-cases', function() {
	   
   input.sendKeys('');
   submit.click();
   input.clear();
   expect((error).getText()).toBe('Amount can\'t empty!');	
   
   input.sendKeys('123Test');
   submit.click();
   input.clear();
   expect((error).getText()).toContain('Error');
   
   input.sendKeys('10000000000011111');
   submit.click();
   input.clear();
   expect((error).getText()).toContain('Error');   
   
  });
  
  
  
});