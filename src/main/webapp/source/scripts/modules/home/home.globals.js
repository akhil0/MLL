(function(angular){
    'use strict';

    let musicianUrl = '/MLL/MusicianHomePageServlet';
    let searchUrl = '/MLL/SearchServlet/?searchTitle=';
    let deleteUrl = '/MLL/DeleteAssetServlet/?assetId=';
    let editUrl = '/MLL/UpdateSongMetaDataServlet';
    let addBandUrl = '/MLL/ProfileServlet';
    let getBandDetailsUrl = '/MLL/BandServlet/?bandId=';
    

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
    
    let musicContributions = ['Lyrics', 'Music', 'Lyrics and Music'].sort();
    let bandData = { ownershipInformation: { 
    		bandName:'',
    		songwriters: [{ 
    			name: '', primaryEmail: '', primaryPhone: '', secondaryPhone: '', contribution:'', ownershipPercent: 0, musicianRole:'' 
    		}
        ]}
};
    let musicianRoles = ['Vocals', 'Backup Vocals', 'Percussion', 'Guitar', 'Drums', 'Keyboard'].sort();
    
    angular
        .module('mllApp.home')
        .constant('musicianUrl', musicianUrl)
    	.constant('searchUrl', searchUrl)
    	.constant('deleteUrl', deleteUrl)
    	.constant('editUrl', editUrl)
    	.constant('addBandUrl', addBandUrl)
    	.constant('musicData', musicData)
    	.constant('bandData', bandData)
    	.constant('musicContributions', musicContributions)
    	.constant('musicianRoles', musicianRoles)
    	.constant('getBandDetailsUrl', getBandDetailsUrl)
    	.constant('musicForms', musicForms);
})(window.angular);