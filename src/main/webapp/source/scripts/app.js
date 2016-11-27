(function (angular) {
    'use strict';

    angular.module('mllApp',
        [
            'mllApp.shared', 'mllApp.header', 'mllApp.sidebar','mllApp.arhome', 'mllApp.footer', 'mllApp.home', 'mllApp.login', 'mllApp.registration',
            'mllApp.upload', 'ui.router', 'ui.grid'
        ]);
})(window.angular);