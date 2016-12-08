 
(function(angular){
	'use strict';
    angular
        .module("mllApp.home")
        .controller("MusicianHomeController", MusicianHomeController);
    
    MusicianHomeController.$inject = ['$scope', '$state', '$location', 'musicData', 'musicianHomePageSerivce', 'authenticationService' ];

	function MusicianHomeController($scope, $state, $location, musicData, musicianHomePageSerivce, authenticationService ) {

       this.authService = authenticationService;

       this.editData = angular.copy(musicData);
       
       this.data = {
               userId: +this.userId,
               tracks: null
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
       
       this.prepare = (data) => {
           let obj = {
               generalInformation: data.generalInformation,
               ownershipInformation: data.ownershipInformation,
               soundInformation: data.soundInformation
           };

           return obj;
       };
       
       // EDIT SONG
       
      this.editSong = (track) => {
    	  let data = this.prepare(this.editData);
    	  

          let promise = musicianHomePageSerivce.editSong(track, 'file');

          promise.then((response) => {
              //this.data.serverInformation.message = response.data.message;
          })
          .catch((reject) => reject
          );
	   $state.reload();
       };
    }
})(window.angular);
