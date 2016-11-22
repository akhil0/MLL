(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('ARFeaturesController', ARFeaturesController);

    ARFeaturesController.$inject = 	['userId', 'arHomePageSerivce'];

    function ARFeaturesController(userId, arHomePageService) {

    	var model = this;
    	model.userId = userId;
    	model.getRegisteredMusician = getRegisteredMusician;
        console.log(userId);
        console.log("TRY AGAIN " + userId);
        console.log("AGAIN  "+ userId);
//        model.service = arHomePageService;
        model.flag = false;
        
        function getRegisteredMusician(){
        	arHomePageService.getRegisteredMusician(userId)
        		.success(function(object){
        			var registeredMusiciansObject = [];
        			var unRegisteredMusiciansObject = [];
        			console.log(object);
        			for(var i=0; i< object.registeredMusicians.length; i++){
        				registeredMusiciansObject.push(JSON.parse(object.registeredMusicians[i]));        				
        			}
        			
        			for(var j=0; j< object.unRegisteredMusicians.length; j++){
        				console.log(object.unRegisteredMusicians[j]);
        				unRegisteredMusiciansObject.push(JSON.parse(object.unRegisteredMusicians[j]));        				
        			}
        			
        			console.log(registeredMusiciansObject);
        			console.log(unRegisteredMusiciansObject);
        			//console.log(JSON.parse(musicians))
        			model.musicians = 	registeredMusiciansObject;
        			model.flag = true;
        		});
        }  
        
        getRegisteredMusician();
//        	() => {
    }
})(window.angular);