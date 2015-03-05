smin.controller("entranceSignInController", function ($scope, $location, $rootScope, $http) {
  $scope.signIn = function () {
    $http.post('signIn', $scope.userLogin)
      .success(function (data, status, headers, config) {
        if (data.status === 200) {
          //alertify.success(data.message);
          $.notify(data.message, "success");
          $rootScope.userInfo = data.hit;
          $location.path("/");
        }
        else {
          $rootScope.userInfo = undefined;
          $.notify(data.message, "error");
        }
      })
      .error(function (data, status, headers, config) {
        $rootScope.userInfo = undefined;
      });
  }

  $('.form-login input').keydown(function(event){
    if(event.keyCode == 13) {
      event.preventDefault();
      $("a.loginButton").trigger("click");
      return false;
    }
  });

});

smin.controller("entranceSettingController", function ($scope, $location, $rootScope, $http) {
  $scope.update = function () {
    if ($scope.userSetting.password !== $(".confirmPassword").val()) {
      $.notify("New password & Confirm password not match.", "error",
        {globalPosition: "bottom right"}
      );
      return;
    }
    $scope.userSetting.username = $rootScope.userInfo.username;
    $http.post('setting', $scope.userSetting)
      .success(function (data, status, headers, config) {
        if (data.status === 200) {
          $rootScope.userInfo = undefined;
          //alertify.success(data.message);
          $.notify(data.message, "success");
          $location.path("/");
        }
        else {
          //alertify.error(data.message);
          $.notify(data.message, "error");
        }
      })
      .error(function (data, status, headers, config) {
        $rootScope.userInfo = undefined;
      });
  }
});

smin.controller("entranceSignOutController", function ($scope, $location, $rootScope, $http) {
  $rootScope.userInfo = undefined;
  $location.path("/");
});
