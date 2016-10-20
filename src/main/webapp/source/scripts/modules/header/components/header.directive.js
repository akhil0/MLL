(function (angular){
    'use strict';

    angular
        .module('mllApp.header')
        .directive('mllHeader', mllHeader);

    function mllHeader() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'HeaderController',
            controllerAs: 'ctrl',
            templateUrl: 'header.template.html'
        };
    }

})(window.angular);