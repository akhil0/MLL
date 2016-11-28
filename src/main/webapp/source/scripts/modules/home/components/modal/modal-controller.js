(function(angular){
	'use strict';
    angular
        .module("mllApp.home")
        .controller("ModalController", ModalController);
    
    ModalController.$inject = ['arHomePageSerivce', 'authenticationService', 'row'];

	function ModalController(arHomePageSerivce, authenticationService, row) {
       this.authService = authenticationService;     
  
       var model = this;
       model.sortType = 'track';
       model.sortReverse = false;
       
       getSongs();       
       
       function getSongs(){
           arHomePageSerivce.getSongsForMusician(row.entity.folderId)
           .then((response) => {
        	   var songs = response.data.songs;
        	   model.tracks = response.data.songs;
           })
           .catch((rejection) => rejection);
        }
       }

})(window.angular);
