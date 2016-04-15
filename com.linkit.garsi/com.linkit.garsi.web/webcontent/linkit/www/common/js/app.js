// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('rossmobile', [
'ionic',
'HomeControllers',
'TrainingControllers',
'SettingControllers'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
})

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

  // setup an abstract state for the tabs directive
    .state('tab', {
    url: '/tab',
    abstract: true,
    templateUrl: 'modules/nav/tabs.html'
  })
    
    .state('tab.home', {
    url: '/home',
    views: {
        'tab-home': {
          templateUrl: "modules/home/home.html",
          controller: "HomeController"
        }
      }
  })

  // Each tab has its own nav history stack:
   .state('tab.traininglists', {
      url: "/traininglists",
      views: {
        'tab-home': {
          templateUrl: "modules/training/trainingList.html",
          controller: 'TrainingListController'
        }
      }
    })

  .state('tab.trainingSingle', {
    url: "/traininglists/:traininglistId",
    views: {
      'tab-home': {
        templateUrl: "modules/training/trainingDetail.html",
        controller: 'TrainingDetailController'
      }
    }
  })
  .state('tab.collect', {
    url: '/collect',
    views: {
      'tab-collect': {
        templateUrl: 'modules/collect/collect.html'
      }
    }
  })
  .state('tab.setting', {
    url: '/setting',
    views: {
      'tab-setting': {
        templateUrl: 'modules/setting/setting.html',
        controller:'SettingController'
      }
    }
  });

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/tab/home');;
});
