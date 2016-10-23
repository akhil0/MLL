(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .controller('MusicianRegistrationFormController', MusicianRegistrationFormController);

    MusicianRegistrationFormController.$inject = ['$state', 'registrationService', 'registrationTypes'];

    function MusicianRegistrationFormController($state, registrationService, registrationTypes) {
        this.data = {
            type: registrationTypes.musician,
            token: this.inviteToken
        };

        this.register = () => {
            if (this.registrationForm.$invalid) this.registrationForm.$submitted = true;

            else
                registrationService.register(this.data)
                    .then((response) => this.processResponse(response))
                    .catch((rejection) => this.displayError(rejection));
        };

        this.processResponse = (data) => {
            if (data.isRegistered) this.redirect(data.userId);

            else this.displayError(data.errorMessage);
        };

        this.redirect = (id) => $state.go(registrationTypes.musician, { id: id });

        this.displayError = (errorMessage) => {
            this.registrationForm.$serverError = true;
            this.errorMessage = errorMessage;
        };
    }
})(window.angular);