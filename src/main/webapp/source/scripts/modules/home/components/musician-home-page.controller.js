 
(function(angular){
	'use strict';
    angular
        .module("mllApp.home")
        .controller("MusicianHomeController", MusicianHomeController);
    
    MusicianHomeController.$inject = ['$scope', '$state', '$location', 'musicianHomePageSerivce', 'authenticationService' ];

	function MusicianHomeController($scope, $state, $location, musicianHomePageSerivce, authenticationService ) {

       this.authService = authenticationService;
       
       this.data = {
               userId: +this.userId,
               tracks: []
           };
       
       this.sortType = 'track';
       this.sortReverse = false;
       $scope.isCollapsed = false;
       musicianHomePageSerivce.getSongs(this.userId)
       .then((response) => {
    	   var songs = response;
    	   this.data = {
                   userId: +this.userId,
                   tracks: songs
               };
    	   console.log(this.data);
       })
       .catch((rejection) => rejection);
    }
})(window.angular);
