(function(angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .factory('authenticationService', authenticationService);

    authenticationService.$inject = ['$cookies', 'authDetailsService', 'authDetailsKey'];

    function authenticationService($cookies, authDetailsService, authDetailsKey) {
        return {
            details: authDetailsService,
            clear: clear,
            change: change,
            check: check
        };

        function check() {
            let authDetails = $cookies.getObject(authDetailsKey);

            if (authDetails) this.details.init(authDetails.data);
        }

        function clear() {
            $cookies.remove(authDetailsKey);

            this.details.clear();
        }

        function change(data) {
            this.details.change(data);

            $cookies.putObject(authDetailsKey, this.details);
        }
    }
})(window.angular);