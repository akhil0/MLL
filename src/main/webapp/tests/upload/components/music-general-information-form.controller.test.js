'use strict';

describe("Music General Information form Controller:", function() {
    beforeEach(module('mllApp.upload'));

    let ctrl;

    let onNext = () => {};
    let onPrevious = () => {};

    let data;

    beforeEach(inject(function ($controller) {
        data = {
            title: '',
            artists: [ { name: '' } ],
            beatRate: 0,
            primaryGenre: '',
            secondaryGenre: ''
        };

        ctrl = $controller('MusicGeneralInformationFormController', {},
            { onNext: onNext, onPrevious: onPrevious, data: data }
        );

        ctrl.generalForm = {
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

    it("'addArtist' should add an extra object to 'artists' array", function () {
        let before = ctrl.data.artists.length;

        ctrl.addArtist();

        expect(ctrl.data.artists.length).toEqual(before + 1);
    });

    it("'addArtist' should add an extra object to 'artists' array with 'name' property", function () {
        ctrl.addArtist();

        let length = ctrl.data.artists.length;

        expect(ctrl.data.artists[length - 1]).toBeDefined();
        expect(ctrl.data.artists[length - 1].name).toEqual('');
    });

    it("'removeArtist' should remove a specific 'artist' object", function () {
        ctrl.addArtist();
        ctrl.addArtist();
        ctrl.addArtist();

        ctrl.data.artists[0].name = 'John';
        ctrl.data.artists[1].name = 'Mark';
        ctrl.data.artists[2].name = 'Bill';

        let before = ctrl.data.artists.length;
        let index = 0;

        ctrl.removeArtist(index);

        expect(ctrl.data.artists.length).toEqual(before - 1);
        expect(ctrl.data.artists[index].name).toEqual('Mark');
    });

    it("'removeArtist' should remove a specific 'artist' object", function () {
        ctrl.addArtist();
        ctrl.addArtist();
        ctrl.addArtist();

        ctrl.data.artists[0].name = 'John';
        ctrl.data.artists[1].name = 'Mark';
        ctrl.data.artists[2].name = 'Bill';

        let before = ctrl.data.artists.length;
        let index = 2;

        ctrl.removeArtist(index);

        expect(ctrl.data.artists.length).toEqual(before - 1);
        expect(ctrl.data.artists[index - 1].name).toEqual('Mark');
    });

    it("'removeArtist' shouldn't remove an 'artist' if the index if out of range", function () {
        ctrl.addArtist();
        ctrl.addArtist();
        ctrl.addArtist();

        ctrl.data.artists[0].name = 'John';
        ctrl.data.artists[1].name = 'Mark';
        ctrl.data.artists[2].name = 'Bill';

        let before = ctrl.data.artists.length;
        let index = ctrl.data.artists.length + 1;

        ctrl.removeArtist(index);

        expect(ctrl.data.artists.length).toBe(before);
    });

    it("'selectGenre' function should not change 'secondaryGenre' parameter", function () {
        ctrl.data.secondaryGenre = 'Test Genre';

        ctrl.selectGenre('New Test Genre');

        expect(ctrl.data.secondaryGenre).toBe('Test Genre');
    });

    it("'selectGenre' function should reset 'secondaryGenre' parameter", function () {
        ctrl.data.secondaryGenre = 'Test Genre';

        ctrl.selectGenre('');

        expect(ctrl.data.secondaryGenre).toBeNull();
    });

    it("'submit' function shouldn't call 'onNext'", function() {
        ctrl.generalForm.$invalid = true;

        spyOn(ctrl, 'onNext');

        ctrl.submit();

        expect(ctrl.generalForm.$submitted).toBe(true);
        expect(ctrl.onNext.calls.any()).toBeFalsy();
    });

    it("'submit' function shouldn't call 'onNext'", function() {
        ctrl.generalForm.$invalid = false;

        spyOn(ctrl, 'onNext');

        ctrl.submit();

        expect(ctrl.generalForm.$submitted).toBeFalsy();
        expect(ctrl.onNext).toHaveBeenCalled();
    });

    it("'reset' function should call 'onPrevious'", function() {
        spyOn(ctrl, 'onPrevious');

        ctrl.reset();

        expect(ctrl.onPrevious).toHaveBeenCalled();
    });
});