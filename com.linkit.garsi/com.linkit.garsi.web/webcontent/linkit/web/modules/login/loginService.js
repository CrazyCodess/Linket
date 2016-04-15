'use strict';

/* Services */
angular.module('LoginServices', ['ngResource','commonServices'])

.factory('UserService',['$resource','configData', function($resource,configData){
	var _url = configData.basePath+'data/login/login.json';
    return $resource(_url,{},{});
}]);

