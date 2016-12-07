(function (angular) {
    'use strict';

    angular
        .module('mllApp.arhome')
        .controller('PlaylistModalController', PlaylistModalController);

    PlaylistModalController.$inject = ['$stateParams','$uibModal'];
    function PlaylistModalController($stateParams,  $uibModal) {
    	var ctrl = this;
    	ctrl.showPlaylist = function (grid, row) {
    			// Modal to show the songs in playlist
    		console.log(row);
    		    $uibModal.open({
    		      templateUrl: 'single-playlist.html',
    		      controller: 'SinglePlaylistController',
    		      controllerAs: 'ctrl',
    		      resolve: {
    		        id: function () { return row.entity.id; },
    		        name: function() {return row.entity.playlistName},
    		        deleteSong : function () { return false; }
    		      }
    		    });
    		  }    	
    }
        
})(window.angular);






