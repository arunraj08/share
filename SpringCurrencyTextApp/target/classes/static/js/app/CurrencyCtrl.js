'use strict';

currTextApp.controller('CurrencyCtrl',['CurrencyService', '$scope',  function(CurrencyService, $scope) {
	
	  var self = $scope;
	  
      self.submit = submit;      
      self.bigIntsubmit = bigIntsubmit;

      self.successMessage = '';
      self.errorMessage = '';
      self.convertedAmount = '';
      self.done = false;

      var search = {};
      
      search["amount"] = self.amount;   
	    
	  function submit() {            
		  console.log('Submitting');                
          console.log('Amount-----'+self.amount);            
          getTextValueForAmount(self.amount);                     
        } 
	  function bigIntsubmit() {            
		  console.log('Submitting bigIntsubmit');                
          console.log('Amount bigIntsubmit-----'+self.amount);
            
          getTextValueForbigIntAmount(self.amount);                     
        } 
	  
	  function getTextValueForAmount(amt) {          
		  console.log('Controller - At Converting Amount');          
          CurrencyService.getCurrencyText(amt)          
              .then(
                  function (response) {
                      console.log('Text Retrieved successfully');
                      self.successMessage = 'Text Retrieved successfully';
                      self.errorMessage='';
                      self.convertedAmount=response.result;
                      self.done = true;                      
                  },
                  function (errResponse) {
                      console.error('Error while retrieving Amount', errResponse);                      
                      self.errorMessage = errResponse.data.msg;
                      self.successMessage='';
                  }
              );
      }
	  
	  function getTextValueForbigIntAmount(amt) {          
		  console.log('Controller - At Converting Amount');          
          CurrencyService.getCurrencybigIntText(amt)          
              .then(
                  function (response) {
                      console.log('Text Retrieved successfully');
                      self.successMessage = 'Text Retrieved successfully';
                      self.errorMessage='';
                      self.convertedAmount=response.result;
                      self.done = true;                      
                  },
                  function (errResponse) {
                      console.error('Error while retrieving Amount', errResponse);                      
                      self.errorMessage = errResponse.data.msg;
                      self.successMessage='';
                  }
              );
      }

}]);