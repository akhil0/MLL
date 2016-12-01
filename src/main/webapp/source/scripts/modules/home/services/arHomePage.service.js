(function(angular) {
	'use strict';

	angular.module('mllApp.home').factory('arHomePageSerivce',
			arHomePageSerivce);

	arHomePageSerivce.$inject = [ '$http', 'authenticationService' ];

	function arHomePageSerivce($http, authenticationService) {
		return {
			getRegisteredMusician : getRegisteredMusician,
			getSongsForMusician : getSongsForMusician,
			getAllPlaylists : getAllPlaylists,
			addSongToPlaylist : addSongToPlaylist
			
		};

		function getRegisteredMusician(userId) {
			var arUrl = '/MLL/ARTrackingServlet/?userId=' + userId;
			return $http.get(arUrl);
		}

		function getSongsForMusician(folderId) {
			console.log(folderId);
			var arUrl = '/MLL/ARMusicianSongServlet/?folderId=' + folderId;
			return $http.get(arUrl);
		}
		
        function getAllPlaylists() {
        	var playListUrl = '/MLL/PlaylistReferenceServlet/?actionType=get';
            return $http.get(playListUrl);
        }   
        
        function addSongToPlaylist(assetId, playlistId) {
        	var playListUrl = '/MLL/PlaylistServlet/?assetId=' + assetId + '&playlistId=' + playlistId + '&actionType=add';
        	console.log(playListUrl)
            return $http.get(playListUrl);
        }   
	}
})(window.angular);