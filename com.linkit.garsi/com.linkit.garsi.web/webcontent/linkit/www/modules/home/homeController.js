'use strict';

/* Controllers */
angular.module('HomeControllers', [])
.controller('HomeController', ['$scope','$state',
	function($scope,$state) {
		 $scope.linked =function (){
		 	$state.go('tab.traininglists')
		 }
	}
]);