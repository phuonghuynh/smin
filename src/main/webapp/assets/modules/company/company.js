//smin.controller("companyKonsoleController", function($scope, $location) {
//  //$scope.close = function() {
//  //  $location.path("/company/list");
//  //}
//});

smin.controller("companyRegistrationController", function($scope, $http, $location) {
  if ($rootScope.userInfo === undefined) {
    return;
  }
  $scope.register = function() {
    $scope.companyInfo.type = "ORPM";
    //$location.path("/company/registration-konsole");
    $scope.companyInfo.name.replace(/ /g,'');
    $http.post('company/register', $scope.companyInfo)
      .success(function (data, status, headers, config) {
        if (data.status === 200) {
          alertify.success(data.message);
          $location.path("/");
        }
        else {
          alertify.error(data.message);
        }
      })
      .error(function (data, status, headers, config) {
        alertify.error("Could not register company.");
      });
  };
});

//smin.controller("companyListController", function($scope, $http, $rootScope, $location) {
//  if ($rootScope.userInfo === undefined) {
//    return;
//  }
//  $http.get('company')
//    .success(function (data, status, headers, config) {
//      $scope.companies = data;
//      //$('.table-responsive').responsiveTable();
//    })
//    .error(function (data, status, headers, config) {
//      alertify.error("Could not get companies.");
//    });
//});