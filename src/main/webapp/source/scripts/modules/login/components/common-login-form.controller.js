(function (angular) {
    'use strict';

    angular
        .module('mllApp.login')
        .controller('CommonLoginFormController', CommonLoginFormController);

    CommonLoginFormController.$inject = ['$state', 'loginService'];

    function CommonLoginFormController($state, loginService) {
        this.service = loginService;

        this.login = () => {
            if (this.loginForm.$invalid) this.loginForm.$submitted = true;

            else {
                this.service.login(this.data)
                    .then((data) => {
                        this.processResponse(data);
                    })
                    .catch(() => { });
            }
        };

        this.processResponse = (data) => {
            if (data.isValidUser) this.redirect(data.userId, data.type);

            else this.displayError(data.errorMessage);
        };

        this.redirect = (id, type) => {
            $state.go(type, { id: id });
        };

        this.displayError = (errorMessage) => {
            this.loginForm.$serverError = true;
            this.errorMessage = errorMessage;
        };
    }
})(window.angular);