(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .directive('mllDropboxFileReader', mllDropboxFileReader);

    function mllDropboxFileReader() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: function() {},
            controllerAs: 'ctrl',
            bindToController: {
                formats: '=',
                onSelect: '&'
            },
            templateUrl: 'dropbox-file-reader.template.html',
            link: link
        };

        function link(scope, elem, attr, ctrl) {
            ctrl.select = () => {
                let options = {
                    success: (files) => {
                        scope.$apply(() =>
                            ctrl.onSelect({ fileInformation : { isDirect: false, file: files[0] } }));
                    },
                    linkType: 'direct',
                    multiselect: false,
                    extensions: ctrl.formats
                };

                Dropbox.choose(options);
            };
        }
    }
})(window.angular);