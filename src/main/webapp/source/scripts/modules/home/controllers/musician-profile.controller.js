(function(angular) {
	'use strict';

	angular.module('mllApp.home')
	.controller('MusicianProfileController', MusicianProfileController);

	MusicianProfileController.$inject = [ 'userId', '$scope', 'musicianUrl', '$http' ];

	function MusicianProfileController(userId, $scope) {
		this.userId = userId;
	}
})(window.angular);