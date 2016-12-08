(function(angular) {
	'use strict';

	angular.module('mllApp.home')
	.controller('BandProfileController', BandProfileController);

	BandProfileController.$inject = [ 'userId', '$scope', 'musicianUrl', '$http' ];

	function BandProfileController(userId, $scope) {
		this.userId = userId;
	}
})(window.angular);