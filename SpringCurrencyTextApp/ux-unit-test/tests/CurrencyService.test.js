describe('service currTextApp', function () {
    var currencyService;
    var $httpBackend;

    beforeEach(module('currTextApp'));
    beforeEach(inject(function (_CurrencyService_, _$httpBackend_) {
      currencyService = _CurrencyService_;
      $httpBackend = _$httpBackend_;
    }));
	
	
  afterEach(function() {    
     $httpBackend.verifyNoOutstandingRequest();
   });
   
	describe('currTextApplication function', function () {
      it('should exist', function () {
        expect(currencyService.getCurrencyText).not.toEqual(null);
      });

	  it('should return sucess response', function () {
		  
		var search = {}
	    search["amount"] = "6";		
		var dataObj = JSON.stringify(search);


       $httpBackend.when('POST', '/getBIAjaxCurrTxt',dataObj)
      .respond(200, "[{ result : 'Six dollars only', msg : '' }]");		
	  
	  currencyService.getCurrencyText('6')
      .then(function(data) {
	    expect(response.data.result).toEqual("Six dollars only");
      });

    });
	
	  it('should return failure response', function () {
		  
		var search = {}
	    search["amount"] = "-1";		
		var dataObj = JSON.stringify(search);

       $httpBackend.when('POST', '/getBIAjaxCurrTxt',dataObj)
      .respond( 400, "[{ result : '', msg : 'Error: Enter Positive Number' }]");		
	  
	  currencyService.getCurrencyText('-1')
      .then(function(data) {	
	     expect(response.data.msg).toEqual("Error: Enter Positive Number");		 
      });
    });

 });
 
 
  });