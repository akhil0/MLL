(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .directive('mllMusicAgreementForm', mllMusicAgreementForm);

    function mllMusicAgreementForm() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'MusicAgreementFormController',
            controllerAs: 'ctrl',
            templateUrl: 'music-agreement-form.template.html'
        };
    }
})(window.angular);