(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .controller('FileSelectorController', FileSelectorController);

    function FileSelectorController() {
        this.selectHdd = (fileInformation) => {
            this.selectedFile = fileInformation.file.name;
            this.onSelectHdd({ fileInformation: fileInformation });
        };

        this.selectDropbox = (fileInformation) => {
            this.selectedFile = fileInformation.file.name;
            this.onSelectDropbox({ fileInformation: fileInformation });
        };
    }
})(window.angular);