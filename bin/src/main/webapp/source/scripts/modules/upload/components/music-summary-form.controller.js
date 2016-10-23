(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicSummaryFormController', MusicSummaryFormController);

    MusicSummaryFormController.$inject = ['$state', 'authenticationService'];

    function MusicSummaryFormController($state, authenticationService) {
        this.authService = authenticationService;

        this.quit = () => {
            $state.go(this.authService.details.data.type, { id: this.authService.details.data.id });
        };
    }
})(window.angular);