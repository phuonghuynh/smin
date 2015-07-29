var baseUrl = (function () {
  var paths = window.location.pathname.split('/');
  paths.pop();
  return window.location.protocol + '//' + window.location.host + paths.join('/');
})();

var smin = angular.module("Smin", [
  "ngResource", "ngCookies", "ngRoute"
]);

smin.config(["$routeProvider", function ($routeProvider) {
  $routeProvider
    .when("/signIn", {
      templateUrl: "modules/entrance/signIn.html",
      controller: "entranceSignInController"
    })
    .when("/signOut", {
      templateUrl: "modules/entrance/signIn.html",
      controller: "entranceSignOutController"
    })
    .when("/company/registration", {
      templateUrl: "modules/company/registration.html",
      controller: "companyRegistrationController"
    })
    //.when("/company/registration-konsole", {
    //  templateUrl: "modules/company/registration-konsole.html",
    //  controller: "companyKonsoleController"
    //})
    .when("/entrance/setting", {
      templateUrl: "modules/entrance/setting.html",
      controller: "entranceSettingController"
    })
    //.when("/company/list", {
    //  templateUrl: "modules/company/list.html",
    //  controller: "companyListController"
    //})
    .otherwise({
      redirectTo: "/company/registration"
    });
}]);

smin.run(function ($rootScope, $location) {
  $(".leftMenu > li").click(function (e) {
    $(".leftMenu > li").removeClass("active");
    $(e.currentTarget).addClass("active");
  });
  $.notify.defaults({
    globalPosition: 'bottom right',
    autoHide: false
  });

  $rootScope.$on('$routeChangeSuccess', function (event, next, current) {
    if (!/\/signIn\//i.test($location.path())) {
      if ($rootScope.userInfo === undefined) {
        $location.path("/signIn");
      }
    }
    else if ($rootScope.userInfo !== undefined) {
      $location.path("/");
    }
  });
});

