angular.module('myApp.taskservice', []).
  factory('taskservice', function ($http,$q) {

    var BASE = "http://localhost:9080/api/v1/task-";
    var api = {};

    api.getTasks = function () {
      var request = $http({
        method: "get",
        url: BASE + 'list',
        /*params: {
          action: "delete"
        },
        data: {
          id: id
        }*/
      });
      return (request.then(handleSuccess, handleError));
    }

    api.getDrivers2 = function () {

      return $http({
        method: 'JSONP',
        url: 'http://ergast.com/api/f1/2013/driverStandings.json?callback=JSON_CALLBACK'
      });
      /*taskservice.getDrivers2().success(function (response) {
        console.log("Drivers getted2");
        console.log(response.MRData.StandingsTable.StandingsLists[0].DriverStandings);
        //$scope.driversList = response.MRData.StandingsTable.StandingsLists[0].DriverStandings;
      });*/
    }


    // ---
    // PRIVATE METHODS.
    // ---
    // I transform the error response, unwrapping the application dta from
    // the API response payload.
    function handleError(response) {
      // The API response from the server should be returned in a
      // nomralized format. However, if the request was not handled by the
      // server (or what not handles properly - ex. server error), then we
      // may have to normalize it on our end, as best we can.
      if (
        !angular.isObject(response.data) ||
        !response.data.message
      ) {
        return ($q.reject("An unknown error occurred."));
      }
      // Otherwise, use expected error message.
      return ($q.reject(response.data.message));
    }


    // I transform the successful response, unwrapping the application data
    // from the API response payload.
    function handleSuccess(response) {
      return (response.data);
    }

    return api;
  });