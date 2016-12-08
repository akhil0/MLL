(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicSoundInformationFormController', MusicSoundInformationFormController);

    MusicSoundInformationFormController.$inject = ['$scope', 'musicContributions', 'musicianRoles'];
    
    function MusicSoundInformationFormController($scope, musicContributions, musicianRoles) {
    	
    	this.musicContributions = angular.copy(musicContributions);
    	this.musicianRoles = angular.copy(musicianRoles);
    	
    	$scope.autoPopulate = false;
    	
        this.addOwner = () => this.data.soundOwners.push({
            name: '', primaryPhone: '', secondaryPhone: '', primaryEmail: '', secondaryEmail: ''
        });

        this.removeOwner = (i) => this.data.soundOwners.splice(i, 1);

        this.selectContribution = (contribution) => { if(!contribution) this.data.contribution = null; };
        
        this.selectRole = (role) => { if(!role) this.data.musicianRole = null; };
        
        this.submit = () => {
            if (this.soundForm.$invalid) { this.soundForm.$submitted = true; }
            else this.onNext();
        };

        this.reset = () => this.onPrevious();

    }
})(window.angular);