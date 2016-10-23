(function(angular){
    'use strict';

    let invitationUrl = '/MLL/InviteServlet';

    let authDetailsKey = 'mllApp.authDetails';

    let tokenActionTypes = {
        validate: 'validate',
        generate: 'generate'
    };

    angular
        .module('mllApp.shared')
        .constant('invitationUrl', invitationUrl)
        .constant('authDetailsKey', authDetailsKey)
        .constant('tokenActionTypes', tokenActionTypes);
})(window.angular);