(function(angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .factory('authDetailsService', authDetailsService);

    function authDetailsService() {
        return {
            isAuth: false,
            data: {},
            init: init,
            clear: clear,
            change: change
        };

        function init(data) {
            this.isAuth = true;

            this.data = data;
        }

        function clear() {
            this.isAuth = false;

            this.data = {};
        }

        function change(data) {
            this.isAuth = true;

            this.data.id = data.userId;
            this.data.type = data.type;

            this.data.permissions = {};
            this.data.permissions.browse = data.browse;
            this.data.permissions.upload = data.upload;
        }
    }
})(window.angular);