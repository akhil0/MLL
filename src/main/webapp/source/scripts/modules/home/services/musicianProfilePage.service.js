(function(angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .factory('musicianProfilePageSerivce', musicianProfilePageSerivce);

    musicianProfilePageSerivce.$inject = ['$http', 'addBandUrl', 'getBandDetailsUrl'];

    function musicianProfilePageSerivce($http, addBandUrl, getBandDetailsUrl) {
        return {
            getBands: getBands,
            getBandDetails: getBandDetails,
            addBand: addBand,
            getProfile: getProfile,
            editProfile: editProfile,
            editBands: editBands
        };
        
        
        function getBands(id) {
        	console.log("in getBands");
        	var bands = ['ABC', 'PQR', 'XYZ'];
        	console.log(bands);
        	return bands;
        }
        
        function getBandDetails(id) {
        	var bandUrl = getBandDetailsUrl + id;
        	return $http.post(bandUrl)
        	.then((response) => {
            	console.log("after servlet hit");
                	console.log(response);
                   // return response.data;
                })
                .catch((rejection) => rejection);
        }
        
        function addBand(data){
        	console.log("in add band in service");
        	console.log(data);
        	return $http.post(addBandUrl, data)
        	.then((response) => {
            	console.log("after servlet hit");
                	console.log(response);
                    return response.data;
                })
                .catch((rejection) => rejection);
        }
        
        function getProfile(id) {
        	console.log("in getProfile");
        	var user = {
                    username:"medhavimusic",
                	firstName:"Medhavi",
                	lastName:"Mahansaria",
                	primaryPhone:"123-123-1234",
                	secondaryPhone:"987-987-9876",
                	primaryEmail: "medhavi@gmail.com",
                	secondaryEmail: "medhavi1607@gmail.com",
                	contribution: "Lyrics",
                	ownershipPercent: 50,
                	musicianRole: "Vocal"
                };
        	console.log(user);
        	return user;
        }
        
        function editProfile() {
        	console.log("in editProfile");
        }
        
        function editBands() {
        	console.log("in editBands");
        }
//        function getBands() {
//        	return $http.post(musicianUrl)
//                .then((response) => {
//                    return response.data;
//                })
//                .catch((rejection) => rejection);
//        }
//        
//        function searchSongs(searchTitle) {
//        	var mSearchUrl = searchUrl + searchTitle;
//        	return $http.post(mSearchUrl)
//                .then((response) => {
//                    return response.data;
//                })
//                .catch((rejection) => rejection);
//        }
//        
//        function deleteSong(assetId) {
//        	var mDeleteUrl = deleteUrl + assetId;
//        	return $http.post(mDeleteUrl)
//                .then((response) => {
//                	console.log(response);
//                    return response;
//                })
//                .catch((rejection) => rejection);
//        }
//        
//        function editSong(data) {
//        	let fd = new FormData();
//        	
//            Object.keys(data).forEach((key) =>
//                fd.append(key, (key === 'file') ? data[key] : JSON.stringify(data[key])));
//
//        	return $http.post(editUrl, data)
//            .then((response) => {
//            	console.log("after servlet hit");
//                	console.log(response);
//                    //return response;
//                })
//                .catch((rejection) => rejection);
//        }
    }
})(window.angular);