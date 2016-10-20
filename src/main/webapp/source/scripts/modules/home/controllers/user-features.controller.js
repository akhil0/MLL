(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('UserFeaturesController', UserFeaturesController);

    UserFeaturesController.$inject = ['userId'];

    function UserFeaturesController(userId) {
        this.userId = userId;
    }
})(window.angular);