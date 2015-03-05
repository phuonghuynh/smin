//smin.controller("companyKonsoleController", function($scope, $location) {
//  //$scope.close = function() {
//  //  $location.path("/company/list");
//  //}
//});

smin.controller("companyRegistrationController", function ($scope, $http, $location, $rootScope) {
  if ($rootScope.userInfo === undefined) {
    return;
  }
  $scope.register = function () {
    $("#companyRegisterButton").prop("disabled", true);

    $scope.companyInfo.type = "ORPM";
    //$location.path("/company/registration-konsole");
    $scope.companyInfo.name.replace(/ /g, '');
    $http.post('company/register', $scope.companyInfo)
      .success(function (data, status, headers, config) {
        $("#companyRegisterButton").prop("disabled", false);
        if (data.status === 200) {
          //alertify.set({ delay: 10000 });
          //alertify.success(data.message);
          $.notify(data.message, "success");

          $("span:contains('Go to link')").click(function() {
            var msg = $(this).text();
            var httpIndex = $("span:contains('Go to link')").text().indexOf("http://");
            var link = $("span:contains('Go to link')").text().substring(httpIndex, msg.indexOf(" to continue"));
            window.open(link);
          });

          $location.path("/");
        }
        else {
          $.notify(data.message, "error");
        }
      })
      .error(function (data, status, headers, config) {
        $("#companyRegisterButton").prop("disabled", false);
        $.notify("Could not register company.", "error");
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