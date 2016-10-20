'use strict';

describe('Authentication Service:', function () {
    beforeEach(module('mllApp.shared'));

    let service, cookies, cookiesKey;

    beforeEach(inject(function (authenticationService) {
        service = authenticationService;

        service.clear();
    }));

    beforeEach(inject(function ($cookies) {
        cookies = $cookies;
    }));

    beforeEach(inject(function (authDetailsKey) {
        cookiesKey = authDetailsKey;
    }));

    it("'change' should update details property", function() {
        let dummyData = { userId: 0, type: 'musician', browse: false, upload: true };

        service.change(dummyData);

        expect(service.details.isAuth).toBeTruthy();
        expect(service.details.data.id).toBe(0);
        expect(service.details.data.type).toBe('musician');
        expect(service.details.data.permissions.browse).toBeFalsy();
        expect(service.details.data.permissions.upload).toBeTruthy();
    });

    it("'change' should update cookies", function() {
        let dummyData = { userId: 0, type: 'musician', browse: false, upload: true };

        service.change(dummyData);

        let cookiesData = cookies.getObject(cookiesKey);

        expect(cookiesData.isAuth).toBeTruthy();
        expect(cookiesData.data.id).toBe(0);
        expect(cookiesData.data.type).toBe('musician');
        expect(cookiesData.data.permissions.browse).toBeFalsy();
        expect(cookiesData.data.permissions.upload).toBeTruthy();
    });

    it("'change' should throw error", function() {
        expect(service.change).toThrow();
    });

    it("'clear' should reset details property", function() {
        service.clear();

        expect(service.details.isAuth).toBeFalsy();
        expect(service.details.data).toEqual({ });
    });

    it("'clear' should reset cookies", function() {
        service.clear();

        let cookiesData = cookies.getObject(cookiesKey);

        expect(cookiesData).not.toBeDefined();
    });

    it("'check' without any previous information", function() {
        service.check();

        expect(service.details.isAuth).toBeFalsy();
        expect(service.details.data).toEqual({ });
    });

    it("'check' with previous information", function() {
        let dummyData = { userId: 0, type: 'musician', browse: false, upload: true };

        service.change(dummyData);

        service.check();

        expect(service.details.isAuth).toBeTruthy();
        expect(service.details.data.id).toBe(0);
        expect(service.details.data.type).toBe('musician');
        expect(service.details.data.permissions.browse).toBeFalsy();
        expect(service.details.data.permissions.upload).toBeTruthy();
    });
});