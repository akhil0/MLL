'use strict';

describe('Registration Service:', function () {
    beforeEach(module('mllApp.registration'));

    let httpBackend, regService, authService;

    let url;

    beforeEach(inject(function (registrationUrl) {
        url = registrationUrl;
    }));

    beforeEach(inject(function ($injector) {
        httpBackend = $injector.get('$httpBackend');
    }));

    beforeEach(inject(function (registrationService, authenticationService) {
        regService = registrationService;
        authService = authenticationService;
    }));

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it("'register' should fire POST request", function () {
        httpBackend.expectPOST(url, { }).respond({});

        regService.register({ });

        httpBackend.flush();
    });

    it("'register' should fire POST request and send passed data", function () {
        let data = { userName: 'User', password: '12345678' };

        httpBackend.expectPOST(url, data).respond({});

        regService.register(data);

        httpBackend.flush();
    });

    it("'register' should call authentication regService's 'check' method", function () {
        let serverResponse = { isRegistered: true, userId: 0, userType: 'user', browse: true, upload: false };

        httpBackend.whenPOST(url, {}).respond(serverResponse);

        spyOn(authService, 'change');

        regService.register({ });

        httpBackend.flush();

        expect(authService.change).toHaveBeenCalled();
        expect(authService.change).toHaveBeenCalledWith(serverResponse);
    });

    it("'register' should not call authentication regService's 'check' method", function () {
        let serverResponse = { isRegistered: false };

        httpBackend.whenPOST(url, {}).respond(serverResponse);

        spyOn(authService, 'change');

        regService.register({ });

        httpBackend.flush();

        expect(authService.change).not.toHaveBeenCalled();
    });

    it("'register' should return unwrapped server response", function () {
        let serverResponse = { isRegistered: true, userId: 0, userType: 'user', browse: true, upload: false };
        let receivedResponse;

        httpBackend.whenPOST(url, {}).respond(serverResponse);

        regService.register({ })
            .then(function (response) {
                receivedResponse = response;
            });

        httpBackend.flush();

        expect(receivedResponse).toEqual(serverResponse);
    });
});