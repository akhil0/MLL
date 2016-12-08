(function(angular){
    'use strict';

    let musicianUrl = '/MLL/MusicianHomePageServlet';
    let searchUrl = '/MLL/SearchServlet/?searchTitle=';
    let deleteUrl = '/MLL/DeleteAssetServlet/?assetId=';
    let editUrl = '/MLL/UpdateSongMetaDataServlet';
    

    let musicForms = {
            currentId: 0,
            submitFormId: 4,
            data: [
            	{ title: 'Recording Information', isActive: true },
            	{ title: 'Master Owner Information', isActive: false },
                { title: 'Song Ownership Information', isActive: false },
                { title: 'Recording Upload', isActive: false },
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
    
    angular
        .module('mllApp.home')
        .constant('musicianUrl', musicianUrl)
    	.constant('searchUrl', searchUrl)
    	.constant('deleteUrl', deleteUrl)
    	.constant('editUrl', editUrl)
    	.constant('musicData', musicData)
    	.constant('musicForms', musicForms);
})(window.angular);