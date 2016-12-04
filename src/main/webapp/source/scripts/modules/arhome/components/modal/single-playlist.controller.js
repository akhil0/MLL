(function(angular){
	'use strict';
    angular
        .module("mllApp.arhome")
        .controller("SinglePlaylistController", SinglePlaylistController);
    
    SinglePlaylistController.$inject = ['arHomeSerivce', 'authenticationService', 'id', 'deleteSong'];

	function SinglePlaylistController(arHomeSerivce, authenticationService, id, deleteSong) {
       this.authService = authenticationService;
       var model = this;
       model.sortType = 'track';
       model.sortReverse = false;
       model.deleteSong = deleteSong;       
       getSongsInPlaylist();       
       
       
       model.showPlaylist = function(){
    	   
       }
       
       
       model.deleteSong = function(assetId){
    	   console.log("PLAYLIST ID   " + id);
    	   console.log("ASSET ID   " + assetId);
    	   
    	   arHomeSerivce.deleteSong(id,assetId).success(function(response){
    		   console.log(response);
    	   })
       }
       
       function getSongsInPlaylist(){
    	   arHomeSerivce.getSongsInPlaylist(id)
           .then((response) => {
        	   console.log(response);
        	   var songs = response.data.songs;
        	   model.tracks = response.data.songs;
           })
           .catch((rejection) => rejection);
        }
       }
})(window.angular);
