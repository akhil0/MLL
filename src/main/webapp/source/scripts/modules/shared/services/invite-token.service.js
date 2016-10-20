(function(angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .factory('inviteTokenService', inviteTokenService);

    inviteTokenService.$inject = ['$http', 'invitationUrl', 'tokenActionTypes'];

    function inviteTokenService($http, invitationUrl, tokenActionTypes) {
        return {
            validateToken: validateToken,
            generateToken: generateToken
        };

        function validateToken(data) {
            data.actionType =  tokenActionTypes.validate;

            return $http.post(invitationUrl, data);
        }

        function generateToken(data) {
            data.actionType = tokenActionTypes.generate;

            return $http.post(invitationUrl, data);
        }
    }
})(window.angular);