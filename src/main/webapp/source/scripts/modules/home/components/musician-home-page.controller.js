 
(function(angular){
	'use strict';
    angular
        .module("mllApp.home")
        .controller("MusicianHomeController", MusicianHomeController);
    
    MusicianHomeController.$inject = ['$scope', '$state', '$location', 'musicianHomePageSerivce', 'authenticationService' ];

	function MusicianHomeController($scope, $state, $location, musicianHomePageSerivce, authenticationService ) {

       this.authService = authenticationService;
       this.message = null;
       
       this.data = {
               userId: +this.userId,
               tracks: []
           };
       
       this.sortType = 'track';
       this.sortReverse = false;
       $scope.isCollapsed = false;
       musicianHomePageSerivce.getSongs()
       .then((response) => {
    	   var songs = response;
    	   this.data = {
                   userId: +this.userId,
                   tracks: songs
               };
       })
       .catch((rejection) => rejection);
       
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
       
       this.deleteSong = (assetId) => {
    	   this.message = null;
   	   musicianHomePageSerivce.deleteSong(assetId)
   	   .then((response) => {
   		   var res = response;
   		   console.log(response);
   		   if( res === "Asset(s) have been removed successfully") {
   		   //if( res.indexOf("success") >= 0) {
   			   this.isDeleted = true;
   			   this.message = res;
   		   }
   		   else if( res === "failure") {
   			   this.isDeleted = false;
   			   this.message = "There was some error while deteling the song. Please try again.";
   		   }
   		   else {
   			   this.isDeleted = false;
   			   this.message = res;
   		   }
   		   })
   		   .catch((rejection) => rejection);
   	   }
    }
})(window.angular);
