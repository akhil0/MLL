(function (angular) {
    'use strict';

    angular.module('mllApp',
        [
            'mllApp.shared', 'mllApp.header', 'mllApp.footer', 'mllApp.home', 'mllApp.login', 'mllApp.registration',
            'mllApp.upload', 'ui.router'
        ]);
})(window.angular);