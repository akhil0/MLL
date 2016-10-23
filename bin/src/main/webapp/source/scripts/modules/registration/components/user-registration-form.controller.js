
(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .controller('UserRegistrationFormController', UserRegistrationFormController);

    UserRegistrationFormController.$inject =
        [
            '$state', 'registrationService', 'musicGenres', 'userGenders',
            'universityColleges', 'universityAffiliations', 'registrationTypes'
        ];

    function UserRegistrationFormController($state, regService, genres, genders, colleges, affiliations, regTypes) {
        this.genres = genres;
        this.genders = genders;
        this.colleges = colleges;
        this.affiliations = affiliations;

        this.data = {
            type: regTypes.user,
            token: this.inviteToken
        };

        this.register = () => {
            if (this.registrationForm.$invalid) this.registrationForm.$submitted = true;

            else
                regService.register(this.data)
                    .then((response) => this.processResponse(response))
                    .catch((rejection) => this.displayError(rejection));
        };

        this.processResponse = (data) => {
            if (data.isRegistered) this.redirect(data.userId);

            else this.displayError(data.errorMessage);
        };

        this.redirect = (id) => $state.go(regTypes.user, { id: id });

        this.displayError = (errorMessage) => {
            this.registrationForm.$serverError = true;
            this.errorMessage = errorMessage;
        };
    }
})(window.angular);