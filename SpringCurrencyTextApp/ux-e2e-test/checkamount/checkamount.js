describe('check amount currency convertor', function() {
	
  beforeEach(function() {
    browser.get('http://localhost:8080/');
  });
  
  it('success-test-1', function() {

   input = element(by.model('amount'));
   input.sendKeys('121');
   
   submit =  element(by.css('button.btn.btn-default'));
   submit.click();
   
 
   expect(element(by.css('.alert.alert-success')).getText()).toBe('One hundred twenty one dollars only');
	
  });
  
  
  it('success-test-2', function() {  
  
   input = element(by.name('amount')).sendKeys('111.2');
  
   submit =  element(by.css('button.btn.btn-default'));
   submit.click();
   
   expect(element(by.css('.alert.alert-success')).getText()).toBe('One hundred eleven dollars and 20/100');
	
  });
  
  
   it('error-test-1', function() {

   input = element(by.name('amount')).sendKeys('');
  
   submit =  element(by.css('button.btn.btn-default'));
   submit.click();
   
   expect(element(by.css('.alert.alert-danger')).getText()).toBe('Amount can\'t empty!');
	
  });
  
   it('error-test-2', function() {
	
   input = element(by.name('amount')).sendKeys('123Test');
  
   submit =  element(by.css('button.btn.btn-default'));
   submit.click();
   
   expect(element(by.css('.alert.alert-danger')).getText()).toContain('Error');
	
  });
  
  
});