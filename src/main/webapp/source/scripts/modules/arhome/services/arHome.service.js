(function(angular) {
    'use strict';

    angular
        .module('mllApp.arhome')
        .factory('arHomeSerivce', arHomeSerivce);

    arHomeSerivce.$inject = ['$http', 'authenticationService'];

    function arHomeSerivce($http, authenticationService) {
        return {
        	getAllPlaylists: getAllPlaylists,
            add: add,
            deletePlayList : deletePlayList
        };

        function getAllPlaylists(userId) {
        	var playListUrl = '/MLL/PlaylistReferenceServlet/?userId=' + userId + '&actionType=get';
        	console.log($http.get(playListUrl));
            return $http.get(playListUrl);
        }        
        
        function add(userId,playlistName) {
        	var playListUrl = '/MLL/PlaylistReferenceServlet/?userId=' + userId + '&actionType=add&playlistName=' + playlistName;
            return $http.get(playListUrl);
        }
        
        function getSongsInPlaylist(playlistId) {
        	var playListUrl = '/MLL/PlaylistServlet/?playlistId=' + playlistId;
//            return $http.get(playListUrl);
            return null;
        }
        
        function deletePlayList(playlistId) {
        	console.log("DELETE  " + playlistId)
        	var playlistUrl = '/MLL/PlaylistReferenceServlet/?playlistId=' + playlistId + '&actionType=delete';
        	console.log(playlistUrl);
            return $http.get(playlistUrl);
        }
    }
})(window.angular);