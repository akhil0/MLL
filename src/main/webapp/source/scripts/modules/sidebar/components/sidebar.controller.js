
(function(angular){
	'use strict';
    angular
        .module("mllApp.sidebar")
        .controller("SidebarController", SidebarController);
    
	    SidebarController.$inject = [ '$state', '$location', 'uploadLink', 'profileLink', 'authenticationService' ];

	function SidebarController($state, $location, uploadLink, profileLink, authenticationService ) {

       this.authService = authenticationService;
       var userId = this.authService.details.data.id;
       var userType = this.authService.details.data.type;
       this.userId = userId;
       this.userType = userType;
       this.uploadLink = uploadLink;
       this.profileLink = profileLink;
       
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
			if (this.authService.details.data.type == 'musician') {
				this.authService.details.data.permissions.browse=true;
			}
            $state.go("about");        	
        };
        
        this.upload = () => {
            $state.go(uploadLink.href, {}, { reload: true });
        };
        
        this.profile = () => {
        	console.log("in sidebar controller -> profile");
        	console.log(profileLink);
        	console.log(userId);
            $state.go(profileLink.href, { id: userId });
        };
        
    }
})(window.angular);