
(function(angular){
	'use strict';
    angular
        .module("mllApp.sidebar")
        .controller("SidebarController", SidebarController);
    
	    SidebarController.$inject = [ '$state', '$location', 'authenticationService' ];

	function SidebarController($state, $location, authenticationService ) {

       this.authService = authenticationService;
       var userId = this.authService.details.data.id;
        this.home = function(){
        	console.log(this.authService.details.data.type);
            $state.go(this.authService.details.data.type, { id: this.authService.details.data.id });
        };
        
		this.invite = function(){
            $state.go("invite", { id: userId});        	
		};
		
		this.track = function(){
            $state.go("track", { id: userId});        	
		};		
				this.about = function(){
            $state.go("about");        	
        };
        
    }
})(window.angular);