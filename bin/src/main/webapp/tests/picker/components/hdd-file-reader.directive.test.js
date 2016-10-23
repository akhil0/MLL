'use strict';

describe("HDD File Reader Directive:", function() {
    beforeEach(module('mllApp.picker'));

    let scope, ctrl, elem;
    let input;

    let onSelect = (file) => { };

    beforeEach(inject(function($rootScope, $compile) {
        scope = $rootScope.$new();

        elem = angular.element(
            `<mll-hdd-file-reader on-select="onSelect(file)">
             </mll-hdd-file-reader>`
        );

        angular.element(document).find('body').append(elem);

        elem = $compile(elem)(scope);

        $rootScope.$digest();

        input = elem.find('input[type="file"]');
    }));

    it ("selecting a file should trigger controller's 'change' function", function() {
        let file = { name: 'sample.mp3', size: 50000, type: 'audio/mp3' };

        let files = { 0: file, length: 1, item: (index) => file };

        // Workaround
        let ctrl = scope.$$childTail.ctrl;

        spyOn(ctrl, 'change');

        input.triggerHandler({
            type: 'change',
            target: {
                files: files
            }
        });

        expect(ctrl.change).toHaveBeenCalled();
    });
});