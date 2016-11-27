(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .controller('MusicAgreementFormController', MusicAgreementFormController);

    function MusicAgreementFormController() {
        this.form = { invalid: true, submitted: false };

        this.validate = (isChecked) => this.form.invalid = !isChecked;
        console.log(this);
})(window.angular);