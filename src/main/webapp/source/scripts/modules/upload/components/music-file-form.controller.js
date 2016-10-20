(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicFileFormController', MusicFileFormController);

    MusicFileFormController.$inject = ['musicFormats', 'musicSize'];

    function MusicFileFormController(musicFormats, musicSize) {
        this.size = angular.copy(musicSize);
        this.formats = angular.copy(musicFormats);

        // Replication of Angular Form Behaviour
        this.form = {
            invalid: true,
            submitted: false,
            errors: {
                size: false,
                format: false,
                required: true
            }
        };

        this.validateFormat = (fileName) =>
            this.formats.includes(fileName.slice(fileName.lastIndexOf('.')));

        this.validateSize = (size) => size <= this.size;

        this.selectHdd = (fileInformation) => {
            this.form.errors.size = !this.validateSize(fileInformation.file.size);
            this.form.errors.format = !this.validateFormat(fileInformation.file.name);
            this.form.errors.required = false;

            this.form.invalid = this.form.errors.size || this.form.errors.format;

            if (!this.form.invalid) this.data = fileInformation;

        };

        this.selectDropbox = (fileInformation) => {
            this.form.invalid = false;

            this.form.errors.size = false;
            this.form.errors.format = false;
            this.form.errors.required = false;

            this.data = fileInformation;
        };

        this.submit = () => {
            if (this.form.invalid) this.form.submitted = true;
            else {
                if (this.data.file === null) {
                    this.form.invalid = true;
                    this.form.submitted = true;
                    this.form.errors.required = true;
                }
                else this.onNext();
            }
        };

        this.reset = () => this.onPrevious();
    }
})(window.angular);