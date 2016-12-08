(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicOwnerInformationFormController', MusicOwnerInformationFormController);
    
    MusicOwnerInformationFormController.$inject = ['musicContributions', 'musicianRoles', 'authenticationService', 'musicianProfilePageSerivce'];

    function MusicOwnerInformationFormController(musicContributions, musicianRoles, authenticationService, musicianProfilePageSerivce) {

    	this.musicContributions = angular.copy(musicContributions);
    	this.musicianRoles = angular.copy(musicianRoles);
    	this.authService = authenticationService;
    	this.bands = this.authService.bands;
    	console.log("medhavi");
    	console.log(this.bands);
    	console.log(this.authService);
    	this.selectedBand = null;
    	
        this.addWriter = () => this.data.songwriters.push({
            name: '', primaryPhone: '', secondaryPhone: '', primaryEmail: '', secondaryEmail: '', contribution:'', ownershipPercent: 0, musicianRole:''
        });

        this.selectContribution = (contribution) => { if(!contribution) this.data.contribution = null; };
        
        this.selectRole = (role) => { if(!role) this.data.musicianRole = null; };
        
        this.removeWriter = (i) => this.data.songwriters.splice(i, 1);
        this.submit = () => {
            if (this.ownerForm.$invalid) {
            	this.ownerForm.$submitted = true;
            }
            else this.onNext();
        };

        this.reset = () => this.onPrevious();
        
        this.getBandDetails = (band) => {
        	console.log("MusicOwnerInformationFormController");
        	console.log(band);
        	//musicianProfilePageSerivce.
        };

    }
})(window.angular);
