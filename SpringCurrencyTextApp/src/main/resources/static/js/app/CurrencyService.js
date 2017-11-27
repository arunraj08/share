'use strict';

currTextApp.factory('CurrencyService',
	    ['$http', '$q','urls',
	        function ($http, $q, urls) {

	            var factory = {
	            		getCurrencyText: getCurrencyText,
	            		getCurrencybigIntText:getCurrencybigIntText
	            };

	            return factory;
	            
	            function getCurrencyText(amt) {
	                console.log('Service : Converting Amount--');
	                
	                var search = {}
	        	    search["amount"] = amt;
	                
	                var deferred = $q.defer();	               
	                
	                $http({
	                    method : "POST",
	                    contentType: "application/json",
	             	    data: JSON.stringify(search),
	             	    dataType: 'json',        
	             	    url: "/getAjaxCurrTxt",
	                }).then(function onSuccess(response) {
	                    
	                	console.log("SUCCESS : response", response);	                	
	    	        	console.log("SUCCESS : Result", response.data.result);
	    	        	console.log("SUCCESS : Msg", response.data.msg);	 
	    	        	
	    	        	deferred.resolve(response.data);
	    	           
	                }).catch(function onError(response) {	
	                	console.log("ERROR : response", response);
	    	        	deferred.reject(response.data);
	    	           
	                });
	             
	                return deferred.promise;
	            }
	            
	            
	            function getCurrencybigIntText(amt) {
	                console.log('Service : Converting Amount--');
	                
	                var search = {}
	        	    search["amount"] = amt;
	                
	                var deferred = $q.defer();	                
	                
	                $http({
	                    method : "POST",
	                    contentType: "application/json",
	             	    data: JSON.stringify(search),
	             	    dataType: 'json',        
	             	    url: "/getBIAjaxCurrTxt",
	                }).then(function onSuccess(response) {
	                    
	                	console.log("SUCCESS : response", response);	                	
	    	        	console.log("SUCCESS : Result", response.data.result);
	    	        	console.log("SUCCESS : Msg", response.data.msg);	 
	    	        	
	    	        	deferred.resolve(response.data);
	    	           
	                }).catch(function onError(response) {	
	                	console.log("ERROR : response", response);
	    	        	deferred.reject(response.data);
	    	           
	                });
	             
	                return deferred.promise;
	            }	   
}
]);