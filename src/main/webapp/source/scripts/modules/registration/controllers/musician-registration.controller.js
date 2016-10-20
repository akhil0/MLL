(function(angular){
    'use strict';

    angular
        .module('mllApp.registration')
        .controller('MusicianRegistrationController', MusicianRegistrationController);

    MusicianRegistrationController.$inject = ['token'];

    function MusicianRegistrationController(token) {
        this.data = { inviteToken: token };
    }
})(window.angular);