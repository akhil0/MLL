'use strict';

describe('Auth Details Service:', function () {
    beforeEach(module('mllApp.shared'));

    let service;

    beforeEach(inject(function (authDetailsService) {
        service = authDetailsService;
    }));

    it("default state", function () {
        expect(service.isAuth).toBeFalsy();
        expect(service.data).toEqual({ });
    });

    it("'init' without data", function () {
        let dummyData = {};

        service.init(dummyData);

        expect(service.isAuth).toBeTruthy();
        expect(service.data).toEqual(dummyData);
    });

    it("'init' with data", function () {
        let dummyData = { userId: 0, type: 'musician' };

        service.init(dummyData);

        expect(service.isAuth).toBeTruthy();
        expect(service.data).toEqual(dummyData);
    });

    it("'clear' should clean up inner properties", function () {
        let dummyData = { userId: 0, type: 'musician' };

        service.init(dummyData);
        service.clear();

        expect(service.isAuth).toBeFalsy();
        expect(service.data).toEqual({ });
    });

    it("'change' should set up inner properties", function () {
        let dummyData = { userId: 0, type: 'musician', browse: false, upload: true };

        service.change(dummyData);

        expect(service.isAuth).toBeTruthy();
        expect(service.data.id).toBe(0);
        expect(service.data.type).toBe('musician');
        expect(service.data.permissions.browse).toBeFalsy();
        expect(service.data.permissions.upload).toBeTruthy();
    });

    it("'change' should throw an error", function () {
        expect(service.change).toThrow();
    });
});