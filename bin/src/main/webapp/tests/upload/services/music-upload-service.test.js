'use strict';

describe('Music Upload Service:', function () {
    beforeEach(module('mllApp.upload'));

    let httpBackend, service;

    let directData = { isDirect: true, file: {} };
    let cloudData = { isDirect: false, file: 'http://someurl.org' };

    let url;

    beforeEach(inject(function (musicUrl) {
        url = musicUrl;
    }));

    beforeEach(inject(function ($injector) {
        httpBackend = $injector.get('$httpBackend');
    }));

    beforeEach(inject(function (musicUploadService) {
        service = musicUploadService;
    }));

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it("'submitDirect' should fire POST request", function () {
        httpBackend.expectPOST(url.direct, new FormData()).respond({});

        service.submitDirect({});

        httpBackend.flush();
    });

    it("'submitCloud' should fire POST request", function () {
        httpBackend.expectPOST(url.cloud, {}).respond({});

        service.submitCloud({});

        httpBackend.flush();
    });

    it("'submitCloud' should fire POST request with passed data", function () {
        httpBackend.expectPOST(url.cloud, cloudData).respond({});

        service.submitCloud(cloudData);

        httpBackend.flush();
    });

    it("'submitDirect' should fire POST request with appropriate headers", function () {
        httpBackend.expectPOST(url.direct, new FormData(), function(headers) {
            return headers['Content-Type'] === undefined;
        }).respond({});

        service.submitDirect({});

        httpBackend.flush();
    });
});