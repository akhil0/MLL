(function(angular){
	'use strict';
    angular
        .module("mllApp.home")
        .controller("ModalController", ModalController);
    
    ModalController.$inject = ['arHomePageSerivce', 'authenticationService', 'row','$state', '$uibModalInstance'];

	function ModalController(arHomePageSerivce, authenticationService, row, $state, $uibModalInstance) {
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
				 if(response.playlists.length > 0){
					 model.playlists = response.playlists;
					 model.playlistExist = true;   
				 }else{
					 model.playlistExist = false;   
				 }
			 })	    	 
	     }
       
       function getSongs(){
           arHomePageSerivce.getSongsForMusician(row.entity.folderId)
           .then((response) => {        	   
        	   var songs = response.data.songs;
        	   model.tracks = response.data.songs;
           })
           .catch((rejection) => rejection);
        }
       
       model.addToPlayList = function(assetId){
    	   console.log(assetId);
    	   if(model.selectedPlaylist){
    		   arHomePageSerivce.addSongToPlaylist(assetId, model.selectedPlaylist.id).success(function(response){
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
       model.addNewPlaylist = function(){
           $state.go(this.authService.details.data.type, { id: model.authService.details.data.id });
           $uibModalInstance.dismiss('cancel');
       }
              
       model.selectPlaylist = function(playlist){
    	   model.selectedPlaylist = playlist
    	   model.selectedPlaylist = JSON.parse(playlist);    	   
       }
      }

})(window.angular);
