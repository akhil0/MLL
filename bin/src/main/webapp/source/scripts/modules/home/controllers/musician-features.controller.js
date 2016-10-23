(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('MusicianFeaturesController', MusicianFeaturesController);

    MusicianFeaturesController.$inject = ['userId'];

    function MusicianFeaturesController(userId) {
        this.userId = userId;
    }
})(window.angular);