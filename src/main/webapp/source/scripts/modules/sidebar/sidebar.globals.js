(function(angular){
    'use strict';

    let homeLink = { text: 'Home' };

    let inviteLink = { text: 'Invite' };

    let trackLink = { text: 'Track' };

    let faqslink = { text: 'FAQs'};

	let about = { text: 'About'};
	
	let uploadLink = { text: 'Upload', href: 'musicianUpload' };
	
	let profileLink = { text: 'Profile', href: 'musicianProfile' };
    

    angular
        .module('mllApp.sidebar')
        .constant('homeLink', homeLink)
        .constant('inviteLink', inviteLink)
        .constant('trackLink', trackLink)
        .constant('uploadLink', uploadLink)
        .constant('profileLink', profileLink)
        .constant('faqslink', faqslink)
        .constant('about', about);
})(window.angular);