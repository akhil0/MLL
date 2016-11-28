(function(angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .factory('musicianHomePageSerivce', musicianHomePageSerivce);

    musicianHomePageSerivce.$inject = ['$http', 'musicianUrl', 'searchUrl'];

    function musicianHomePageSerivce($http, musicianUrl, searchUrl) {
        return {
            getSongs: getSongs,
            searchSongs: searchSongs
        };

        function getSongs() {
        	return $http.post(musicianUrl)
                .then((response) => {
                    return response.data;
                })
                .catch((rejection) => rejection);
        }
        
        function searchSongs(searchTitle) {
        	console.log("in service");
        	console.log(searchTitle);
        	var mSearchUrl = searchUrl + searchTitle;
        	return $http.post(mSearchUrl)
                .then((response) => {
                	console.log("after servelet");
                	console.log(response);
                    return response.data;
                })
                .catch((rejection) => rejection);
        }
    }
})(window.angular);