(function (angular) {
    'use strict';

    angular
        .module('mllApp.arhome')
        .controller('PlaylistModalController', PlaylistModalController);

    PlaylistModalController.$inject = ['$stateParams','$uibModal'];
    function PlaylistModalController($stateParams,  $uibModal) {
    	var ctrl = this;
    	ctrl.showPlaylist = function (grid, row) {
    		console.log("ROW   " + row.entity.id);
    		console.log("MODAL OPEN  ")
    		    $uibModal.open({
    		      templateUrl: 'single-playlist.html',
    		      controller: 'SinglePlaylistController',
    		      controllerAs: 'model',
    		      resolve: {
    		        id: function () { return row.entity.id; }
    		      }
    		    });
    		  }
    	
    }
        
})(window.angular);






