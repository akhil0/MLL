(function(angular){
    'use strict';

    let musicianUrl = '/MLL/MusicianHomePageServlet';
    let searchUrl = '/MLL/SearchServlet/?searchTitle=';
    let deleteUrl = '/MLL/DeleteAssetServlet/?assetId=';
    let editUrl = '/MLL/UpdateSongMetaDataServlet';
    
    //let musicGenres = [ 'Alternative', 'Blues', 'Children\'s Music', 'Christian & Gospel', 'Comedy', 'Classical',
    //    'Country', 'Dance', 'Electronic', 'Hip - Hop / Rap', 'Pop', 'Jazz', 'Latino', 'R & B / Soul', 'Reggae',
    //    'Metal', 'Rock', 'Singer / Songwriter', 'Folk / Americana', 'Funk' ].sort();

    let musicForms = {
            currentId: 0,
            submitFormId: 4,
            data: [
            	{ title: 'Recording Information', isActive: true },
                { title: 'Song Ownership Information', isActive: false },
                { title: 'Recording Upload', isActive: false },
                { title: 'Summary', isActive: false }
//            	{ title: 'Ownership Information', isActive: true },
//                { title: 'General Information', isActive: false },
//                { title: 'Song Selection', isActive: false },
//                { title: 'Sound Ownership Information', isActive: false },
//                { title: 'Summary', isActive: false }
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
    
    angular
        .module('mllApp.home')
        .constant('musicianUrl', musicianUrl)
    	.constant('searchUrl', searchUrl)
    	.constant('deleteUrl', deleteUrl)
    	.constant('editUrl', editUrl)
    	.constant('musicData', musicData)
    	.constant('musicForms', musicForms);
})(window.angular);