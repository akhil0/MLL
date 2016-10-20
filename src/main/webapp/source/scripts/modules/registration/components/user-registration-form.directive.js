
(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .directive('mllUserRegistrationForm', mllUserRegistrationForm);

    function mllUserRegistrationForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'UserRegistrationFormController',
            controllerAs: 'ctrl',
            templateUrl: 'user-registration-form.template.html',
            bindToController: {
                inviteToken: '@'
            }
        };
    }
})(window.angular);