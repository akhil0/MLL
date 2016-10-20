(function(angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .factory('registrationService', registrationService);

    registrationService.$inject = ['$http', 'authenticationService', 'registrationUrl'];

    function registrationService($http, authenticationService, registrationUrl) {
        return {
            register: register
        };

        function register(data) {
            return $http.post(registrationUrl, data)
                .then((response) => {
                    if (response.data.isRegistered) authenticationService.change(response.data);

                    return response.data;
                })
                .catch((rejection) => rejection);
        }
    }
})(window.angular);