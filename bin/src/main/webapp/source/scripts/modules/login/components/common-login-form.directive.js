(function (angular) {
    'use strict';

    angular
        .module('mllApp.login')
        .directive('mllCommonLoginForm', mllCommonLoginForm);

    function mllCommonLoginForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'CommonLoginFormController',
            controllerAs: 'ctrl',
            templateUrl: 'common-login-form.template.html'
        };
    }
})(window.angular);