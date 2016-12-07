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
	    ctrl.addMyPlaylistPrompt = false;
	    ctrl.notAddMyPlaylistPrompt = true;
        ctrl.selected ="1";
        
        ctrl.morePlaylist = function(){
        	ctrl.selected=2;
        	getMorePlaylists();
        }
        
	     function getMorePlaylists(){
	    	 arHomeSerivce.getMorePlaylists().success(function(response){
	    		 ctrl.datareceived = true;
	    		 ctrl.playlists = response.playlists;
	    		 ctrl.gridOptions = {
	    				 data : ctrl.playlists,
	    				 enableSorting: true,
	    				 enableFiltering:true,
	    				 enableHiding:false, 
	    				  columnDefs: [ 
	    				    { field: 'playlistName', name : 'Playlist name'},
	    				    { field: 'creationDate' , name : 'Creation Date'},
		    				{ field: '', name : 'Songs', cellTemplate: 'show-playlist-button.html', enableFiltering:false},
		    				{field: '', name: 'Add to my playlist', cellTemplate: '<button class="tomatobttn" type="button" ng-click="grid.appScope.addtoMyPlaylist(row.entity.id)" >ADD</button> ', enableFiltering:false}, 
		    				{field: '', name: 'Unshare', cellTemplate: '<button class="tomatobttn" type="button" ng-click="grid.appScope.unShare(row.entity.id)" >Un Share</button> ', enableFiltering:false}, 
	    				  ]
	    				};	    		 
	    		 ctrl.gridOptions.appScopeProvider = ctrl;
	    	 })	    	 
	     }
        
	     ctrl.addtoMyPlaylist = function(id){
	    	 console.log("ADD TO MY PLAYLIST")
	    	 arHomeSerivce.addtoMyPlaylist(id).success(function(response){
	    		if(response.isValid){
		    		ctrl.addMyPlaylistPrompt = response.isValid;	
		    		ctrl.notAddMyPlaylistPrompt = response.isValid
	    		}else{
		    		ctrl.addMyPlaylistPrompt = response.isValid;	    			
		    		ctrl.notAddMyPlaylistPrompt = response.isValid	    			
	    		}
	    		getAllPlaylists();
	    	 })
	     }
	     
	     
	     ctrl.unShare = function(id){
	    	 console.log("GET ID " + id)
	    	 arHomeSerivce.unShare(id).success(function(response){
	    		 console.log("RESPONSE");
	    		 console.log(response);
	    		 
	    		 if(response.success){
	    			 ctrl.isUnshare = true;
	    			 ctrl.tooltipResponse = "Playlist Unshared";
                     $timeout(() => ctrl.isUnshare = false, 1500);
	    			 getMorePlaylists();
	    		 }else{
	    			 ctrl.isUnshare = true;
	    			 ctrl.tooltipResponse = "Cannot share global playlist";
	    			 $timeout(() => ctrl.isUnshare = false, 1500);
	    		 }

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
					}); 
			
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
	    		 if(response.playlists.length !=0){		    			
	    			 ctrl.myList = response.playlists;
	    			 ctrl.showPlaylist = true;
	    		 }else{
	    			 ctrl.showPlaylist = false;
	    		 }
		    	 })
	     };         
		 
		  ctrl.sharePlayList = function(playlistId){
		    	 arHomeSerivce.sharePlayList(playlistId).success(function(response){
		    		 	ctrl.isOpen = true;
		    		 	ctrl.isGenerated = true;
		    		 	ctrl.tooltipResponse = "Added to your playlist";
                        $timeout(() => ctrl.isOpen = false, 1500);
			    	 })	    	 
		   };
		   
	     function getAllPlaylists(){
	    	 arHomeSerivce.getAllPlaylists(userId).success(function(response){
	    		 if(response.playlists.length !=0){		    			
	    			 ctrl.myList = response.playlists;
	    			 ctrl.showPlaylist = true;
	    		 }else{
	    			 ctrl.showPlaylist = false;
	    		 }
	    	 })	    	 
	     }
	     
    	 getAllPlaylists();
    	 
    	 // Modal for displaying songs in playlist
    	 ctrl.getSongsInPlaylist = function (id,name){
 		    $uibModal.open({
  		      templateUrl: 'single-playlist.html',
  		      controller: 'SinglePlaylistController',
  		      controllerAs: 'ctrl',
  		      resolve: {
  		        id: function () { return id; },
                        name: function () { return name; },
  		        deleteSong : function () { return true;},
  		      }
  		    });
    	 }
 	}	
})(window.angular);