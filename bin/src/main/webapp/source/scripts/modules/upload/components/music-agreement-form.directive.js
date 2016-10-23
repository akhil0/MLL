(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .directive('mllMusicAgreementForm', mllMusicAgreementForm);

    function mllMusicAgreementForm() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'MusicAgreementFormController',
            controllerAs: 'ctrl',
            bindToController: {
                onNext: '&'
            },
            templateUrl: 'music-agreement-form.template.html'
        };
    }
})(window.angular);