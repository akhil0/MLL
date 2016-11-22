(function(angular) {
	'use strict';

	angular.module('mllApp.home')
	.controller('MusicianFeaturesController', MusicianFeaturesController);

	MusicianFeaturesController.$inject = [ 'userId', '$scope', 'musicianUrl', '$http' ];

	function MusicianFeaturesController(userId, $scope) {
		this.userId = userId;
		this.songs = [];
	}
})(window.angular);