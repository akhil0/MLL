'use strict';

describe('Invite Token Service:', function () {
    beforeEach(module('mllApp.shared'));

    let httpBackend, service;

    let url;

    beforeEach(inject(function (invitationUrl) {
        url = invitationUrl;
    }));

    beforeEach(inject(function ($injector) {
        httpBackend = $injector.get('$httpBackend');
    }));

    beforeEach(inject(function (inviteTokenService) {
        service = inviteTokenService;
    }));

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it("'validateToken' should fire POST request with appropriate action type", function () {
        httpBackend.expectPOST(url, {
            token: 'TEST_TOKEN',
            inviteType: 'user',
            actionType: 'validate'
        }).respond({});

        service.validateToken({ token: 'TEST_TOKEN', inviteType: 'user' });

        httpBackend.flush();
    });

    it("'generateToken' should fire POST request with appropriate action type", function () {
        httpBackend.expectPOST(url, {
            userId: 0,
            email: 'test@gmail.com',
            messageBody: '',
            inviteType: 'user',
            actionType: 'generate'
        }).respond({});

        service.generateToken({ userId: 0, email: 'test@gmail.com', messageBody: '', inviteType: 'user' });

        httpBackend.flush();
    });
});