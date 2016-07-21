'use strict';

angular.module('myApp.view1', ['ngRoute', 'ui.bootstrap'])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view1', {
      templateUrl: 'view1/view1.html',
      controller: 'View1Ctrl'
    });
  }])

  .controller('View1Ctrl', ['$scope', 'taskservice', 'systeminfo',
    function ($scope, taskservice, systeminfo) {

      console.log("ViewControl");
      $scope.status = {
        name: "",
        description: "",
        status: "",
        subSystemsInfo: {}
      };

      $scope.error = '';

      $scope.checkStatus = function () {
        checkSystemStatus();
      };

      function checkSystemStatus() {
        systeminfo.getSystemStatus().then(
          function (info) {
            console.log(info.data);
            $scope.status = info.data;
            console.log($scope.status);
          }, function (reason) {
            handleError("SytemInfo " + reason);
          }
        );
      }


      /*taskservice.getTasks().then(
        function (tasks) {
          console.log("getTasks");
        }, function (reason) {
          handleError("Task " + reason);
        }
      );*/

      /* systeminfo.getSystemStatus().success(function (response) {
         console.log("System info status getted");
         console.log(response);
       });*/


      function handleError(reason) {
        $scope.error = reason;
      }

      checkSystemStatus();
    }]);