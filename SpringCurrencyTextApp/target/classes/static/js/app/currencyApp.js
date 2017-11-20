'use strict';

var currTextApp=angular.module('currTextApp',[]);

currTextApp.constant('urls', {
    BASE: 'http://localhost:8080/',
    CURRENCY_SERVICE_API : 'http://localhost:8080/getAjaxCurrTxt/',
    CURRENCY_BI_SERVICE_API : 'http://localhost:8080/getBIAjaxCurrTxt/'
});
