'use strict';

describe("File Selector Controller:", function() {
    beforeEach(module('mllApp.picker'));

    let ctrl;
    let selectedFileInformation;
    let onSelectHdd = (fileInformation) => selectedFileInformation = fileInformation,
        onSelectDropbox = (fileInformation) => selectedFileInformation = fileInformation;

    beforeEach(inject(function($controller) {
        ctrl = $controller('FileSelectorController', {},
            { onSelectHdd: onSelectHdd, onSelectDropbox: onSelectDropbox });

        selectedFileInformation = null;
    }));

    it("'onSelectHdd' should be defined", function() {
        expect(ctrl.onSelectHdd).toBeDefined();
    });

    it("'onSelectDropbox' should be defined", function() {
        expect(ctrl.onSelectDropbox).toBeDefined();
    });

    it("'onSelectHdd' should be a function", function() {
        expect(typeof ctrl.onSelectHdd).toBe('function');
    });

    it("'onSelectDropbox' should be a function", function() {
        expect(typeof ctrl.onSelectDropbox).toBe('function');
    });

    it("'selectHdd' should call 'onSelectHdd' and set 'selectedFile' field", function() {
        let fileInformation ={
            isDirect: true,
            file: { name: 'sample.mp3', size: 50000, type: 'audio/mp3' }
        };

        spyOn(ctrl, 'onSelectHdd');

        ctrl.selectHdd(fileInformation);

        expect(ctrl.onSelectHdd).toHaveBeenCalled();
        expect(ctrl.selectedFile).toBeDefined();
        expect(ctrl.selectedFile).toEqual('sample.mp3');
    });

    it("'selectDropbox' should call 'onSelectDropbox' and set 'selectedFile' field", function() {
        let fileInformation ={
            isDirect: false,
            file: { name: 'demo.wav', size: 50000, type: 'audio/wav' }
        };

        spyOn(ctrl, 'onSelectDropbox');

        ctrl.selectDropbox(fileInformation);

        expect(ctrl.onSelectDropbox).toHaveBeenCalled();
        expect(ctrl.selectedFile).toBeDefined();
        expect(ctrl.selectedFile).toEqual('demo.wav');
    });

    it("'selectHdd' function should pass 'sample.mp3' to 'onSelectHdd'", function() {
        let fileInformation = {
            isDirect: true,
            file: {name: 'sample.mp3', size: 50000, type: 'audio/mp3'}
        };

        ctrl.selectHdd(fileInformation);

        expect(selectedFileInformation.fileInformation).toBeDefined();
        expect(selectedFileInformation.fileInformation.isDirect).toBeTruthy();
        expect(selectedFileInformation.fileInformation.file.name).toEqual('sample.mp3');
    });

    it("'selectDropbox' function should pass 'demo.wav' to 'onSelectDropbox'", function() {
        let fileInformation = {
            isDirect: false,
            file: {name: 'demo.wav', size: 50000, type: 'audio/wav'}
        };
        ctrl.selectDropbox(fileInformation);

        expect(selectedFileInformation.fileInformation).toBeDefined();
        expect(selectedFileInformation.fileInformation.isDirect).toBeFalsy();
        expect(selectedFileInformation.fileInformation.file.name).toEqual('demo.wav');
    });
});