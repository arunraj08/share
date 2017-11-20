<!DOCTYPE html>
<html>

<head>
    <title>SpringAjax : Convert Large Amount to Text</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">    
</head>
<body>
<script src="js/lib/angular.min.js" ></script>

<div class="container" ng-app="currTextApp" ng-controller="CurrencyCtrl">
<h1>Check Writer Application</h1>

<div class="alert alert-danger" role="alert" ng-if="errorMessage">{{errorMessage}}</div>

<div class="row">

<form class="form-inline" name="currForm" ng-submit="bigIntsubmit()">
  <div class="form-group">
     <label class="control-label col-sm-4"  for="amount">Enter Amount in USD Format:</label>
     <div class="col-sm-8">
    <input type="text" class="form-control" ng-model="amount" name="amount">
    </div>    
  </div>
  <button type="submit" class="btn btn-default">Convert</button>
</form>
</div>

<div class="row"  style="margin-top: 15px;">
<div class="alert alert-success" role="alert" ng-if="successMessage">{{convertedAmount}}</div>
</div>

</div>

 <script src="js/app/currencyApp.js" ></script>
 <script src="js/app/CurrencyCtrl.js" ></script>
 <script src="js/app/CurrencyService.js" ></script>
 
</body>
</html>