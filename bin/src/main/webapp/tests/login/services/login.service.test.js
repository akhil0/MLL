'use strict';

describe('Login Service:', function () {
    beforeEach(module('mllApp.login'));

    let httpBackend, logService, authService;

    let url;

    beforeEach(inject(function (loginUrl) {
        url = loginUrl;
    }));

    beforeEach(inject(function ($injector) {
        httpBackend = $injector.get('$httpBackend');
    }));

    beforeEach(inject(function (loginService, authenticationService) {
        logService = loginService;
        authService = authenticationService;
    }));

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it("'login' should fire POST request", function () {
        httpBackend.expectPOST(url, { }).respond({});

        logService.login({ });

        httpBackend.flush();
    });

    it("'login' should fire POST request and send passed data", function () {
        let data = { emailId: 'test@gmail.com', password: '12345678' };

        httpBackend.expectPOST(url, data).respond({});

        logService.login(data);

        httpBackend.flush();
    });

    it("'login' should call authentication regService's 'check' method", function () {
        let serverResponse = { isValidUser: true, userId: 0, userType: 'user', browse: true, upload: false };

        httpBackend.whenPOST(url, {}).respond(serverResponse);

        spyOn(authService, 'change');

        logService.login({ });

        httpBackend.flush();

        expect(authService.change).toHaveBeenCalled();
        expect(authService.change).toHaveBeenCalledWith(serverResponse);
    });

    it("'login' should not call authentication regService's 'check' method", function () {
        let serverResponse = { isValidUser: false };

        httpBackend.whenPOST(url, {}).respond(serverResponse);

        spyOn(authService, 'change');

        logService.login({ });

        httpBackend.flush();

        expect(authService.change).not.toHaveBeenCalled();
    });

    it("'login' should return unwrapped server response", function () {
        let serverResponse = { isRegistered: true, userId: 0, userType: 'user', browse: true, upload: false };
        let receivedResponse;

        httpBackend.whenPOST(url, {}).respond(serverResponse);

        logService.login({ })
            .then(function (response) {
                receivedResponse = response;
            });

        httpBackend.flush();

        expect(receivedResponse).toEqual(serverResponse);
    });
});