(function(angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .factory('musicianHomePageSerivce', musicianHomePageSerivce);

    musicianHomePageSerivce.$inject = ['$http', 'musicianUrl'];

    function musicianHomePageSerivce($http, musicianUrl) {
        return {
            getSongs: getSongs
        };

        function getSongs() {
        	return $http.post(musicianUrl)
                .then((response) => {
                    return response.data;
                })
                .catch((rejection) => rejection);
        }
    }
})(window.angular);