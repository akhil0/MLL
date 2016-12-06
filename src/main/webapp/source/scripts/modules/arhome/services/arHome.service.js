(function(angular) {
    'use strict';

    angular
        .module('mllApp.arhome')
        .factory('arHomeSerivce', arHomeSerivce);

    arHomeSerivce.$inject = ['$http', 'authenticationService'];

    function arHomeSerivce($http, authenticationService) {
        return {
        	getAllPlaylists: getAllPlaylists,
        	getMorePlaylists: getMorePlaylists,
        	sharePlayList: sharePlayList,
            add: add,
            deletePlayList : deletePlayList,
            getSongsInPlaylist: getSongsInPlaylist,
            deleteSong: deleteSong,
            addtoMyPlaylist: addtoMyPlaylist,
        };

        
		function getMorePlaylists(){
			var playListUrl = '/MLL/PlaylistReferenceServlet/?actionType=shared';
			console.log(playListUrl);
        	console.log($http.get(playListUrl));
            return $http.get(playListUrl);
		}
		
        function getAllPlaylists(userId) {
        	var playListUrl = '/MLL/PlaylistReferenceServlet/?userId=' + userId + '&actionType=get';        	
            return $http.get(playListUrl);
        }        
        
        function sharePlayList(playlistId){
        	var playlistUrl = '/MLL/PlaylistReferenceServlet/?actionType=addToShare' + '&playlistId=' + playlistId;
        	return $http.get(playlistUrl);
        	
        }
        
        
        function add(userId,playlistName) {
        	var playListUrl = '/MLL/PlaylistReferenceServlet/?userId=' + userId + '&actionType=add&playlistName=' + playlistName;
            return $http.get(playListUrl);
        }
        
        function getSongsInPlaylist(playlistId) {
        	var playlistUrl = '/MLL/PlaylistServlet/?playlistId=' + playlistId + '&actionType=get';
            return $http.get(playlistUrl);
        }
        
        function deletePlayList(playlistId) {
        	var playlistUrl = '/MLL/PlaylistReferenceServlet/?playlistId=' + playlistId + '&actionType=delete';
            return $http.get(playlistUrl);
        }
        
        function deleteSong(playlistId, assetId){
        	var playlistUrl = '/MLL/PlaylistServlet/?playlistId=' + playlistId + '&assetId='+ assetId +'&actionType=delete';
        	return $http.get(playlistUrl);        	
        }
        
        
        function addtoMyPlaylist(playlistId){
        	var playlistUrl = '/MLL/PlaylistServlet/?playlistId=' + playlistId + '&actionType=addToMyPlaylist';
        	return $http.get(playlistUrl);
        }        
    }
})(window.angular);