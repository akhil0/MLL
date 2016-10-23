'use strict';

describe("Scrollable Agreement Directive:", function() {
    beforeEach(module('mllApp.shared'));

    let scope, elem, content;
    let agreement, checkbox;

    let onAgree = (isChecked) => {};

    beforeEach(inject(function($rootScope, $compile) {
        scope = $rootScope.$new();

        Array.apply(null, { length: 100 }).forEach((e, i) => content += '<p>Test</p>');

        elem = angular.element(
            `<mll-scrollable-agreement on-agree="onAgree(isChecked)">
                ${content}
             </mll-scrollable-agreement>`
        );

        angular.element(document).find('body').append(elem);

        elem = $compile(elem)(scope);

        $rootScope.$digest();

        agreement = elem.find('.agreement__document');
        checkbox = elem.find('input[type="checkbox"]');
    }));

    afterEach(function() {
        content = '';
    });

    it("agreement height should be 300px", function() {
        expect(agreement.outerHeight()).toBe(300);
    });

    it ("agreement checkbox should be initially disabled", function() {
        expect(checkbox.attr('disabled')).toEqual('disabled');
    });

    it ("agreement checkbox should be disabled after scrolling by 1%", function() {
        let scrollStep = (agreement.prop('scrollHeight') - agreement.prop('offsetHeight')) / 100;

        agreement.scrollTop(scrollStep);
        agreement.triggerHandler('scroll');

        expect(checkbox.attr('disabled')).toEqual('disabled');
    });

    it ("agreement checkbox should be disabled after scrolling by 10%", function() {
        let scrollStep = (agreement.prop('scrollHeight') - agreement.prop('offsetHeight')) / 10;

        agreement.scrollTop(scrollStep);
        agreement.triggerHandler('scroll');

        expect(checkbox.attr('disabled')).toEqual('disabled');
    });

    it ("agreement checkbox should be disabled after scrolling by 50%", function() {
        let scrollStep = (agreement.prop('scrollHeight') - agreement.prop('offsetHeight')) / 2;

        agreement.scrollTop(scrollStep);
        agreement.triggerHandler('scroll');

        expect(checkbox.attr('disabled')).toEqual('disabled');
    });

    it ("agreement checkbox should be disabled after scrolling by 99%", function() {
        let scrollStep = (agreement.prop('scrollHeight') - agreement.prop('offsetHeight')) / 100 * 99;

        agreement.scrollTop(scrollStep);
        agreement.triggerHandler('scroll');

        expect(checkbox.attr('disabled')).toEqual('disabled');
    });

    it ("agreement checkbox should be enabled after scrolling by 100%", function() {
        let scrollStep = (agreement.prop('scrollHeight') - agreement.prop('offsetHeight'));

        agreement.scrollTop(scrollStep);
        agreement.triggerHandler('scroll');

        expect(checkbox.attr('disabled')).not.toBeDefined();
    });
});