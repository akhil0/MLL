(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .directive('mllFileSelector', mllFileSelector);

    function mllFileSelector() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'FileSelectorController',
            controllerAs: 'ctrl',
            bindToController: {
                formats: '=',
                onSelectHdd: '&',
                onSelectDropbox: '&'
            },
            templateUrl: 'file-selector.template.html'
        };
    }
})(window.angular);