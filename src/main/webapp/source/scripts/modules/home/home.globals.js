(function(angular){
    'use strict';

    let musicianUrl = '/MLL/MusicianHomePageServlet';

    angular
        .module('mllApp.home')
        .constant('musicianUrl', musicianUrl);
})(window.angular);