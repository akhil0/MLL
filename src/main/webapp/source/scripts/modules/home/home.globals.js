(function(angular){
    'use strict';

    let musicianUrl = '/MLL/MusicianHomePageServlet';
    let searchUrl = '/MLL/SearchServlet/?searchTitle=';

    angular
        .module('mllApp.home')
        .constant('musicianUrl', musicianUrl)
    	.constant('searchUrl', searchUrl);
})(window.angular);