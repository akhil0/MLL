(function (angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .controller('ScrollableAgreementController', ScrollableAgreementController);

    function ScrollableAgreementController() {
        this.agree = () => this.onAgree({ isChecked: this.isChecked });
    }
})(window.angular);