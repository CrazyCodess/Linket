/*!
 * ross - JS for Debug
 * @licence ross - v1.0.0 (2015-08-05)
 * http://blog.csdn.net/jennieji | Licence: MIT
 */
/*! ross */
"use strict";angular.module("HomeControllers",[]).controller("HomeController",["$scope","$state",function(a,b){a.linked=function(){b.go("tab.traininglists")}}]);
/*! ross */
"use strict";angular.module("SettingControllers",[]).controller("SettingController",["$scope","$ionicModal","$timeout",function(a,b,c){a.loginData={},b.fromTemplateUrl("modules/setting/login.html",{scope:a}).then(function(b){a.modal=b}),a.closeLogin=function(){a.modal.hide()},a.login=function(){a.modal.show()},a.doLogin=function(){console.log("Doing login",a.loginData),c(function(){a.closeLogin()},1e4)}}]);
/*! ross */
"use strict";angular.module("TrainingControllers",["TrainingServices"]).controller("TrainingListController",["$scope","TrainingListService",function(a,b){a.shouldShowDelete=!1,a.shouldShowReorder=!1,a.listCanSwipe=!0,b.query(function(b){console.log(b),a.training={list:b,total:b.length,pageSize:b.length/10}})}]).controller("TrainingDetailController",["$scope","$stateParams",function(a,b){a.traininglistId=b.traininglistId}]);
/*! ross */
"use strict";angular.module("TrainingServices",["ngResource","commonServices"]).factory("TrainingListService",["$resource","configData",function(a,b){var c=b.basePath+"data/training/TrainingList.json";return a(c,{},{})}]);