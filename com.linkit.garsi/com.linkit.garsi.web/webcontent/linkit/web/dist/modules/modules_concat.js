/*!
 * ross - JS for Debug
 * @licence ross - v1.0.0 (2015-08-05)
 * http://blog.csdn.net/jennieji | Licence: MIT
 */
/*! ross */
"use strict";angular.module("LoginControllers",["LoginServices"]).controller("loginController",["$scope","UserService",function(a,b){a.welcomeInfo="欢迎登录ROSS系统",a.users=b.query(),a.username="1231231231"}]);
/*! ross */
"use strict";angular.module("LoginServices",["ngResource","commonServices"]).factory("UserService",["$resource","configData",function(a,b){var c=b.basePath+"data/login/login.json";return a(c,{},{})}]);
/*! ross */
"use strict";angular.module("PersonalInfoControllers",["PersonalInfoServices"]).controller("PersonalListController",["$scope","PersonalListService",function(a,b){a.users={},b.query(function(b){a.users={list:b,total:b.length,pageSize:b.length/10}})}]);
/*! ross */
"use strict";angular.module("PersonalInfoServices",["ngResource","commonServices"]).factory("PersonalListService",["$resource","configData",function(a,b){var c=b.basePath+"data/personalInfo/personalList.json";return a(c,{},{})}]);