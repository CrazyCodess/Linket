'use strict';

/* Controllers */
angular.module('LoginControllers', ['LoginServices'])
.controller('loginController', ['$scope','UserService',
  function($scope,UserService) {
    $scope.welcomeInfo = '欢迎登录ROSS系统';
    $scope.users = UserService.query();
    $scope.username = '1231231231';
}]);

