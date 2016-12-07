(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('ARFeaturesController', ARFeaturesController);

    ARFeaturesController.$inject = 	['$scope', '$stateParams', 'arHomePageSerivce', 'authenticationService'];

    function ARFeaturesController($scope, $stateParams, arHomePageService, authenticationService) {

    	var model = this;
        model.authService = authenticationService;
        var userId = model.authService.details.data.id;
    	// Get userId from $stateParams
    	var userId = $stateParams.id;

    	model.registered = {};
		model.unregistered = {};
		
        function getMusicians(){
        	arHomePageService.getRegisteredMusician(userId)
        		.success(function(response){
        			model.tablePopulated = true;
        			var registeredMusiciansObject = [];
        			var unregisteredMusiciansObject = [];
        			
        			for(var i=0; i< response.registeredMusicians.length; i++){
        				model.isMusicians = true;
        				registeredMusiciansObject.push(response.registeredMusicians[i]);        				
        			}
        			
        			for(var j=0; j< response.unregisteredMusicians.length; j++){
        				model.isInvitees = true;
        				unregisteredMusiciansObject.push(response.unregisteredMusicians[j]);        				
        			}    
        			
        			model.musicians = registeredMusiciansObject;
        			model.unregisteredMusicians = unregisteredMusiciansObject;
        			
        			
        			// Grid for registered Users
        			model.registered = {
        					data : registeredMusiciansObject,
        					paginationPageSizes: [1,2,3],
        					paginationPageSize: 1,
	   	    				 enableSorting: true,
		    				 enableFiltering:true,
        					enableHiding : false,
        				    onRegisterApi: function(gridApi){
        				    	$scope.gridApi = gridApi;
        				    },
        					columnDefs: 
        					[
        					  { field: 'id', name: '', cellTemplate: 'edit-button.html', width: "30%", enableFiltering:false},
        					  { field: 'name', displayName: 'First Name', width: "40%" , cellTemplate:'<div style="color:black; margin-left:10px;">{{row.entity.name}}</div>'},
                              { field: 'age', width: "30%", enableFiltering: false, enableSorting:true}
                            ]
        			}

        			// Grid for Unregistered Users
        			model.unregistered = {
        					data : unregisteredMusiciansObject,
	   	    				 enableSorting: true,
		    				 enableFiltering:true,
        					paginationPageSizes: [1,2,3],
        					paginationPageSize: 1,
        				    onRegisterApi: function(gridApi){
        				      model.gridApi = gridApi;
        				    },
        					paginationPageSizes: [1,2,3],
        					columnDefs: 
        					[
        					  { field: 'emailId', displayName: 'Email', width: "60%"},
                              { field: 'tokenId', displayName: 'Reminder', width: "40%", cellTemplate:'<button class="btn primary" ng-click="grid.appScope.sendInvitation(row)">Send Invitation</button>', enableFiltering:false},
                            ]
        			}        		
        		});        	
        }    
       
		model.invite = function(){
            $state.go("invite", { id: userId});        	
		};
		
        $scope.sendInvitation = function(row){        	
        	arHomePageService.sendInvitation(row).success(function(response){
        	})
        }        
        
        getMusicians();
    }
})(window.angular);