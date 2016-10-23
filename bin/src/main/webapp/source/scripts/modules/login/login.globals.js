(function(angular){
    'use strict';

    let loginUrl = '/MLL/LoginServlet';

    angular
        .module('mllApp.login')
        .constant('loginUrl', loginUrl);
})(window.angular);