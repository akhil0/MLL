(function (angular){
    'use strict';

    angular
        .module('mllApp.arhome')
        .directive('mllArhome', mllArhome);

    function mllArhome() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'ArhomeController',
            controllerAs: 'ctrl',
            templateUrl: 'arhome.template.html'
        };
    }
})(window.angular);