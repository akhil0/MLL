(function (angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .directive('mllScrollableAgreement', mllScrollableAgreement);

    function mllScrollableAgreement() {
        return {
            restrict: 'AE',
            transclude: true,
            scope: {},
            controller: 'ScrollableAgreementController',
            controllerAs: 'ctrl',
            bindToController: {
                onAgree: '&'
            },
            templateUrl: 'scrollable-agreement.template.html',
            link: link
        };

        function link(scope, elem, attrs, ctrl) {
            let doc = elem.find('.agreement__document');

            doc.on('scroll', onScroll);

            function onScroll() {
                let ratio = ($(this).prop('scrollTop') + $(this).prop('offsetHeight')) / $(this).prop('scrollHeight');

                if (ratio === 1) {
                    scope.$apply(() => { ctrl.isScrolled = true; });
                }
            }
        }
    }
})(window.angular);