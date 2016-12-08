(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .directive('mllBandAddProfile', mllBandAddProfile);

    function mllBandAddProfile() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'BandAddPageController',
            controllerAs: 'ctrl',
            templateUrl: 'band-add-page.template.html',
            bindToController: {
                userId: '@'
            }
        };
    }

})(window.angular);
