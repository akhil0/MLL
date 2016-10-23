(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .directive('mllHddFileReader', mllHddFileReader);

    function mllHddFileReader() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'HddFileReaderController',
            controllerAs: 'ctrl',
            bindToController: {
                onSelect: '&'
            },
            templateUrl: 'hdd-file-reader.template.html',
            link: _link
        };

        function _link(scope, elem, attrs, ctrl) {
            let input = elem.find('input[type="file"]');

            input.on('change', (e) => {
                scope.$apply(() => ctrl.change(e));
            });
        }
    }
})(window.angular);