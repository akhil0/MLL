(function (angular){
    'use strict';

    angular
        .module('mllApp.sidebar')
        .directive('mllSidebar', mllSidebar);

    function mllSidebar() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'SidebarController',
            controllerAs: 'ctrl',
            templateUrl: 'sidebar.template.html'
        };
    }

})(window.angular);