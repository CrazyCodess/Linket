'use strict';

/* Services */
angular.module('PersonalInfoServices', ['ngResource', 'commonServices'])

.factory('PersonalListService', ['$resource', 'configData',
	function($resource, configData) {
		var _userInfoUrl = configData.basePath + 'data/personalInfo/personalList.json';
		return $resource(_userInfoUrl, {}, {});
	}
]);