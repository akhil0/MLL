(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .directive('mllMusicSummaryForm', mllMusicSummaryForm);

    function mllMusicSummaryForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'MusicSummaryFormController',
            controllerAs: 'ctrl',
            bindToController: {
                data: '=',
                onAgain: '&'
            },
            templateUrl: 'music-summary-form.template.html'
        };
    }
})(window.angular);