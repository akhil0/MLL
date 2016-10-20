(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .directive('mllMusicFileUploader', mllMusicFileUploader);

    function mllMusicFileUploader() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'MusicFileUploaderController',
            controllerAs: 'ctrl',
            templateUrl: 'music-file-uploader.template.html',
            bindToController: {
                userId: '='
            }
        };
    }
})(window.angular);