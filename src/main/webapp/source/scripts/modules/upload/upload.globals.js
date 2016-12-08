(function(angular){
    'use strict';

    let musicFormats = ['.mp3', '.wav'];

    let musicGenres = [ 'Alternative', 'Blues', 'Children\'s Music', 'Christian & Gospel', 'Comedy', 'Classical',
        'Country', 'Dance', 'Electronic', 'Hip - Hop / Rap', 'Pop', 'Jazz', 'Latino', 'R & B / Soul', 'Reggae',
        'Metal', 'Rock', 'Singer / Songwriter', 'Folk / Americana', 'Funk' ].sort();

    let musicForms = {
        currentId: 0,
        submitFormId: 4,
        data: [
        	{ title: 'Recording Information', isActive: true },
        	{ title: 'Master Owner Information', isActive: false },
            { title: 'Song Ownership Information', isActive: false },
            { title: 'Recording Upload', isActive: false },
            { title: 'Summary', isActive: false }
        	
//        	{ title: 'Ownership Information', isActive: true },
//            { title: 'General Information', isActive: false },
//            { title: 'Song Selection', isActive: false },
//            { title: 'Sound Ownership Information', isActive: false },
//            { title: 'Summary', isActive: false }
        ]
    };

    let musicData = {
        fileInformation: { name: '', file: null },
        generalInformation: {
            title: '',
            artists: [
                { name: '' }
            ],
            beatRate: 0,
            primaryGenre: '',
            secondaryGenre: ''
        },
        ownershipInformation: {
            songwriters: [
                { name: '', primaryEmail: '', primaryPhone: '', secondaryPhone: '', contribution:'', ownershipPercent: 0, musicianRole:'' }
            ],
            copyright: '',
            pubCompany: '',
            pro: ''
        },
        soundInformation: {
            soundOwners: [
                { name: '', primaryEmail: '', primaryPhone: '', secondaryPhone: '', contribution:'', ownershipPercent: 0, musicianRole:'' }
            ]
        },
        serverInformation: { }
    };

    let musicSize = 10 * 1024 * 1024;

    let musicContributions = ['Lyrics', 'Music', 'Lyrics and Music'].sort();
    
    let trackTypes = ['Vocal', 'Instrumental', 'Vocal/Instrumental'].sort();
    
    let musicianRoles = ['Vocals', 'Backup Vocals', 'Percussion', 'Guitar', 'Drums', 'Keyboard'].sort();
    
    let musicUrl = {
        direct: '/MLL/SubmissionServlet',
        cloud: '/MLL/SubmissionServlet'
    };

    angular
        .module('mllApp.upload')
        .constant('musicFormats', musicFormats)
        .constant('musicGenres', musicGenres)
        .constant('musicForms', musicForms)
        .constant('musicData', musicData)
        .constant('musicSize', musicSize)
        .constant('musicContributions', musicContributions)
        .constant('trackTypes', trackTypes)
        .constant('musicianRoles', musicianRoles)
        .constant('musicUrl', musicUrl);
})(window.angular);