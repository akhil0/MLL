(function(angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .factory('musicianHomePageSerivce', musicianHomePageSerivce);

    musicianHomePageSerivce.$inject = ['$http', 'musicianUrl', 'searchUrl', 'deleteUrl', 'editUrl'];

    function musicianHomePageSerivce($http, musicianUrl, searchUrl, deleteUrl, editUrl) {
        return {
            getSongs: getSongs,
            searchSongs: searchSongs,
            deleteSong: deleteSong,
            editSong: editSong
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
        
        function editSong(data) {
        	let fd = new FormData();
        	
            Object.keys(data).forEach((key) =>
                fd.append(key, (key === 'file') ? data[key] : JSON.stringify(data[key])));

        	return $http.post(editUrl, data)
            .then((response) => {
            	//console.log("after servlet hit");
                //	console.log(response);
                    //return response;
                })
                .catch((rejection) => rejection);
        }
    }
})(window.angular);
