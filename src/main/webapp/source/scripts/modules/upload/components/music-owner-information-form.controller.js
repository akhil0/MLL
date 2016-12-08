(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicOwnerInformationFormController', MusicOwnerInformationFormController);
    
    MusicOwnerInformationFormController.$inject = ['musicContributions', 'musicianRoles'];

    function MusicOwnerInformationFormController(musicContributions, musicianRoles) {

    	this.musicContributions = angular.copy(musicContributions);
    	this.musicianRoles = angular.copy(musicianRoles);
    	
        this.addWriter = () => this.data.songwriters.push({
            name: '', primaryPhone: '', secondaryPhone: '', primaryEmail: '', secondaryEmail: ''
        });
        
        this.addOwner = () => this.data.soundOwners.push({
            name: '', primaryPhone: '', secondaryPhone: '', primaryEmail: '', secondaryEmail: ''
        });

        this.selectContribution = (contribution) => { if(!contribution) this.data.contribution = null; };
        
        this.selectRole = (role) => { if(!role) this.data.musicianRole = null; };
        
        this.removeWriter = (i) => this.data.songwriters.splice(i, 1);
        //***
        this.removeOwner = (i) => this.data.soundOwners.splice(i, 1);
      //***
        this.submit = () => {
            if (this.ownerForm.$invalid || this.soundForm.$invalid) {
            	this.ownerForm.$submitted = true;
            	this.soundForm.$submitted = true;//***
            }
            else this.onNext();
        };

        this.reset = () => this.onPrevious();

    }
})(window.angular);