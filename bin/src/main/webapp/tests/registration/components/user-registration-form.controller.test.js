'use strict';

describe('Registration Service:', function () {
    beforeEach(module('ui.router'));

    beforeEach(module('mllApp.registration'));

    let ctrl, state, types, service, q;

    beforeEach(inject(function($controller) {
        ctrl = $controller('UserRegistrationFormController', {},
            { inviteToken: 0 });

        ctrl.registrationForm = { $invalid: true, $submitted: false };

    }));

    beforeEach(inject(function($state) {
        state = $state;
    }));

    beforeEach(inject(function($q) {
        q = $q;
    }));

    beforeEach(inject(function(registrationService, registrationTypes) {
        service = registrationService;
        types = registrationTypes;
    }));

    it("'inviteToken' should be injected", function() {
        expect(ctrl.inviteToken).toBeDefined();
        expect(ctrl.inviteToken).toEqual(0);
    });

    it("'redirect' should call 'go' on '$state'", function() {
        spyOn(state, 'go');

        ctrl.redirect(1);

        expect(state.go).toHaveBeenCalled();
        expect(state.go).toHaveBeenCalledWith(types.user, { id: 1 });
    });

    it("'displayError' should set '$serverError' to 'true'", function() {
        let message = 'Error';

        ctrl.displayError(message);

        expect(ctrl.registrationForm.$serverError).toBe(true);
    });

    it("'displayError' should set 'errorMessage' property", function() {
        let message = 'Error';

        ctrl.displayError(message);

        expect(ctrl.errorMessage).toBe(message);
    });

    it("'processResponse' should call 'redirect' if the registration succeeded", function() {
        let data = { isRegistered: true, userId: 0 };

        spyOn(ctrl, 'redirect');

        ctrl.processResponse(data);

        expect(ctrl.redirect).toHaveBeenCalled();
        expect(ctrl.redirect).toHaveBeenCalledWith(data.userId);
    });

    it("'processResponse' should call 'displayError' if the registration failed", function() {
        let data = { isRegistered: false, errorMessage: 'Error' };

        spyOn(ctrl, 'displayError');

        ctrl.processResponse(data);

        expect(ctrl.displayError).toHaveBeenCalled();
        expect(ctrl.displayError).toHaveBeenCalledWith(data.errorMessage);
    });

    it("'processResponse' should not call 'redirect' if the registration failed", function() {
        let data = { isRegistered: false, errorMessage: 'Error' };

        spyOn(ctrl, 'redirect');

        ctrl.processResponse(data);

        expect(ctrl.redirect).not.toHaveBeenCalled();
    });

    it("'register' should not trigger call to 'registrationService' if the form is invalid", function () {
        spyOn(service, 'register');

        ctrl.register();

        expect(service.register).not.toHaveBeenCalled();
    });

    it("'register' should set '$submitted' flag to 'true' if the form is invalid", function () {
        ctrl.register();

        expect(ctrl.registrationForm.$submitted).toBe(true);
    });

    it("'register' function should trigger call to 'registrationService' if the form is valid", function () {
        ctrl.registrationForm.$invalid = false;

        spyOn(service, 'register').and.returnValue(q.resolve({ isRegistered: true, userId: 0 }));

        ctrl.register();

        expect(service.register).toHaveBeenCalled();
    });
});