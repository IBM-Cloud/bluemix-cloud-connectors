/*
 * Copyright IBM Corp. 2014
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
angular.module('cloudantApp.status', ['cloudantApp.services'])
.config(['$routeProvider', function ($routeProvider) {
  $routeProvider.otherwise({
    templateUrl: 'js/status/status.html',
    controller: 'StatusController'
  });
}])
.controller('StatusController', ['$scope', 'StatusService', function($scope, StatusService) {
  $scope.status = {};
  $scope.statuses = StatusService.query();
  $scope.submit = function() {
	StatusService.save($scope.status).$promise.then(function(savedStatus) {
	  $scope.statuses.push(savedStatus);
	  $scope.status.msg = '';
	}, function(err) {
	  console.error(err);
	});  
  };
  
  $scope.delete = function(status, index) {
    StatusService.delete({id:status.id}).$promise.then(function() {
      $scope.statuses.splice(index, 1);  
    }, function(err) {
      console.error(err);
    })
  };
}]);