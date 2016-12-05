 
(function(angular){
	'use strict';
    angular
        .module("mllApp.home")
        .controller("MusicianProfilePageController", MusicianProfilePageController);
    
    MusicianProfilePageController.$inject = ['$scope', '$state', '$location', 'musicianHomePageSerivce', 'authenticationService' ];

	function MusicianProfilePageController($scope, $state, $location, musicianHomePageSerivce, authenticationService ) {

		this.firstName = "Medhavi";
		this.lastName = "Mahansaria";
		this.email = "medhavi@gmail.com"

       this.authService = authenticationService;
       
       this.data = {
               userId: +this.userId,
               tracks: []
           };
       console.log("in musician profile controller");

       
    	   // SEARCH SONG
       this.search = (title) => {

    	   // new edits
    	   // ***********
    	   console.log("in search function"+title)
    	   // ***********
    	   
    	   musicianHomePageSerivce.searchSongs(title)
    	   .then((response) => {
    		   var songs = response;
    		   this.data = {
    				   userId: +this.userId,
    				   tracks: songs
    				   };
    		   })
    		   .catch((rejection) => rejection);
    	   };
 
    }
})(window.angular);
