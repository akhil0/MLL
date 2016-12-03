 
(function(angular){
	'use strict';
    angular
        .module("mllApp.home")
        .controller("MusicianHomeController", MusicianHomeController);
    
    MusicianHomeController.$inject = ['$scope', '$state', '$location', 'musicGenres', 'musicianHomePageSerivce', 'authenticationService' ];

	function MusicianHomeController($scope, $state, $location, musicianHomePageSerivce, authenticationService ) {

       this.authService = authenticationService;
       //this.failureMessage = null;
       //this.successMessage = null;
       
       this.data = {
               userId: +this.userId,
               tracks: []
           };
       
       this.sortType = 'track';
       this.sortReverse = false;
       $scope.isCollapsed = false;
       $scope.isEditable = false;
       
       
    	   musicianHomePageSerivce.getSongs()
    	   .then((response) => {
    		   var songs = response;
    		   this.data = {
    				   userId: +this.userId,
    				   tracks: songs
               	};
    	   })
    	   .catch((rejection) => rejection);
       
    	   //SEARCH SONG
       this.search = (title) => {
    	   musicianHomePageSerivce.searchSongs(title)
    	   .then((response) => {
    		   var songs = response;
    		   this.data = {
    				   userId: +this.userId,
    				   tracks: songs
    				   };
    		   })
    		   .catch((rejection) => rejection);
    	   }
       
       // DELETE SONG
       this.deleteSong = (assetId) => {
    	   this.failureMessage = null;
           this.successMessage = null;
   	   musicianHomePageSerivce.deleteSong(assetId)
   	   .then((response) => {
   		   var res = response;
   		   console.log(response);
   		   if( res.data === "Asset(s) have been removed successfully") {
   			   this.isDeleted = true;
   			this.successMessage = res.data;
   		   }
   		   else if( res.data === "failure") {
   			   this.isDeleted = false;
   			this.failureMessage = "There was some error while deteling the song. Please try again.";
   		   }
   		   else {
   			   this.isDeleted = false;
   			this.failureMessage = res.data;
   		   }
   		   })
   		   .catch((rejection) => rejection);
   	   $state.reload();
   	   }
       
       // EDIT SONG
       
       this.editSong = (track) => {
    	   this.failureMessage = null;
           this.successMessage = null;
    	   musicianHomePageSerivce.editSong(track)
    	   .then((response) => {
   		   var res = response;
   		   console.log(response);
   		if( res.data === "Data Updated Successfully") {
			this.successeMessage = res.data;
		   }
		   else {
			this.failureMessage = res.data;
		   }
		   })
		   .catch((rejection) => rejection);
	   $state.reload();
       }
    }
})(window.angular);
