'use strict';

describe('Sidebar Service:', function () {
    beforeEach(module('ui.router'));

    beforeEach(module('mllApp.sidebar'));

    let ctrl, state,scope,location,auth;

    beforeEach(inject(function($controller) {
        ctrl = $controller('SidebarController', {});

    }));

    beforeEach(inject(function(authenticationService) {
        auth = authenticationService;
    }));
    
    beforeEach(inject(function($state) {
        state = $state;
    }));
    
    
   it("'redirect' should call 'home' on '$state'", function() {
       spyOn(state, 'go');
       var userId = auth.details.data.id
       ctrl.home();
       expect(state.go).toHaveBeenCalled();
       expect(state.go).toHaveBeenCalledWith('home', { id: userId });
   });
   
   
   it("'redirect' should call 'home' on '$state'", function() {
       spyOn(state, 'go');
       var userId = auth.details.data.id
       ctrl.home();
       expect(state.go).toHaveBeenCalled();
       expect(state.go).toHaveBeenCalledWith('track', { id: userId });
   });
   
   it("'redirect' should call 'home' on '$state'", function() {
       spyOn(state, 'go');
       var userId = auth.details.data.id
       ctrl.home();
       expect(state.go).toHaveBeenCalled();
       expect(state.go).toHaveBeenCalledWith('userDetails', { id: userId });
   });
});
