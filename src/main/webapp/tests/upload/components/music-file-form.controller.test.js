'use strict';

describe("Music File Form Controller:", function() {
    beforeEach(module('mllApp.upload'));

    let ctrl;

    let onNext = () => {},
        onPrevious = () => {};

    beforeEach(inject(function($controller) {
        ctrl = $controller('MusicFileFormController', {},
            { onNext: onNext, onPrevious: onPrevious });
    }));

    it("'musicFormats' should be injected", function() {
        expect(ctrl.formats).toBeDefined();
        expect(angular.isArray(ctrl.formats)).toBeTruthy();
    });

    it("'musicSize' should be injected", function() {
        expect(ctrl.size).toBeDefined();
    });

    it("'onNext' should be defined", function() {
        expect(ctrl.onNext).toBeDefined();
    });

    it("'onPrevious' should be defined", function() {
        expect(ctrl.onPrevious).toBeDefined();
    });

    it("'onNext' should be a function", function() {
        expect(typeof ctrl.onNext).toBe('function');
    });

    it("'onPrevious' should be a function", function() {
        expect(typeof ctrl.onPrevious).toBe('function');
    });

    it("'validateFormat' should return 'false' for 'hands up!.mp4'", function() {
        expect(ctrl.validateFormat('hands up!.mp4')).toBeFalsy();
    });

    it("'validateFormat' should return 'false' for 'liar.ogg'", function() {
        expect(ctrl.validateFormat('liar.ogg')).toBeFalsy();
    });

    it("'validateFormat' should return 'false' for 'heartbreak.m4a'", function() {
        expect(ctrl.validateFormat('heartbreak.m4a')).toBeFalsy();
    });

    it("'validateFormat' should return 'true' for 'skyfall.mp3'", function() {
        expect(ctrl.validateFormat('skyfall.mp3')).toBeTruthy();
    });

    it("'validateFormat' should return 'true' for 'placebo.wav'", function() {
        expect(ctrl.validateFormat('placebo.wav')).toBeTruthy();
    });

    it("'validateSize' should return 'false' for '11MB'", function() {
        expect(ctrl.validateSize(11 * 1024 * 1024)).toBeFalsy();
    });

    it("'validateSize' should return 'true' for '10MB", function() {
        expect(ctrl.validateSize(10 * 1024 * 1024)).toBeTruthy();
    });

    it("'selectDropbox' should set 'data' field and mark 'form.invalid' flag as 'false'", function() {
        let fileInformation = {
            isDirect: false,
            file: {name: 'sample.mp3', size: 5 * 1024 * 1024, type: 'audio/mp3'}
        };

        ctrl.selectDropbox(fileInformation);

        expect(ctrl.data).toBeDefined();
        expect(ctrl.data).toEqual(fileInformation);
        expect(ctrl.form.invalid).toBeFalsy();
    });

    it("'selectHdd' should set 'data' field and mark 'form.invalid' flag as 'false'", function() {
        let fileInformation = {
            isDirect: false,
            file: {name: 'sample.mp3', size: 5 * 1024 * 1024, type: 'audio/mp3'}
        };

        ctrl.selectHdd(fileInformation);

        expect(ctrl.form.errors.required).toBeFalsy();
        expect(ctrl.form.errors.size).toBeFalsy();
        expect(ctrl.form.errors.format).toBeFalsy();
        expect(ctrl.form.invalid).toBeFalsy();
        expect(ctrl.data).toBeDefined();
        expect(ctrl.data).toEqual(fileInformation);
    });

    it("'selectHdd' should not set 'data' field and mark 'form.invalid' flag as 'true'", function() {
        let fileInformation = {
            isDirect: false,
            file: {name: 'sample.m4a', size: 5 * 1024 * 1024, type: 'audio/m4a'}
        };

        ctrl.selectHdd(fileInformation);

        expect(ctrl.form.errors.required).toBeFalsy();
        expect(ctrl.form.errors.size).toBeFalsy();
        expect(ctrl.form.errors.format).toBeTruthy();
        expect(ctrl.form.invalid).toBeTruthy();
        expect(ctrl.data).toBeUndefined();
    });

    it("'selectHdd' should set not 'data' field and mark 'form.invalid' flag as 'true'", function() {
        let fileInformation = {
            isDirect: false,
            file: {name: 'sample.ogg', size: 111 * 1024 * 1024, type: 'audio/ogg'}
        };

        ctrl.selectHdd(fileInformation);

        expect(ctrl.form.errors.required).toBeFalsy();
        expect(ctrl.form.errors.size).toBeTruthy();
        expect(ctrl.form.errors.format).toBeTruthy();
        expect(ctrl.form.invalid).toBeTruthy();
        expect(ctrl.data).toBeUndefined();
    });

    it("'submit' function shouldn't call 'onNext'", function() {
        spyOn(ctrl, 'onNext');

        ctrl.form.invalid = true;

        ctrl.submit();

        expect(ctrl.form.submitted).toBeTruthy();
        expect(ctrl.onNext.calls.any()).toBeFalsy();
    });

    it("'submit' function should call 'onNext'", function() {
        let fileInformation = {
            isDirect: false,
            file: {name: 'sample.mp3', size: 5 * 1024 * 1024, type: 'audio/mp3'}
        };

        ctrl.selectDropbox(fileInformation);

        spyOn(ctrl, 'onNext');

        ctrl.form.invalid = false;

        ctrl.submit();

        expect(ctrl.form.submitted).toBeFalsy();
        expect(ctrl.onNext).toHaveBeenCalled();
    });

    it("'reset' function should call 'onPrevious'", function() {
        spyOn(ctrl, 'onPrevious');

        ctrl.reset();

        expect(ctrl.onPrevious).toHaveBeenCalled();
    });

    it("'reset' function should call 'onPrevious'", function() {
        spyOn(ctrl, 'onPrevious');

        ctrl.reset();

        expect(ctrl.onPrevious).toHaveBeenCalled();
    });
});