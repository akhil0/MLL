 
(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .directive('mllMusicianProfilePage', mllMusicianProfilePage);

    function mllMusicianProfilePage() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'MusicianProfileController',
            controllerAs: 'ctrl',
            templateUrl: 'musician-profile-home.template.html',
            bindToController: {
                userId: '@'
            }
        };
    }

})(window.angular);
