
(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .directive('mllMusicianHomePage', mllMusicianHomePage);

    function mllMusicianHomePage() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'MusicianHomeController',
            controllerAs: 'ctrl',
            templateUrl: 'musician-home.template.html',
            bindToController: {
                userId: '@'
            }
        };
    }

})(window.angular);