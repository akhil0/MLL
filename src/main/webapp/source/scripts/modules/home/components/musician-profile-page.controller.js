 
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
       var userId = this.authService.details.data.id;
       var allBands = this.authService.details.data.bands;
       this.user = {
               userId: +this.userId,
               details: '',
               bands: allBands
           };

       this.user.details = musicianProfilePageSerivce.getProfile(this.user.userId);

       //this.user.bands = musicianProfilePageSerivce.getBands(this.user.userId);
       this.user.bands = this.authService.details.data.bands;
       this.addBand = () => {
    	   $state.go("addBand", { id: userId}, {reload: true}); 
       }
    }
})(window.angular);
