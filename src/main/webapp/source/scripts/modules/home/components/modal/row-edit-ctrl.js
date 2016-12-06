(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('RowEditController', RowEditController);

    RowEditController.$inject = ['$stateParams', 'arHomePageSerivce', '$uibModal'];
    function RowEditController($stateParams, arHomePageService, $uibModal) {
    	var ctrl = this;
    	  ctrl.editRow = function (grid, row) {
    		    $uibModal.open({
    		      templateUrl: 'edit-modal.html',
    		      controller: 'ModalController',
    		      controllerAs: 'model',
    		      resolve: {
    		        grid: function () { return grid; },
    		        row: function () { return row; }
    		      }
    		    });
    		  }    	
    }
        
})(window.angular);