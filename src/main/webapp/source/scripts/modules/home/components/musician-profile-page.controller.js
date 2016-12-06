 
(function(angular){
	'use strict';
    angular
        .module("mllApp.home")
        .controller("MusicianProfilePageController", MusicianProfilePageController);
    
    MusicianProfilePageController.$inject = ['$scope', '$state', '$location', 'musicianProfilePageSerivce', 'authenticationService' ];

	function MusicianProfilePageController($scope, $state, $location, musicianProfilePageSerivce, authenticationService ) {

//		this.firstName = "Medhavi";
//		this.lastName = "Mahansaria";
//		this.email = "medhavi@gmail.com"

       this.authService = authenticationService;
       
       this.user = {
               userId: +this.userId,
               details: '',
               bands: []
           };

       this.user.details = musicianProfilePageSerivce.getProfile(this.user.userId);
//    	   .then((response) => {
//    		   var res = response;
//    		   this.user = {
//    				   userId: +this.userId,
//    				   details: res
//    				   };
//    		   })
//    		   .catch((rejection) => rejection);

       this.user.bands = musicianProfilePageSerivce.getBands(this.user.userId);
//	   .then((response) => {
//		   var res = response;
//		   this.user.bands = res;
//		   console.log(this.user);
//		   })
//		   .catch((rejection) => rejection);
 
    }
})(window.angular);
