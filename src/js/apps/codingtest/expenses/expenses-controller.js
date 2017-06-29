"use strict";

/******************************************************************************************

Expenses controller

******************************************************************************************/

var app = angular.module("expenses.controller", []);

app.controller("ctrlExpenses", ["$rootScope", "$scope", "config", "restalchemy", function ExpensesCtrl($rootScope, $scope, $config, $restalchemy) {
	
	const VAT = 0.20;
	const AMOUNT_REGEXP = "^(\\d*\\.?\\d*)(\\s*)([A-Z]{3})?$"

	// Update the headings
	$rootScope.mainTitle = "Expenses";
	$rootScope.mainHeading = "Expenses";

	// Update the tab sections
	$rootScope.selectTabSection("expenses", 0);

	var restExpenses = $restalchemy.init({ root: $config.apiroot }).at("expenses");

	$scope.dateOptions = {
		changeMonth: true,
		changeYear: true,
		dateFormat: "dd/mm/yy"
	};

	var loadExpenses = function() {
		// Retrieve a list of expenses via REST
		restExpenses.get().then(function(expenses) {
			$scope.expenses = expenses;
		});
	}

	$scope.saveExpense = function() {
		if ($scope.expensesform.$valid) {
			// Post the expense via REST
			restExpenses.post($scope.newExpense).then(function() {
				// Reload new expenses list
				loadExpenses();
			});
		}
	};

	$scope.clearExpense = function() {
		$scope.newExpense = {};
	};

	$scope.calculateVat = function(){

		//Extracting the amount and igonoring the currency
		var regexp = new RegExp(AMOUNT_REGEXP);
		var match = regexp.exec($scope.newExpense.amount);

		if(match != null && (Number(match[1])>0)){
			let amount = Number(match[1]);
			$scope.vat = Math.round((amount * VAT) * 100) / 100;
		}
		else{
			$scope.vat = null;
		}
	}

	// Initialise scope variables
	loadExpenses();
	$scope.clearExpense();
}]);
