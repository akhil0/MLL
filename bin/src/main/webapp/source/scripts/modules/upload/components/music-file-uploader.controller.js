(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicFileUploaderController', MusicFileUploaderController);

    MusicFileUploaderController.$inject = ['musicForms', 'musicData', 'musicUploadService'];

    function MusicFileUploaderController(musicForms, musicData, musicUploadService, authService) {
        this.forms = angular.copy(musicForms);

        this.data = angular.copy(musicData);
        this.data.generalInformation.userId = this.userId;

        this.uploadService = musicUploadService;
        this.authService = authService;

        this.next = () => {
            this.forms.data[this.forms.currentId].isActive = false;

            this.forms.currentId++;

            this.forms.data[this.forms.currentId].isActive = true;
        };

        this.previous = () => {
            this.forms.data[this.forms.currentId].isActive = false;

            this.forms.currentId--;

            this.forms.data[this.forms.currentId].isActive = true;
        };

        this.prepare = (data) => {
            let obj = {
                generalInformation: data.generalInformation,
                ownershipInformation: data.ownershipInformation,
                soundInformation: data.soundInformation
            };

            obj.isDirect = data.fileInformation.isDirect;
            obj.file = (obj.isDirect) ? data.fileInformation.file : data.fileInformation.file.link;

            return obj;
        };

        this.submit = () => {
            let data = this.prepare(this.data);

            let promise = (data.isDirect) ? this.uploadService.submitDirect(data, 'file')
                : this.uploadService.submitCloud(data);

            promise.then((response) => {
                this.data.serverInformation.isUploaded = response.data.isUploaded;
                this.data.serverInformation.message = response.data.message;
            })
            .catch((reject) => {
                this.data.serverInformation.isUploaded = false;
                this.data.serverInformation.message = reject;
            })
            .finally(() => this.next());
        };

        this.again = () => {
            this.forms.data[this.forms.currentId].isActive = false;

            this.forms.currentId = 0;

            this.forms.data[this.forms.currentId].isActive = true;

            /* Clear file information */
            this.data.fileInformation.file = null;

            /* Clear general information */
            this.data.generalInformation.title = '';
        };
    }
})(window.angular);