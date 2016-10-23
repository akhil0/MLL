'use strict';

describe("Music Sound Information Form Controller:", function() {
    beforeEach(module('mllApp.upload'));

    let ctrl;

    let onNext = () => { };
    let onPrevious = () => { };

    let data;

    beforeEach(inject(function ($controller) {
        data = {
            soundOwners: [ { name: '', primaryEmail: '', primaryPhone: '', secondaryPhone: '' } ]
        };

        ctrl = $controller('MusicSoundInformationFormController', {},
            {onNext: onNext, onPrevious: onPrevious, data: data }
        );

        ctrl.soundForm = {
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

    it("'addOwner' should add an extra object to 'soundOwners' array", function () {
        let before = ctrl.data.soundOwners.length;

        ctrl.addOwner();

        expect(ctrl.data.soundOwners.length).toEqual(before + 1);
    });

    it("'addOwner' should add an extra object to 'soundOwners' array with 'name' and other properties", function () {
        ctrl.addOwner();

        let length = ctrl.data.soundOwners.length;

        expect(ctrl.data.soundOwners[length - 1]).toBeDefined();
        expect(ctrl.data.soundOwners[length - 1].name).toEqual('');
        expect(ctrl.data.soundOwners[length - 1].primaryPhone).toEqual('');
        expect(ctrl.data.soundOwners[length - 1].secondaryPhone).toEqual('');
        expect(ctrl.data.soundOwners[length - 1].primaryEmail).toEqual('');
        expect(ctrl.data.soundOwners[length - 1].secondaryEmail).toEqual('');
    });

    it("'removeOwner' should remove a specific 'soundOwner' object", function () {
        ctrl.addOwner();
        ctrl.addOwner();
        ctrl.addOwner();

        ctrl.data.soundOwners[0].name = 'John';
        ctrl.data.soundOwners[1].name = 'Mark';
        ctrl.data.soundOwners[2].name = 'Bill';

        let before = ctrl.data.soundOwners.length;
        let index = 0;

        ctrl.removeOwner(index);

        expect(ctrl.data.soundOwners.length).toEqual(before - 1);
        expect(ctrl.data.soundOwners[index].name).toEqual('Mark');
    });

    it("'removeWriter' should remove a specific 'soundOwner' object", function () {
        ctrl.addOwner();
        ctrl.addOwner();
        ctrl.addOwner();

        ctrl.data.soundOwners[0].name = 'John';
        ctrl.data.soundOwners[1].name = 'Mark';
        ctrl.data.soundOwners[2].name = 'Bill';

        let before = ctrl.data.soundOwners.length;
        let index = 2;

        ctrl.removeOwner(index);

        expect(ctrl.data.soundOwners.length).toEqual(before - 1);
        expect(ctrl.data.soundOwners[index - 1].name).toEqual('Mark');
    });

    it("'removeWriter' shouldn't remove a 'songwriter' if the index if out of range", function () {
        ctrl.addOwner();
        ctrl.addOwner();
        ctrl.addOwner();

        ctrl.data.soundOwners[0].name = 'John';
        ctrl.data.soundOwners[1].name = 'Mark';
        ctrl.data.soundOwners[2].name = 'Bill';

        let before = ctrl.data.soundOwners.length;
        let index = ctrl.data.soundOwners.length + 1;

        ctrl.removeOwner(index);

        expect(ctrl.data.soundOwners.length).toBe(before);
    });

    it("'submit' function shouldn't call 'onNext'", function() {
        ctrl.soundForm.$invalid = true;

        spyOn(ctrl, 'onNext');

        ctrl.submit();

        expect(ctrl.soundForm.$submitted).toBeTruthy();
        expect(ctrl.onNext.calls.any()).toBeFalsy();
    });

    it("'submit' function should call 'onNext'", function() {
        ctrl.soundForm.$invalid = false;

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