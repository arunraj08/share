 describe('Testing  Currency Cntrl Controller', function () {
	
        var $scope;
        var $q;
        var deferred;
		var currencyService;
		var $controller;
        beforeEach(module('currTextApp'));
		
        beforeEach(inject(function(_$controller_, _$rootScope_, _$q_, _CurrencyService_) {			
		  currencyService = _CurrencyService_;
          $q = _$q_;
          $scope = _$rootScope_.$new();          
          deferred = _$q_.defer();
          
          spyOn(currencyService, 'getCurrencyText').and.returnValue(deferred.promise);
                   
          $controller = _$controller_('CurrencyCtrl', { 
		   currencyService: currencyService,
           $scope: $scope          
          });		  
        }));
		
		
		it('controller should be defined and validate method call with input', function() {
			
		  expect($controller).toBeDefined();
		  $scope.amount = '123';
		  $scope.submit();
		  expect(currencyService.getCurrencyText).toHaveBeenCalledWith('123' );
			
        });
  
		it('Should invoke success method', function () {
			
		  $scope.amount = '123';
		  $scope.submit();
		  deferred.resolve({msg: null, result: "One hundred twenty three dollars only"});
         
          $scope.$apply();
          		 
          expect($scope.successMessage).not.toBe(undefined);
		  expect($scope.successMessage).toContain('Text Retrieved successfully');
		  expect($scope.errorMessage).toBe('');
		  expect($scope.convertedAmount).toContain('One hundred twenty three dollars only');
		  expect($scope.done).toBe(true);
		  
        });
		
		
		it('should call Failure block', function () {

		  $scope.amount = '-1';
          deferred.reject({msg:"Error: Enter Positive Number",result:null});
		  $scope.submit();
          $scope.$apply();
          
          expect($scope.successMessage).toBe('');
		  expect($scope.errorMessage).toBe('Error: Enter Positive Number');
		  
        });

        

});