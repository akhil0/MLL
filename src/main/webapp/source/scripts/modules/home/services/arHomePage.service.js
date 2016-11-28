(function(angular) {
	'use strict';

	angular.module('mllApp.home').factory('arHomePageSerivce',
			arHomePageSerivce);

	arHomePageSerivce.$inject = [ '$http', 'authenticationService' ];

	function arHomePageSerivce($http, authenticationService) {
		return {
			getRegisteredMusician : getRegisteredMusician,
			getSongsForMusician : getSongsForMusician
		};

		function getRegisteredMusician(userId) {
			var arUrl = '/MLL/ARTrackingServlet/?userId=' + userId;
			return $http.get(arUrl);
		}

		function getSongsForMusician(folderId) {
			console.log(folderId);
			var arUrl = '/MLL/ARMusicianSongServlet/?folderId=' + folderId;
			return $http.get(arUrl);
		}
	}
})(window.angular);