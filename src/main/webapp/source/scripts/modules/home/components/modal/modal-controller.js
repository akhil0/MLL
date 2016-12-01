(function(angular){
	'use strict';
    angular
        .module("mllApp.home")
        .controller("ModalController", ModalController);
    
    ModalController.$inject = ['arHomePageSerivce', 'authenticationService', 'row'];

	function ModalController(arHomePageSerivce, authenticationService, row) {
       this.authService = authenticationService;     
  
       var model = this;
       model.sortType = 'track';
       model.sortReverse = false;
       
       model.showPlaylistColumn = false
       model.selctedPlaylist = {};
       function init(){
           getSongs();       
           getAllPlaylists();
       }
       init();
       
       function getAllPlaylists(){
    	   arHomePageSerivce.getAllPlaylists().success(function(response){
    		   console.log("ALL PLAYLISTS");
				 console.log(response.playlists);
				 model.playlists = response.playlists;
			 })	    	 
//	    	 model.playlists = ["HIP-HOP", "Classical", "Indian"];
	     }
       
       function getSongs(){
           arHomePageSerivce.getSongsForMusician(row.entity.folderId)
           .then((response) => {        	   
        	   var songs = response.data.songs;
        	   console.log(songs);
        	   console.log(songs.length);
        	   model.tracks = response.data.songs;
           })
           .catch((rejection) => rejection);
        }
       
       model.addToPlayList = function(assetId){
    	   console.log(assetId);
    	   if(model.selectedPlaylist){
    		   console.log(model.selectedPlaylist);
    		   arHomePageSerivce.addSongToPlaylist(assetId, model.selectedPlaylist.id).success(function(response){
    			   console.log(response);
    			   if(response.isValid){
    	        	   model.showPlaylistColumn = true;
    	        	   model.responseMessage = "Song added to your playlist";
    			   }else{
    				   model.responseMessage = "Song already exist in this playlist";
    			   }
	        	   model.selectedPlaylist = null;
	    	   })
    	   }else{
	    		   model.responseMessage = "Select a playlist";
	    	   }
    	   
       }
              
       model.selectPlaylist = function(playlist){
    	   model.selectedPlaylist = playlist
    	   console.log(typeof(playlist))
    	   model.selectedPlaylist = JSON.parse(playlist);
    	   console.log(model.selectedPlaylist);
    	   
       }
      }

})(window.angular);
