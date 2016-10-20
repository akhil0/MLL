'use strict';

describe("Scrollable Agreement Controller:", function() {
    beforeEach(module('mllApp.shared'));

    let ctrl, value;

    let onAgree = (val) => value = val;

    beforeEach(inject(function($controller) {
        ctrl = $controller('ScrollableAgreementController', {}, { onAgree: onAgree });
        value = null;
    }));

    it("'onAgree should be defined", function() {
        expect(ctrl.onAgree).toBeDefined();
    });

    it("'onAgree' should be a function", function() {
        expect(typeof ctrl.onAgree).toBe('function');
    });

    it("'agree' should call 'onAgree'", function() {
        spyOn(ctrl, 'onAgree');

        ctrl.agree();

        expect(ctrl.onAgree).toHaveBeenCalled();
    });

    it("'agree' function should pass 'false' to 'onAgree'", function() {
        ctrl.isChecked = false;
        ctrl.agree();

        expect(value.isChecked).toBeFalsy();
    });

    it("'agree' function should pass 'true' to 'onAgree'", function() {
        ctrl.isChecked = true;
        ctrl.agree();

        expect(value.isChecked).toBeTruthy();
    });
});