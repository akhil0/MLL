(function(angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .factory('arHomePageSerivce', arHomePageSerivce);

    arHomePageSerivce.$inject = ['$http', 'authenticationService'];

    function arHomePageSerivce($http, authenticationService) {
        return {
            getRegisteredMusician: getRegisteredMusician
        };

        function getRegisteredMusician(userId) {
        	
        	console.log("AYA");
        	console.log('/MLL/ARHomePageServlet/' + userId);
        	var arUrl = '/MLL/ARHomePageServlet/?userId=' + userId;
            return $http.get(arUrl);
        }
    }
})(window.angular);