'use strict';

/* App Module */

angular.module('PingAnApp', [
	'ui.router',
	'PingAnControllers',
	'PingAnFilters',
	'LoginControllers',
	'PersonalInfoControllers'
])
.config(['$stateProvider', '$urlRouterProvider', '$httpProvider',
	function($stateProvider, $urlRouterProvider, $httpProvider) {
		$stateProvider
		.state('login', {
			url: '/login',
			controller: 'loginController',
			templateUrl: 'modules/login/login.html'
		})		
		.state('personalInfo', {
			url: '/personalInfo',
			templateUrl: 'modules/personalInfo/personalInfo.html'			
		})
				
		.state('personalInfo.lists', {
			url: '/lists',
			views:{
				'list':{
					templateUrl: 'modules/personalInfo/personalList.html',
					controller:'PersonalListController'
				},
				'search': {
					templateUrl: 'modules/personalInfo/personalSearch.html'
				}
			}
		})
		.state('personalInfo.detail', {
			url: '/detail/{contactId:[0-9]{1,4}}',
			templateUrl: 'modules/personalInfo/personalDetail.html'
		})
		
		.state('welcome', {
			url: '/',
			templateUrl: 'common/templates/welcome.html'
		})
	}
])
.run(['$rootScope', '$state', '$stateParams',
    function($rootScope, $state, $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    }
]);