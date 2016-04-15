'use strict';

/* Filters */

angular.module('PingAnFilters', []).filter('checkmark', function() {
  return function(input) {
    return input ? '\u2713' : '\u2718';
  };
});
