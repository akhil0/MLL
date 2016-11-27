(function(angular) {
    'use strict';

    angular
        .module('mllApp.arhome')
        .factory('arHomeSerivce', arHomeSerivce);

    arHomeSerivce.$inject = ['$http', 'authenticationService'];

    function arHomeSerivce($http, authenticationService) {
        return {
        	getAllPlaylists: getAllPlaylists,
            add: add
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
    }
})(window.angular);