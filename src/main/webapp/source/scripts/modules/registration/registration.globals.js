(function(angular){
    'use strict';

    let registrationUrl = '/MLL/RegistrationServlet';

    let registrationTypes = {
        user: 'user',
        musician: 'musician'
    };

    let musicGenres = [ 'Alternative', 'Blues', 'Children\'s Music', 'Christian & Gospel', 'Comedy', 'Classical',
        'Country', 'Dance', 'Electronic', 'Hip - Hop / Rap', 'Pop', 'Jazz', 'Latino', 'R & B / Soul', 'Reggae',
        'Metal', 'Rock', 'Singer / Songwriter', 'Folk / Americana', 'Funk' ].sort();

    let universityAffiliations = [ 'Undergraduate Student', 'Graduate Student', 'Faculty/Stuff' ];

    let userGenders = [ 'Male', 'Female', 'Not Specified' ];

    let universityColleges = [ 'College of Arts, Media and Design', 'D\'Amore-McKim School of Business',
        'College of Computer and Information Science', 'College of Engineering', 'Bouvé College of Health Sciences',
        'School of Law', 'College of Professional Studies', 'College of Science',
        'College of Social Sciences and Humanities' ].sort();

    angular
        .module('mllApp.registration')
        .constant('registrationUrl', registrationUrl)
        .constant('registrationTypes', registrationTypes)
        .constant('universityAffiliations', universityAffiliations)
        .constant('universityColleges',universityColleges)
        .constant('userGenders', userGenders)
        .constant('musicGenres', musicGenres);
})(window.angular);