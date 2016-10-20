(function(angular) {
    'use strict';

    angular
        .module('mllApp.login')
        .factory('loginService', loginService);

    loginService.$inject = ['$http', 'authenticationService', 'loginUrl'];

    function loginService($http, authenticationService, loginUrl) {
        return {
            login: login
        };

        function login(data) {
            return $http.post(loginUrl, data)
                .then((response) => {
                    if (response.data.isValidUser) authenticationService.change(response.data);

                    return response.data;
                })
                .catch((rejection) => rejection);
        }
    }
})(window.angular);