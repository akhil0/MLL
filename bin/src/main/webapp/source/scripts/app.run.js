(function(angular) {
    'use strict';

    angular
        .module('mllApp')
        .run(run);

    run.$inject = ['authenticationService'];

    function run(authenticationService) {
        authenticationService.check();
    }
})(window.angular);