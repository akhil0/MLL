(function (angular){
    'use strict';

    angular
        .module('mllApp.header')
        .controller('HeaderController', HeaderController);

    HeaderController.$inject =
        ['$state', 'homeLink', 'loginLink', 'logoutLink', 'uploadLink', 'navigationLinks', 'authenticationService'];

    function HeaderController($state, homeLink, loginLink, logoutLink, uploadLink, navLinks, authenticationService) {

        this.authService = authenticationService;

        this.homeLink = homeLink;
        this.loginLink = loginLink;
        this.logoutLink = logoutLink;
        this.uploadLink = uploadLink;

        this.navLinks = navLinks;

        this.logout = () => {
            this.authService.clear();

            $state.go(loginLink.href);
        };

        this.home = () => {
            $state.go(this.authService.details.data.type, { id: this.authService.details.data.id });
        };

        this.upload = () => {
            $state.go(uploadLink.href, {}, { reload: true });
        };
    }
})(window.angular);
