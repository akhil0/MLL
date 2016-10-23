'use strict';

describe("Music Summary Form Controller:", function() {
    beforeEach(module('mllApp.upload'));

    beforeEach(module('ui.router'));

    let ctrl, state;

    let onAgain = () => { };

    let data;

    beforeEach(inject(function ($controller) {
        data = { isUploaded: true, message: 'Success!' };

        ctrl = $controller('MusicSummaryFormController', {},
            {onAgain: onAgain, data: data });
    }));

    beforeEach(inject(function ($state) {
        state = $state;
    }));

    it("'data' should be defined", function() {
        expect(ctrl.data).toBeDefined();
    });

    it("'onAgain' should be defined", function() {
        expect(ctrl.onAgain).toBeDefined();
    });

    it("'quit' function should call 'go' on $state", function() {
        spyOn(state, 'go');

        ctrl.quit();

        expect(state.go).toHaveBeenCalled();
    });
});