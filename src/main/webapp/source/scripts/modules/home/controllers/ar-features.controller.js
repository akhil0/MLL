(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('ARFeaturesController', ARFeaturesController);

    ARFeaturesController.$inject = 	['$scope', '$stateParams', 'arHomePageSerivce'];

    function ARFeaturesController($scope, $stateParams, arHomePageService) {

    	var model = this;
        
    	var userId = $stateParams.id;
		model.registered = {};
		model.unregistered = {};
        
        function getRegisteredMusician(){
        	arHomePageService.getRegisteredMusician(userId)
        		.success(function(response){
        			var registeredMusiciansObject = [];
        			var unregisteredMusiciansObject = [];

        			for(var i=0; i< response.registeredMusicians.length; i++){
        				registeredMusiciansObject.push(response.registeredMusicians[i]);        				
        			}
        			
        			for(var j=0; j< response.unregisteredMusicians.length; j++){
        				unregisteredMusiciansObject.push(response.unregisteredMusicians[j]);        				
        			}    
        			
        			model.musicians = registeredMusiciansObject;
        			model.unregisteredMusicians = unregisteredMusiciansObject;
        			model.registered = {
        					data : registeredMusiciansObject,
        					paginationPageSizes: [1,2,3],
        					enableFiltering: true,
        				    onRegisterApi: function(gridApi){
        				    	$scope.gridApi = gridApi;
        				    },
        					columnDefs: 
        					[
        						{ field: 'id', name: '', cellTemplate: 'edit-button.html', width: "30%" },
        					  { field: 'name', displayName: 'First Name', width: "40%" , cellTemplate:'<div style="color:black">{{row.entity.name}}</div>'},
                              { field: 'age', width: "30%"}
                            ]
        			}
        			
        			model.unregistered = {
        					data : unregisteredMusiciansObject,
        					enableFiltering: true,
        				    onRegisterApi: function(gridApi){
        				      model.gridApi = gridApi;
        				    },
        					paginationPageSizes: [1,2,3],
        					columnDefs: 
        					[
        					  { field: 'emailId', displayName: 'Email', width: "60%" },
                              { field: 'tokenId', displayName: 'Send Invitation', width: "40%", cellTemplate:'<button>Send Invitation</button>'},
                            ]
        			}
        		});
        }    
        
        getRegisteredMusician();
//        	() => {
    }
})(window.angular);