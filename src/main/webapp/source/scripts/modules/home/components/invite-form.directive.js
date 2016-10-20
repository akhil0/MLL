(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .directive('mllInviteForm', mllInviteForm);

    function mllInviteForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'InviteFormController',
            controllerAs: 'ctrl',
            templateUrl: 'invite-form.template.html',
            bindToController: {
                userId: '@'
            }
        };
    }
})(window.angular);

