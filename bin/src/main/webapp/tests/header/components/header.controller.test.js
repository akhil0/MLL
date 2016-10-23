'use strict';

describe("Header Controller:", function() {
    beforeEach(module('mllApp.header'));

    beforeEach(module('ui.router'));

    let ctrl, state;

    beforeEach(inject(function($controller) {
        ctrl = $controller('HeaderController', {});
    }));

    beforeEach(inject(function($state) {
        state = $state;
    }));

    it("'home' function should call 'go' on $state", function() {
        spyOn(state, 'go');

        ctrl.home();

        expect(state.go).toHaveBeenCalled();
    });

    it("'logout' function should call 'go' on $state", function() {
        spyOn(state, 'go');

        ctrl.logout();

        expect(state.go).toHaveBeenCalled();
    });
});