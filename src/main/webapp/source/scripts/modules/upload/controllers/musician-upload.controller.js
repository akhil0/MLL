(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicianUploadController', MusicianUploadController);

    MusicianUploadController.$inject = ['userId'];

    function MusicianUploadController(userId) {
        this.userId = userId;
    }
})(window.angular);