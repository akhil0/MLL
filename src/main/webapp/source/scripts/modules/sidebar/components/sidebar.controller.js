
(function(angular){
	'use strict';
    angular
        .module("mllApp.sidebar")
        .controller("SidebarController", SidebarController);
    
	    SidebarController.$inject = [ '$state', '$location', 'uploadLink', 'authenticationService' ];

	function SidebarController($state, $location, uploadLink, authenticationService ) {

       this.authService = authenticationService;
       console.log(this.authService.details.data);
       var userId = this.authService.details.data.id;
       var userType = this.authService.details.data.type;
       this.userId = userId;
       this.userType = userType;
       this.uploadLink = uploadLink;
       
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
        
        this.upload = () => {
            $state.go(uploadLink.href, {}, { reload: true });
        };
        
    }
})(window.angular);