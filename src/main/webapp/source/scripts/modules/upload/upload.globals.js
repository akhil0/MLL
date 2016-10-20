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
            { title: 'License Agreement', isActive: true },
            { title: 'Song Selection', isActive: false },
            { title: 'General Information', isActive: false },
            { title: 'Ownership Information', isActive: false },
            { title: 'Sound Ownership Information', isActive: false },
            { title: 'Summary', isActive: false }
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
                { name: '', primaryEmail: '', primaryPhone: '', secondaryPhone: '' }
            ],
            copyright: '',
            pubCompany: '',
            pro: ''
        },
        soundInformation: {
            soundOwners: [
                { name: '', primaryEmail: '', primaryPhone: '', secondaryPhone: '' }
            ]
        },
        serverInformation: { }
    };

    let musicSize = 10 * 1024 * 1024;

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
        .constant('musicUrl', musicUrl);
})(window.angular);