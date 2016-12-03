(function (angular){
     'use strict';
 
     angular
         .module('mllApp.arhome')
         .controller('ArhomeController', ArhomeController);
 
     ArhomeController.$inject =
         ['$scope',  'authenticationService', '$stateParams','arHomeSerivce', '$uibModal', '$timeout'];
 
     function ArhomeController($scope,  authenticationService, $stateParams, arHomeSerivce, $uibModal, $timeout) {
 
	    this.authService = authenticationService;
	 
	    var userId = $stateParams.id; 
	    var ctrl = this;	

	    ctrl.new_playlist_data1 = true;

        ctrl.selected ="1";
		
        
        
        ctrl.morePlaylist = function(){
        	console.log("MOREEE");
        	ctrl.selected=2;
        	getMorePlaylists();
        }
        
        
        
	     function getMorePlaylists(){
	    	 arHomeSerivce.getMorePlaylists().success(function(response){
	    		 ctrl.datareceived = true;
	    		 
	    		 console.log(response.playlists);
	    		 
	    		 for(var i = 0; i < response.playlists.length; i++){
	    			 console.log(new Date(Date.parse(response.playlists[i].creationDate)));
	    			 
	    		 }
	    		 
	    		 ctrl.playlists = response.playlists;
	    		 ctrl.gridOptions = {
	    				 data : ctrl.playlists,
	    				 enableSorting: false,
	    				 enableFiltering:true,
	    				  columnDefs: [ 
	    				    { field: 'playlistName', name : 'Playlist name'},
	    				    { field: 'creationDate' , name : 'Creation Date'},
		    				{ field: '', name : 'Songs', cellTemplate: 'show-playlist-button.html', enableFiltering:false},
	    				  ]
	    		 
	    		 		
	    				};
	    	 })	    	 
	     }
        
	     

	    ctrl.add = function() {
	    	if(ctrl.input){
				if(ctrl.input.length > 15){
					ctrl.errormsg = "Please enter a name of less than 15 character";
					ctrl.error_message = true;
				}else {
				    arHomeSerivce.add(userId,ctrl.input).success(function(response){
						ctrl.myList = response.playlists;
						ctrl.showPlaylist = true;
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
	    		 if(response.playlists.length !=0){		    			
	    			 ctrl.myList = response.playlists;
	    			 ctrl.showPlaylist = true;
	    		 }else{
	    			 ctrl.showPlaylist = false;
	    		 }
		    	 })
//	    	 ctrl.myList.splice(index, 1);
	    				 
	     };
         
		 
		  ctrl.sharePlayList = function(playlistId){
			  console.log(playlistId);
		    	 arHomeSerivce.sharePlayList(playlistId).success(function(response){
		    		 	ctrl.isOpen = true;
		    		 	ctrl.isGenerated = true;
//				    	ctrl.myList = response.playlists;
                        $timeout(() => ctrl.isOpen = false, 5000);
			    	 })	    	 
		   };
	     function getAllPlaylists(){
	    	 arHomeSerivce.getAllPlaylists(userId).success(function(response){
	    		 console.log(response);
	    		 if(response.playlists.length !=0){		    			
	    			 ctrl.myList = response.playlists;
	    			 ctrl.showPlaylist = true;
	    		 }else{
	    			 ctrl.showPlaylist = false;
	    		 }
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