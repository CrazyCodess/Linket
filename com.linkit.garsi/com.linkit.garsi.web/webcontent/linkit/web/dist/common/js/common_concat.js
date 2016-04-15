/*!
 * ross - JS for Debug
 * @licence ross - v1.0.0 (2015-08-05)
 * http://blog.csdn.net/jennieji | Licence: MIT
 */
/*! ross */
"use strict";angular.module("PingAnApp",["ui.router","PingAnControllers","PingAnFilters","LoginControllers","PersonalInfoControllers"]).config(["$stateProvider","$urlRouterProvider","$httpProvider",function(a,b,c){a.state("login",{url:"/login",controller:"loginController",templateUrl:"modules/login/login.html"}).state("personalInfo",{url:"/personalInfo",templateUrl:"modules/personalInfo/personalInfo.html"}).state("personalInfo.lists",{url:"/lists",views:{list:{templateUrl:"modules/personalInfo/personalList.html",controller:"PersonalListController"},search:{templateUrl:"modules/personalInfo/personalSearch.html"}}}).state("personalInfo.detail",{url:"/detail/{contactId:[0-9]{1,4}}",templateUrl:"modules/personalInfo/personalDetail.html"}).state("welcome",{url:"/",templateUrl:"common/templates/welcome.html"})}]).run(["$rootScope","$state","$stateParams",function(a,b,c){a.$state=b,a.$stateParams=c}]);
/*! ross */
"use strict";var pingAnControllers=angular.module("PingAnControllers",[]);pingAnControllers.controller("demoController",["$scope",function(a){a.title="ROSSSSS"}]);
/*! ross */
"use strict";
/*! ross */
"use strict";angular.module("PingAnFilters",[]).filter("checkmark",function(){return function(a){return a?"✓":"✘"}});
/*! ross */
"use strict";angular.module("commonServices",[]).constant("configData",{basePath:"http://127.0.0.1:8020/demo/web/"});
/*! ross */
"use strict";function testFunc(){return 2}var moduleTest={additive:function(a,b){return a+b},multiplicative:function(a,b){return a*b},subtraction:function(a,b){return a-b}};