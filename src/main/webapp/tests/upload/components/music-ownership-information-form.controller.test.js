'use strict';

describe("Music Ownership Information form Controller:", function() {
    beforeEach(module('mllApp.upload'));

    let ctrl;

    let onNext = () => { };
    let onPrevious = () => { };

    let data;

    beforeEach(inject(function ($controller) {
        data = {
            songwriters: [ { name: '', primaryEmail: '', primaryPhone: '', secondaryPhone: '' } ],
            copyright: '',
            pubCompany: '',
            pro: ''
        };

        ctrl = $controller('MusicOwnerInformationFormController', {},
            {onNext: onNext, onPrevious: onPrevious, data: data });

        ctrl.ownerForm = {
            $invalid: true,
            $submitted: false
        };
    }));

    it("'data' should be defined", function() {
        expect(ctrl.data).toBeDefined();
    });

    it("'onNext' should be defined", function() {
        expect(ctrl.onNext).toBeDefined();
    });

    it("'onPrevious' should be defined", function() {
        expect(ctrl.onPrevious).toBeDefined();
    });

    it("'addWriter' should add an extra object to 'songwriters' array", function () {
        let before = ctrl.data.songwriters.length;

        ctrl.addWriter();

        expect(ctrl.data.songwriters.length).toEqual(before + 1);
    });

    it("'addWriter' should add an extra object to 'artists' array with 'name' and other properties", function () {
        ctrl.addWriter();

        let length = ctrl.data.songwriters.length;

        expect(ctrl.data.songwriters[length - 1]).toBeDefined();
        expect(ctrl.data.songwriters[length - 1].name).toEqual('');
        expect(ctrl.data.songwriters[length - 1].primaryPhone).toEqual('');
        expect(ctrl.data.songwriters[length - 1].secondaryPhone).toEqual('');
        expect(ctrl.data.songwriters[length - 1].primaryEmail).toEqual('');
        expect(ctrl.data.songwriters[length - 1].secondaryEmail).toEqual('');
    });

    it("'removeWriter' should remove a specific 'songwriter' object", function () {
        ctrl.addWriter();
        ctrl.addWriter();
        ctrl.addWriter();

        ctrl.data.songwriters[0].name = 'John';
        ctrl.data.songwriters[1].name = 'Mark';
        ctrl.data.songwriters[2].name = 'Bill';

        let before = ctrl.data.songwriters.length;
        let index = 0;

        ctrl.removeWriter(index);

        expect(ctrl.data.songwriters.length).toEqual(before - 1);
        expect(ctrl.data.songwriters[index].name).toEqual('Mark');
    });

    it("'removeWriter' should remove a specific 'songwriter' object", function () {
        ctrl.addWriter();
        ctrl.addWriter();
        ctrl.addWriter();

        ctrl.data.songwriters[0].name = 'John';
        ctrl.data.songwriters[1].name = 'Mark';
        ctrl.data.songwriters[2].name = 'Bill';

        let before = ctrl.data.songwriters.length;
        let index = 2;

        ctrl.removeWriter(index);

        expect(ctrl.data.songwriters.length).toEqual(before - 1);
        expect(ctrl.data.songwriters[index - 1].name).toEqual('Mark');
    });

    it("'removeWriter' shouldn't remove a 'songwriter' if the index if out of range", function () {
        ctrl.addWriter();
        ctrl.addWriter();
        ctrl.addWriter();

        ctrl.data.songwriters[0].name = 'John';
        ctrl.data.songwriters[1].name = 'Mark';
        ctrl.data.songwriters[2].name = 'Bill';

        let before = ctrl.data.songwriters.length;
        let index = ctrl.data.songwriters.length + 1;

        ctrl.removeWriter(index);

        expect(ctrl.data.songwriters.length).toBe(before);
    });

    it("'submit' function shouldn't call 'onNext'", function() {
        ctrl.ownerForm.$invalid = true;

        spyOn(ctrl, 'onNext');

        ctrl.submit();

        expect(ctrl.ownerForm.$submitted).toBeTruthy();
        expect(ctrl.onNext.calls.any()).toBeFalsy();
    });

    it("'submit' function should call 'onNext'", function() {
        ctrl.ownerForm.$invalid = false;

        spyOn(ctrl, 'onNext');

        ctrl.submit();

        expect(ctrl.onNext).toHaveBeenCalled();
    });

    it("'reset' function should call 'onPrevious'", function() {
        spyOn(ctrl, 'onPrevious');

        ctrl.reset();

        expect(ctrl.onPrevious).toHaveBeenCalled();
    });
});