(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('BandAddPageController', BandAddPageController);
    
    BandAddPageController.$inject = ['musicContributions', 'musicianRoles', 'bandData', 'musicianProfilePageSerivce'];

    function BandAddPageController(musicContributions, musicianRoles, bandData, musicianProfilePageSerivce) {

    	this.musicContributions = angular.copy(musicContributions);
    	this.musicianRoles = angular.copy(musicianRoles);
    	this.data = angular.copy(bandData);
    	
        this.addWriter = () => this.data.songwriters.push({
            name: '', primaryPhone: '', secondaryPhone: '', primaryEmail: '', secondaryEmail: '', contribution:'', ownershipPercent: 0, musicianRole:''
        });

        this.selectContribution = (contribution) => { if(!contribution) this.data.contribution = null; };
        
        this.selectRole = (role) => { if(!role) this.data.musicianRole = null; };
        
        this.removeWriter = (i) => this.data.songwriters.splice(i, 1);
        this.submit = () => {
        	musicianProfilePageSerivce.addBand(this.data);
        };
    }
})(window.angular);