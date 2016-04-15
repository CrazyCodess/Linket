'use strict';

/* Controllers */
angular.module('PersonalInfoControllers', ['PersonalInfoServices'])
.controller('PersonalListController', ['$scope', 'PersonalListService',
	function($scope, PersonalListService) {
		$scope.users = {};
		 PersonalListService.query(function(data){
		 	$scope.users = {
		 		list: data,
		 		total: data.length,
		 		pageSize : data.length/10
		 	};
		 });
	}
])
;