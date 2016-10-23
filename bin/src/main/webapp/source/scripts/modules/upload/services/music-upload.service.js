(function(angular){
    'use strict';

    angular
        .module('mllApp.upload')
        .factory('musicUploadService', musicUploadService);

    musicUploadService.$inject = ['$http', 'musicUrl'];

    function musicUploadService($http, musicUrl) {
        return {
            submitCloud: submitCloud,
            submitDirect: submitDirect
        };

        function submitCloud (data) {
            return $http.post(musicUrl.direct, data);
        }

        function submitDirect(data, fileProp) {
            let fd = new FormData();

            Object.keys(data).forEach((key) =>
                fd.append(key, (key === fileProp) ? data[key] : JSON.stringify(data[key])));

            return $http.post(musicUrl.cloud, fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
        }
    }

})(window.angular);