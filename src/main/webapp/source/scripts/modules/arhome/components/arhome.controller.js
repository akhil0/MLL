(function (angular){
     'use strict';
 
     angular
         .module('mllApp.arhome')
         .controller('ArhomeController', ArhomeController);
 
     ArhomeController.$inject =
         ['$scope',  'authenticationService', '$stateParams','arHomeSerivce', '$uibModal'];
 
     function ArhomeController($scope,  authenticationService, $stateParams, arHomeSerivce, $uibModal) {
 
	    this.authService = authenticationService;
	 
	    var userId = $stateParams.id; 
	    var ctrl = this;	

	    ctrl.new_playlist_data1 = true;

        ctrl.selected ="1";
		
	    ctrl.add = function() {
	    	if(ctrl.input){
				if(ctrl.input.length > 15){
					ctrl.errormsg = "Please enter a name of less than 15 character";
					ctrl.error_message = true;
				}else {
				    arHomeSerivce.add(userId,ctrl.input).success(function(response){
						ctrl.myList = response.playlists;
					})	; 
			
					ctrl.error_message = false;
				}
	    	}else{
	    		ctrl.errormsg = "Give a name for playlist";
				ctrl.error_message = true;
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
	     
	     ctrl.deletePlayList = function(index){	    	 
	    	 arHomeSerivce.deletePlayList(index).success(function(response){
	    		 console.log(response);
			    	ctrl.myList = response.playlists;	    		
		    	 })
//	    	 ctrl.myList.splice(index, 1);
	    				 
	     };
         
		 
		  ctrl.sharePlayList = function(index){	
		           console.log(index);
		   };
	     function getAllPlaylists(){
	    	 arHomeSerivce.getAllPlaylists(userId).success(function(response){
		    	ctrl.myList = response.playlists;	    		
	    	 })	    	 
	     }
	     
    	 getAllPlaylists();
    	 
    	 
    	 ctrl.getPlayListId = function (id){
     		console.log("PLAYLIST ID " + id)
 		    $uibModal.open({
  		      templateUrl: 'single-playlist.html',
  		      controller: 'SinglePlaylistController',
  		      controllerAs: 'model',
  		      resolve: {
  		        id: function () { return id; }
  		      }
  		    });

    	 }
 	}
	
})(window.angular);