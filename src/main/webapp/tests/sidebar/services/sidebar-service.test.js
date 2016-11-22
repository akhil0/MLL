/*
'use strict';

describe('Sidebar Service:', function () {
    beforeEach(module('ui.router'));

    beforeEach(module('mllApp.sidebar'));

    
    let ctrl, state;

    beforeEach(inject(function($controller) {
        ctrl = $controller('SidebarController', {});
    }));

    beforeEach(inject(function($state) {
        state = $state;
    }));
    
   it("'redirect' should call 'home' on '$state'", function() {
       spyOn(state, 'go');
      
       ctrl.home();
       expect(state.go).toHaveBeenCalled();
    
   });
   
   
   it("'redirect' should call 'track' on '$state'", function() {
       spyOn(state, 'go');
       
       ctrl.track();
       expect(state.go).toHaveBeenCalled();
      
   });
   
   it("'redirect' should call 'invite' on '$state'", function() {
       spyOn(state, 'go');
       
       ctrl.invite();
       expect(state.go).toHaveBeenCalled();
       
   });
});
*/