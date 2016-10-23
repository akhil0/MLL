(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .controller('HddFileReaderController', HddFileReaderController);

    function HddFileReaderController() {
        this.change = (e) => {
            let file = e.target.files[0];

            this.onSelect({ fileInformation: { isDirect: true, file: file } });
        };
    }
})(window.angular);
