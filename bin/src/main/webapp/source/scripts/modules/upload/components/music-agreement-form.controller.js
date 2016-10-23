(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicAgreementFormController', MusicAgreementFormController);

    function MusicAgreementFormController() {
        this.form = { invalid: true, submitted: false };

        this.validate = (isChecked) => this.form.invalid = !isChecked;

        this.submit = () => {
            if (this.form.invalid) this.form.submitted = true;
            else this.onNext();
        };
    }
})(window.angular);