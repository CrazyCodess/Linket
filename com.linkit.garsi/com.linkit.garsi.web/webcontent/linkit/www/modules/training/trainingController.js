'use strict';

/* Controllers */
angular.module('TrainingControllers', ['TrainingServices'])
.controller('TrainingListController', ['$scope', 'TrainingListService',
	function($scope, TrainingListService) {
		$scope.shouldShowDelete = false;
 		$scope.shouldShowReorder = false;
 		$scope.listCanSwipe = true;
		 TrainingListService.query(function(data){
		 	console.log(data);
		 	$scope.training = {
		 		list: data,
		 		total: data.length,
		 		pageSize : data.length/10
		 	};
		 });
	}
])
.controller('TrainingDetailController', ['$scope','$stateParams',function($scope, $stateParams) {
	$scope.traininglistId = $stateParams.traininglistId;
}]);