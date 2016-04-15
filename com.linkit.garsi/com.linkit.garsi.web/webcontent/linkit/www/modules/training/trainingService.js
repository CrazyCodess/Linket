'use strict';

/* Services */
angular.module('TrainingServices', ['ngResource', 'commonServices'])

.factory('TrainingListService', ['$resource', 'configData',
	function($resource, configData) {
		var _url = configData.basePath + 'data/training/TrainingList.json';
		return $resource(_url, {}, {});
	}	
]);