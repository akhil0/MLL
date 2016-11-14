
(function(angular){
	'use strict';
    angular
        .module("mllApp.sidebar")
        .controller("SidebarController", SidebarController);
    
	    SidebarController.$inject = ['$scope', '$state', '$location', 'authenticationService' ];

	function SidebarController($scope, $state, $location, authenticationService ) {

       this.authService = authenticationService;
       console.log("ID  " + this.authService.details.data.id);
       var userId = this.authService.details.data.id;
        this.home = function(){
            $state.go("home", { id: userId });        	
        };
        
		this.invite = function(){
            $state.go("user", { id: userId});        	
		};
		
		this.track = function(){
			console.log("track CALLED");
            $state.go("track", { id: userId});        	
		};		
    }
})(window.angular);