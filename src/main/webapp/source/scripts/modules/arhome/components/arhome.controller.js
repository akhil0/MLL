(function (angular){
     'use strict';
 
     angular
         .module('mllApp.arhome')
         .controller('ArhomeController', ArhomeController);
 
     ArhomeController.$inject =
         ['$scope',  'authenticationService', '$stateParams','arHomeSerivce'];
 
     function ArhomeController($scope,  authenticationService, $stateParams, arHomeSerivce) {
 
	    this.authService = authenticationService;
	 
	    var userId = $stateParams.id; 
	    var ctrl = this;	

	    ctrl.new_playlist_data1 = true;
	    
	    ctrl.add = function() {
	    	if(ctrl.input){
		    	arHomeSerivce.add(userId,ctrl.input).success(function(response){
		    		ctrl.myList = response.playlists;

		    	})    		
	    	}else{
	    		ctrl.responseMessage = "Give a name for playlist";
	    	}
	    
	    	ctrl.input = null;
	    	ctrl.new_playlist_data = false;
	    	ctrl.new_playlist_data1 = true;  
	     };
	     
	     ctrl.new_playlist_data = false;
	     ctrl.addplaylist = function(){
	    	 ctrl.new_playlist_data = true;
	    	 ctrl.new_playlist_data1 = false;
	     };
	     
	     function getAllPlaylists(){
	    	 arHomeSerivce.getAllPlaylists(userId).success(function(response){
		    	ctrl.myList = response.playlists;	    		
	    	 })	    	 
	     }
	     
    	 getAllPlaylists();
    	 
    	 
    	 ctrl.getPlayListId = function (id){
    		 console.log("PLAYLIST ID " + id)
    	 }
 	}
})(window.angular);