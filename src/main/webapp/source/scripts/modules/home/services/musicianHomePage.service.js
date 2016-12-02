(function(angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .factory('musicianHomePageSerivce', musicianHomePageSerivce);

    musicianHomePageSerivce.$inject = ['$http', 'musicianUrl', 'searchUrl', 'deleteUrl'];

    function musicianHomePageSerivce($http, musicianUrl, searchUrl, deleteUrl) {
        return {
            getSongs: getSongs,
            searchSongs: searchSongs,
            deleteSong: deleteSong
        };

        function getSongs() {
        	return $http.post(musicianUrl)
                .then((response) => {
                    return response.data;
                })
                .catch((rejection) => rejection);
        }
        
        function searchSongs(searchTitle) {
        	var mSearchUrl = searchUrl + searchTitle;
        	return $http.post(mSearchUrl)
                .then((response) => {
                    return response.data;
                })
                .catch((rejection) => rejection);
        }
        
        function deleteSong(assetId) {
        	var mDeleteUrl = deleteUrl + assetId;
        	return $http.post(mDeleteUrl)
                .then((response) => {
                	console.log(response);
                    return response;
                })
                .catch((rejection) => rejection);
        }
    }
})(window.angular);