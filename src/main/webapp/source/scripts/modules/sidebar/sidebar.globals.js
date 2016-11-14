(function(angular){
    'use strict';

    let homeLink = { text: 'Home' };

    let inviteLink = { text: 'Invite' };

    let trackLink = { text: 'Track' };

    let faqslink = { text: 'FAQs'};

	let about = { text: 'About'};
    

    angular
        .module('mllApp.sidebar')
        .constant('homeLink', homeLink)
        .constant('inviteLink', inviteLink)
        .constant('trackLink', trackLink)
        .constant('faqslink', faqslink)
        .constant('about', about);
})(window.angular);