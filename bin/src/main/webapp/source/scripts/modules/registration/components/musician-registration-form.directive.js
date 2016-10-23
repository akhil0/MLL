
(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .directive('mllMusicianRegistrationForm', mllMusicianRegistrationForm);

    function mllMusicianRegistrationForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'MusicianRegistrationFormController',
            controllerAs: 'ctrl',
            templateUrl: 'musician-registration-form.template.html',
            bindToController: {
                inviteToken: '@'
            }
        };
    }
})(window.angular);
