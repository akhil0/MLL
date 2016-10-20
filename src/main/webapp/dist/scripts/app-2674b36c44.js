(function (angular) {
    'use strict';

    angular.module('mllApp',
        [
            'mllApp.shared', 'mllApp.header', 'mllApp.footer', 'mllApp.home', 'mllApp.login', 'mllApp.registration',
            'mllApp.upload', 'ui.router'
        ]);
})(window.angular);
(function(angular) {
    'use strict';

    angular
        .module('mllApp')
        .run(run);

    run.$inject = ['authenticationService'];

    function run(authenticationService) {
        authenticationService.check();
    }
})(window.angular);
(function(angular) {
    'use strict';

    angular
        .module('mllApp')
        .config(config);

    function config($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('home', {
                url: '/',
                views: {
                    left: { template: '' },
                    center: { template: '' },
                    right: { template: '' }
                }
            })
            .state('userRegistration', {
                url: '/user/registration/token/:token',
                views: {
                    left: { template: '' },
                    center: {
                        controller: 'UserRegistrationController as ctrl',
                        templateProvider: function($templateCache) {
                            return $templateCache.get('user-registration.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    token: function($state, $stateParams, $q, inviteTokenService, registrationTypes) {
                        let deferred = $q.defer();

                        inviteTokenService
                            .validateToken({ inviteType: registrationTypes.user, token: $stateParams.token })
                            .then((response) => {
                                if (response.data.isValid) deferred.resolve($stateParams.token);

                                else {
                                    $state.go('home');
                                    deferred.reject();
                                }
                            });

                        return deferred.promise;
                    }
                }
            })
            .state('musicianRegistration', {
                url: '/musician/registration/token/:token',
                views: {
                    left: { template: '' },
                    center: {
                        controller: 'MusicianRegistrationController as ctrl',
                        templateProvider: function($templateCache) {
                            return $templateCache.get('musician-registration.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    token: function ($state, $stateParams, $q, inviteTokenService, registrationTypes) {
                        let deferred = $q.defer();

                        inviteTokenService
                            .validateToken({ inviteType: registrationTypes.musician, token: $stateParams.token })
                            .then((response) => {
                                if (response.data.isValid) deferred.resolve($stateParams.token);

                                else {
                                    $state.go('home');
                                    deferred.reject();
                                }
                            });

                        return deferred.promise;
                    }
                }
            })
            .state('user', {
                url: '/user/profile/id/:id',
                views: {
                    left: { template: '' },
                    center: { template: '' },
                    right: {
                        controller: 'UserFeaturesController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('user-profile-right.view.html');
                        }
                    }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.browse) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve(+$stateParams.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('musician', {
                url: '/musician/profile/id/:id',
                views: {
                    left: { template: '' },
                    center: {
                        controller: 'MusicianFeaturesController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('musician-profile-center.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (authenticationService.details.data.id !== +$stateParams.id &&
                                     authenticationService.details.data.permissions.upload) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve(+$stateParams.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('musicianUpload', {
                url: '/musician/upload',
                views: {
                    left: { template: '' },
                    center: {
                        controller: 'MusicianUploadController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('musician-upload-center.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.upload) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve(+authenticationService.details.data.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('login', {
                url: '/login',
                views: {
                    left: { template: '' },
                    center: {
                        controller: 'LoginController as ctrl',
                        templateProvider: function($templateCache) {
                            return $templateCache.get('login-central.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    userId: function($state, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (authenticationService.details.isAuth) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve();
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('music', {
                url: '/music',
                views: {
                    left: { template: '' },
                    center: { template:
                        `<div class="well well-lg">
                            <h4>
                                Oops... This feature is still under development. We do appreciate your patience.
                            </h4>
                        </div>`
                    },
                    right: { template: '' }
                },
                resolve: {
                    data: function($state, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.browse) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve();
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('people', {
                url: '/people',
                views: {
                    left: { template: '' },
                    center: { template:
                        `<div class="well well-lg">
                            <h4>
                                Oops... This feature is still under development. We do appreciate your patience.
                            </h4>
                        </div>`
                    },
                    right: { template: '' }
                },
                resolve: {
                    data: function($state, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.browse) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve();
                        }, 0);

                        return deferred.promise;
                    }
                }
            });
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp')
        .controller('ApplicationController', ApplicationController);

    function ApplicationController() { }
})(window.angular);

(function (angular) {
    'use strict';

    angular
        .module('mllApp')
        .directive('mllApplication', mllApplication);

    function mllApplication() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'ApplicationController',
            controllerAs: 'ctrl',
            templateUrl: 'app.template.html'
        };
    }

})(window.angular);
(function (angular) {
    'use strict';

    angular.module('mllApp.header', ['mllApp.shared', 'mllApp.templates']);
})(window.angular);
(function(angular){
    'use strict';

    let homeLink = { text: 'Home' };

    let loginLink = { text: 'Log In', href: 'login' };

    let logoutLink = { text: 'Log Out' };

    let uploadLink = { text: 'Upload', href: 'musicianUpload' };

    let navigationLinks = [
        { text: 'People', href: 'people' },
        { text: 'Music', href: 'music' }
    ];

    angular
        .module('mllApp.header')
        .constant('homeLink', homeLink)
        .constant('loginLink', loginLink)
        .constant('logoutLink', logoutLink)
        .constant('uploadLink', uploadLink)
        .constant('navigationLinks', navigationLinks);
})(window.angular);
(function (angular){
    'use strict';

    angular
        .module('mllApp.header')
        .controller('HeaderController', HeaderController);

    HeaderController.$inject =
        ['$state', 'homeLink', 'loginLink', 'logoutLink', 'uploadLink', 'navigationLinks', 'authenticationService'];

    function HeaderController($state, homeLink, loginLink, logoutLink, uploadLink, navLinks, authenticationService) {

        this.authService = authenticationService;

        this.homeLink = homeLink;
        this.loginLink = loginLink;
        this.logoutLink = logoutLink;
        this.uploadLink = uploadLink;

        this.navLinks = navLinks;

        this.logout = () => {
            this.authService.clear();

            $state.go(loginLink.href);
        };

        this.home = () => {
            $state.go(this.authService.details.data.type, { id: this.authService.details.data.id });
        };

        this.upload = () => {
            $state.go(uploadLink.href, {}, { reload: true });
        };
    }
})(window.angular);

(function (angular){
    'use strict';

    angular
        .module('mllApp.header')
        .directive('mllHeader', mllHeader);

    function mllHeader() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'HeaderController',
            controllerAs: 'ctrl',
            templateUrl: 'header.template.html'
        };
    }

})(window.angular);
(function (angular) {
    'use strict';

    angular.module('mllApp.footer', ['mllApp.templates']);

})(window.angular);
(function (angular) {
    'use strict';

    angular.module('mllApp.login', ['mllApp.shared', 'mllApp.templates']);
})(window.angular);
(function(angular){
    'use strict';

    let loginUrl = '/MLL/LoginServlet';

    angular
        .module('mllApp.login')
        .constant('loginUrl', loginUrl);
})(window.angular);
(function(angular){
    'use strict';

    angular
        .module('mllApp.login')
        .controller('LoginController', LoginController);

    function LoginController() { }
})(window.angular);
(function(angular) {
    'use strict';

    angular
        .module('mllApp.login')
        .factory('loginService', loginService);

    loginService.$inject = ['$http', 'authenticationService', 'loginUrl'];

    function loginService($http, authenticationService, loginUrl) {
        return {
            login: login
        };

        function login(data) {
            return $http.post(loginUrl, data)
                .then((response) => {
                    if (response.data.isValidUser) authenticationService.change(response.data);

                    return response.data;
                })
                .catch((rejection) => rejection);
        }
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.login')
        .controller('CommonLoginFormController', CommonLoginFormController);

    CommonLoginFormController.$inject = ['$state', 'loginService'];

    function CommonLoginFormController($state, loginService) {
        this.service = loginService;

        this.login = () => {
            if (this.loginForm.$invalid) this.loginForm.$submitted = true;

            else {
                this.service.login(this.data)
                    .then((data) => {
                        this.processResponse(data);
                    })
                    .catch(() => { });
            }
        };

        this.processResponse = (data) => {
            if (data.isValidUser) this.redirect(data.userId, data.type);

            else this.displayError(data.errorMessage);
        };

        this.redirect = (id, type) => {
            $state.go(type, { id: id });
        };

        this.displayError = (errorMessage) => {
            this.loginForm.$serverError = true;
            this.errorMessage = errorMessage;
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.login')
        .directive('mllCommonLoginForm', mllCommonLoginForm);

    function mllCommonLoginForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'CommonLoginFormController',
            controllerAs: 'ctrl',
            templateUrl: 'common-login-form.template.html'
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular.module('mllApp.registration', ['mllApp.shared', 'mllApp.templates']);
})(window.angular);
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
(function(angular){
    'use strict';

    angular
        .module('mllApp.registration')
        .controller('UserRegistrationController', UserRegistrationController);

    UserRegistrationController.$inject = ['token'];

    function UserRegistrationController(token) {
        this.data = { inviteToken: token };
    }
})(window.angular);
(function(angular){
    'use strict';

    angular
        .module('mllApp.registration')
        .controller('MusicianRegistrationController', MusicianRegistrationController);

    MusicianRegistrationController.$inject = ['token'];

    function MusicianRegistrationController(token) {
        this.data = { inviteToken: token };
    }
})(window.angular);
(function(angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .factory('registrationService', registrationService);

    registrationService.$inject = ['$http', 'authenticationService', 'registrationUrl'];

    function registrationService($http, authenticationService, registrationUrl) {
        return {
            register: register
        };

        function register(data) {
            return $http.post(registrationUrl, data)
                .then((response) => {
                    if (response.data.isRegistered) authenticationService.change(response.data);

                    return response.data;
                })
                .catch((rejection) => rejection);
        }
    }
})(window.angular);

(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .controller('UserRegistrationFormController', UserRegistrationFormController);

    UserRegistrationFormController.$inject =
        [
            '$state', 'registrationService', 'musicGenres', 'userGenders',
            'universityColleges', 'universityAffiliations', 'registrationTypes'
        ];

    function UserRegistrationFormController($state, regService, genres, genders, colleges, affiliations, regTypes) {
        this.genres = genres;
        this.genders = genders;
        this.colleges = colleges;
        this.affiliations = affiliations;

        this.data = {
            type: regTypes.user,
            token: this.inviteToken
        };

        this.register = () => {
            if (this.registrationForm.$invalid) this.registrationForm.$submitted = true;

            else
                regService.register(this.data)
                    .then((response) => this.processResponse(response))
                    .catch((rejection) => this.displayError(rejection));
        };

        this.processResponse = (data) => {
            if (data.isRegistered) this.redirect(data.userId);

            else this.displayError(data.errorMessage);
        };

        this.redirect = (id) => $state.go(regTypes.user, { id: id });

        this.displayError = (errorMessage) => {
            this.registrationForm.$serverError = true;
            this.errorMessage = errorMessage;
        };
    }
})(window.angular);

(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .directive('mllUserRegistrationForm', mllUserRegistrationForm);

    function mllUserRegistrationForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'UserRegistrationFormController',
            controllerAs: 'ctrl',
            templateUrl: 'user-registration-form.template.html',
            bindToController: {
                inviteToken: '@'
            }
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .controller('MusicianRegistrationFormController', MusicianRegistrationFormController);

    MusicianRegistrationFormController.$inject = ['$state', 'registrationService', 'registrationTypes'];

    function MusicianRegistrationFormController($state, registrationService, registrationTypes) {
        this.data = {
            type: registrationTypes.musician,
            token: this.inviteToken
        };

        this.register = () => {
            if (this.registrationForm.$invalid) this.registrationForm.$submitted = true;

            else
                registrationService.register(this.data)
                    .then((response) => this.processResponse(response))
                    .catch((rejection) => this.displayError(rejection));
        };

        this.processResponse = (data) => {
            if (data.isRegistered) this.redirect(data.userId);

            else this.displayError(data.errorMessage);
        };

        this.redirect = (id) => $state.go(registrationTypes.musician, { id: id });

        this.displayError = (errorMessage) => {
            this.registrationForm.$serverError = true;
            this.errorMessage = errorMessage;
        };
    }
})(window.angular);

(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .directive('mllMusicianRegistrationForm', mllMusicianRegistrationForm);

    function mllMusicianRegistrationForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'MusicianRegistrationFormController',
            controllerAs: 'ctrl',
            templateUrl: 'musician-registration-form.template.html',
            bindToController: {
                inviteToken: '@'
            }
        };
    }
})(window.angular);

(function(angular){
    'use strict';

    angular.module('mllApp.home', ['mllApp.shared', 'mllApp.templates', 'mllApp.upload', 'ui.bootstrap']);
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('UserFeaturesController', UserFeaturesController);

    UserFeaturesController.$inject = ['userId'];

    function UserFeaturesController(userId) {
        this.userId = userId;
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('MusicianFeaturesController', MusicianFeaturesController);

    MusicianFeaturesController.$inject = ['userId'];

    function MusicianFeaturesController(userId) {
        this.userId = userId;
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('InviteFormController', InviteFormController);

    InviteFormController.$inject = ['$timeout', 'inviteTokenService'];

    function InviteFormController($timeout, inviteTokenService) {
        this.data = {
            userId: +this.userId
        };

        this.types = [
            { label: 'General User', value: 'user' },
            { label: 'Musician', value: 'musician' }
        ];

        this.invite = () => {
            if (this.form.$invalid) this.form.$submitted = true;

            else
                inviteTokenService
                    .generateToken(this.data)
                    .then((response) => {
                        this.message = response.data.message;
                        this.isGenerated = response.data.isGenerated;

                        this.isOpen = true;

                        this.data.type = '';
                        this.data.email = '';
                        this.form.$submitted = false;

                        $timeout(() => this.isOpen = false, 5000);
                    })
                    .catch((rejection) => rejection);
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .directive('mllInviteForm', mllInviteForm);

    function mllInviteForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'InviteFormController',
            controllerAs: 'ctrl',
            templateUrl: 'invite-form.template.html',
            bindToController: {
                userId: '@'
            }
        };
    }
})(window.angular);


(function (angular) {
    'use strict';

    angular.module('mllApp.picker', ['mllApp.templates']);

})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .controller('HddFileReaderController', HddFileReaderController);

    function HddFileReaderController() {
        this.change = (e) => {
            let file = e.target.files[0];

            this.onSelect({ fileInformation: { isDirect: true, file: file } });
        };
    }
})(window.angular);

(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .directive('mllHddFileReader', mllHddFileReader);

    function mllHddFileReader() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'HddFileReaderController',
            controllerAs: 'ctrl',
            bindToController: {
                onSelect: '&'
            },
            templateUrl: 'hdd-file-reader.template.html',
            link: _link
        };

        function _link(scope, elem, attrs, ctrl) {
            let input = elem.find('input[type="file"]');

            input.on('change', (e) => {
                scope.$apply(() => ctrl.change(e));
            });
        }
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .controller('DropboxFileReaderController', DropboxFileReaderController);

    function DropboxFileReaderController() {

    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .directive('mllDropboxFileReader', mllDropboxFileReader);

    function mllDropboxFileReader() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: function() {},
            controllerAs: 'ctrl',
            bindToController: {
                formats: '=',
                onSelect: '&'
            },
            templateUrl: 'dropbox-file-reader.template.html',
            link: link
        };

        function link(scope, elem, attr, ctrl) {
            ctrl.select = () => {
                let options = {
                    success: (files) => {
                        scope.$apply(() =>
                            ctrl.onSelect({ fileInformation : { isDirect: false, file: files[0] } }));
                    },
                    linkType: 'direct',
                    multiselect: false,
                    extensions: ctrl.formats
                };

                Dropbox.choose(options);
            };
        }
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .controller('FileSelectorController', FileSelectorController);

    function FileSelectorController() {
        this.selectHdd = (fileInformation) => {
            this.selectedFile = fileInformation.file.name;
            this.onSelectHdd({ fileInformation: fileInformation });
        };

        this.selectDropbox = (fileInformation) => {
            this.selectedFile = fileInformation.file.name;
            this.onSelectDropbox({ fileInformation: fileInformation });
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.picker')
        .directive('mllFileSelector', mllFileSelector);

    function mllFileSelector() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'FileSelectorController',
            controllerAs: 'ctrl',
            bindToController: {
                formats: '=',
                onSelectHdd: '&',
                onSelectDropbox: '&'
            },
            templateUrl: 'file-selector.template.html'
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular.module('mllApp.upload', ['mllApp.shared', 'mllApp.picker', 'mllApp.templates', 'ui.bootstrap']);

})(window.angular);
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
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicianUploadController', MusicianUploadController);

    MusicianUploadController.$inject = ['userId'];

    function MusicianUploadController(userId) {
        this.userId = userId;
    }
})(window.angular);
(function(angular){
    'use strict';

    angular
        .module('mllApp.upload')
        .factory('musicUploadService', musicUploadService);

    musicUploadService.$inject = ['$http', 'musicUrl'];

    function musicUploadService($http, musicUrl) {
        return {
            submitCloud: submitCloud,
            submitDirect: submitDirect
        };

        function submitCloud (data) {
            return $http.post(musicUrl.direct, data);
        }

        function submitDirect(data, fileProp) {
            let fd = new FormData();

            Object.keys(data).forEach((key) =>
                fd.append(key, (key === fileProp) ? data[key] : JSON.stringify(data[key])));

            return $http.post(musicUrl.cloud, fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
        }
    }

})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicAgreementFormController', MusicAgreementFormController);

    function MusicAgreementFormController() {
        this.form = { invalid: true, submitted: false };

        this.validate = (isChecked) => this.form.invalid = !isChecked;

        this.submit = () => {
            if (this.form.invalid) this.form.submitted = true;
            else this.onNext();
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .directive('mllMusicAgreementForm', mllMusicAgreementForm);

    function mllMusicAgreementForm() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'MusicAgreementFormController',
            controllerAs: 'ctrl',
            bindToController: {
                onNext: '&'
            },
            templateUrl: 'music-agreement-form.template.html'
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicFileFormController', MusicFileFormController);

    MusicFileFormController.$inject = ['musicFormats', 'musicSize'];

    function MusicFileFormController(musicFormats, musicSize) {
        this.size = angular.copy(musicSize);
        this.formats = angular.copy(musicFormats);

        // Replication of Angular Form Behaviour
        this.form = {
            invalid: true,
            submitted: false,
            errors: {
                size: false,
                format: false,
                required: true
            }
        };

        this.validateFormat = (fileName) =>
            this.formats.includes(fileName.slice(fileName.lastIndexOf('.')));

        this.validateSize = (size) => size <= this.size;

        this.selectHdd = (fileInformation) => {
            this.form.errors.size = !this.validateSize(fileInformation.file.size);
            this.form.errors.format = !this.validateFormat(fileInformation.file.name);
            this.form.errors.required = false;

            this.form.invalid = this.form.errors.size || this.form.errors.format;

            if (!this.form.invalid) this.data = fileInformation;

        };

        this.selectDropbox = (fileInformation) => {
            this.form.invalid = false;

            this.form.errors.size = false;
            this.form.errors.format = false;
            this.form.errors.required = false;

            this.data = fileInformation;
        };

        this.submit = () => {
            if (this.form.invalid) this.form.submitted = true;
            else {
                if (this.data.file === null) {
                    this.form.invalid = true;
                    this.form.submitted = true;
                    this.form.errors.required = true;
                }
                else this.onNext();
            }
        };

        this.reset = () => this.onPrevious();
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .directive('mllFileForm', mllFileForm);

    function mllFileForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'MusicFileFormController',
            controllerAs: 'ctrl',
            bindToController: {
                data: '=',
                onNext: '&',
                onPrevious: '&'
            },
            templateUrl: 'music-file-form.template.html'
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicGeneralInformationFormController', MusicGeneralInformationFormController);

    MusicGeneralInformationFormController.$inject = ['musicGenres'];

    function MusicGeneralInformationFormController(musicGenres) {

        this.genres = angular.copy(musicGenres);

        this.addArtist = () => this.data.artists.push({ name: '' });

        this.removeArtist = (i) => this.data.artists.splice(i, 1);

        this.selectGenre = (genre) => { if(!genre) this.data.secondaryGenre = null; };

        this.submit = () => {
            if (this.generalForm.$invalid) this.generalForm.$submitted = true;
            else this.onNext();
        };

        this.reset = () => this.onPrevious();

    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .directive('mllMusicGeneralInformationForm', mllMusicGeneralInformationForm);

    function mllMusicGeneralInformationForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'MusicGeneralInformationFormController',
            controllerAs: 'ctrl',
            bindToController: {
                data: '=',
                onNext: '&',
                onPrevious:'&'
            },
            templateUrl: 'music-general-information-form.template.html'
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicOwnerInformationFormController', MusicOwnerInformationFormController);

    function MusicOwnerInformationFormController() {

        this.addWriter = () => this.data.songwriters.push({
            name: '', primaryPhone: '', secondaryPhone: '', primaryEmail: '', secondaryEmail: ''
        });

        this.removeWriter = (i) => this.data.songwriters.splice(i, 1);

        this.submit = () => {
            if (this.ownerForm.$invalid) this.ownerForm.$submitted = true;
            else this.onNext();
        };

        this.reset = () => this.onPrevious();

    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .directive('mllMusicOwnerInformationForm', mllMusicOwnerInformationForm);

    function mllMusicOwnerInformationForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'MusicOwnerInformationFormController',
            controllerAs: 'ctrl',
            bindToController: {
                data: '=',
                onNext: '&',
                onPrevious:'&'
            },
            templateUrl: 'music-owner-information-form.template.html'
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicSoundInformationFormController', MusicSoundInformationFormController);

    function MusicSoundInformationFormController() {

        this.addOwner = () => this.data.soundOwners.push({
            name: '', primaryPhone: '', secondaryPhone: '', primaryEmail: '', secondaryEmail: ''
        });

        this.removeOwner = (i) => this.data.soundOwners.splice(i, 1);

        this.submit = () => {
            if (this.soundForm.$invalid) { this.soundForm.$submitted = true; }
            else this.onNext();
        };

        this.reset = () => this.onPrevious();

    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .directive('mllMusicSoundInformationForm', mllMusicSoundInformationForm);

    function mllMusicSoundInformationForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'MusicSoundInformationFormController',
            controllerAs: 'ctrl',
            bindToController: {
                data: '=',
                onNext: '&',
                onPrevious: '&'
            },
            templateUrl: 'music-sound-information-form.template.html'
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicSummaryFormController', MusicSummaryFormController);

    MusicSummaryFormController.$inject = ['$state', 'authenticationService'];

    function MusicSummaryFormController($state, authenticationService) {
        this.authService = authenticationService;

        this.quit = () => {
            $state.go(this.authService.details.data.type, { id: this.authService.details.data.id });
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .directive('mllMusicSummaryForm', mllMusicSummaryForm);

    function mllMusicSummaryForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'MusicSummaryFormController',
            controllerAs: 'ctrl',
            bindToController: {
                data: '=',
                onAgain: '&'
            },
            templateUrl: 'music-summary-form.template.html'
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicFileUploaderController', MusicFileUploaderController);

    MusicFileUploaderController.$inject = ['musicForms', 'musicData', 'musicUploadService'];

    function MusicFileUploaderController(musicForms, musicData, musicUploadService, authService) {
        this.forms = angular.copy(musicForms);

        this.data = angular.copy(musicData);
        this.data.generalInformation.userId = this.userId;

        this.uploadService = musicUploadService;
        this.authService = authService;

        this.next = () => {
            this.forms.data[this.forms.currentId].isActive = false;

            this.forms.currentId++;

            this.forms.data[this.forms.currentId].isActive = true;
        };

        this.previous = () => {
            this.forms.data[this.forms.currentId].isActive = false;

            this.forms.currentId--;

            this.forms.data[this.forms.currentId].isActive = true;
        };

        this.prepare = (data) => {
            let obj = {
                generalInformation: data.generalInformation,
                ownershipInformation: data.ownershipInformation,
                soundInformation: data.soundInformation
            };

            obj.isDirect = data.fileInformation.isDirect;
            obj.file = (obj.isDirect) ? data.fileInformation.file : data.fileInformation.file.link;

            return obj;
        };

        this.submit = () => {
            let data = this.prepare(this.data);

            let promise = (data.isDirect) ? this.uploadService.submitDirect(data, 'file')
                : this.uploadService.submitCloud(data);

            promise.then((response) => {
                this.data.serverInformation.isUploaded = response.data.isUploaded;
                this.data.serverInformation.message = response.data.message;
            })
            .catch((reject) => {
                this.data.serverInformation.isUploaded = false;
                this.data.serverInformation.message = reject;
            })
            .finally(() => this.next());
        };

        this.again = () => {
            this.forms.data[this.forms.currentId].isActive = false;

            this.forms.currentId = 0;

            this.forms.data[this.forms.currentId].isActive = true;

            /* Clear file information */
            this.data.fileInformation.file = null;

            /* Clear general information */
            this.data.generalInformation.title = '';
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .directive('mllMusicFileUploader', mllMusicFileUploader);

    function mllMusicFileUploader() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'MusicFileUploaderController',
            controllerAs: 'ctrl',
            templateUrl: 'music-file-uploader.template.html',
            bindToController: {
                userId: '='
            }
        };
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular.module('mllApp.shared', ['mllApp.templates', 'ui.bootstrap', 'ngCookies']);

})(window.angular);
(function(angular){
    'use strict';

    let invitationUrl = '/MLL/InviteServlet';

    let authDetailsKey = 'mllApp.authDetails';

    let tokenActionTypes = {
        validate: 'validate',
        generate: 'generate'
    };

    angular
        .module('mllApp.shared')
        .constant('invitationUrl', invitationUrl)
        .constant('authDetailsKey', authDetailsKey)
        .constant('tokenActionTypes', tokenActionTypes);
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .controller('ScrollableAgreementController', ScrollableAgreementController);

    function ScrollableAgreementController() {
        this.agree = () => this.onAgree({ isChecked: this.isChecked });
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .directive('mllScrollableAgreement', mllScrollableAgreement);

    function mllScrollableAgreement() {
        return {
            restrict: 'AE',
            transclude: true,
            scope: {},
            controller: 'ScrollableAgreementController',
            controllerAs: 'ctrl',
            bindToController: {
                onAgree: '&'
            },
            templateUrl: 'scrollable-agreement.template.html',
            link: link
        };

        function link(scope, elem, attrs, ctrl) {
            let doc = elem.find('.agreement__document');

            doc.on('scroll', onScroll);

            function onScroll() {
                let ratio = ($(this).prop('scrollTop') + $(this).prop('offsetHeight')) / $(this).prop('scrollHeight');

                if (ratio === 1) {
                    scope.$apply(() => { ctrl.isScrolled = true; });
                }
            }
        }
    }
})(window.angular);
(function (angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .directive('mllInputMatch', mllInputMatch);

    function mllInputMatch() {
        return {
            restrict: 'A',
            require: 'ngModel',
            scope: {
                firstValue: '=mllInputMatch'
            },
            link: link
        };

        function link(scope, elem, attrs, ctrl) {

            ctrl.$parsers.unshift((secondValue) => {
                let match = secondValue === scope.firstValue;

                ctrl.$setValidity('inputmatch', match);

                return secondValue;
            });

            scope.$watch('firstValue', (fValue) => {
                ctrl.$setValidity('inputmatch', fValue === ctrl.$viewValue);
            });
        }
    }
})(window.angular);
(function(angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .factory('authDetailsService', authDetailsService);

    function authDetailsService() {
        return {
            isAuth: false,
            data: {},
            init: init,
            clear: clear,
            change: change
        };

        function init(data) {
            this.isAuth = true;

            this.data = data;
        }

        function clear() {
            this.isAuth = false;

            this.data = {};
        }

        function change(data) {
            this.isAuth = true;

            this.data.id = data.userId;
            this.data.type = data.type;

            this.data.permissions = {};
            this.data.permissions.browse = data.browse;
            this.data.permissions.upload = data.upload;
        }
    }
})(window.angular);
(function(angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .factory('authenticationService', authenticationService);

    authenticationService.$inject = ['$cookies', 'authDetailsService', 'authDetailsKey'];

    function authenticationService($cookies, authDetailsService, authDetailsKey) {
        return {
            details: authDetailsService,
            clear: clear,
            change: change,
            check: check
        };

        function check() {
            let authDetails = $cookies.getObject(authDetailsKey);

            if (authDetails) this.details.init(authDetails.data);
        }

        function clear() {
            $cookies.remove(authDetailsKey);

            this.details.clear();
        }

        function change(data) {
            this.details.change(data);

            $cookies.putObject(authDetailsKey, this.details);
        }
    }
})(window.angular);
(function(angular) {
    'use strict';

    angular
        .module('mllApp.shared')
        .factory('inviteTokenService', inviteTokenService);

    inviteTokenService.$inject = ['$http', 'invitationUrl', 'tokenActionTypes'];

    function inviteTokenService($http, invitationUrl, tokenActionTypes) {
        return {
            validateToken: validateToken,
            generateToken: generateToken
        };

        function validateToken(data) {
            data.actionType =  tokenActionTypes.validate;

            return $http.post(invitationUrl, data);
        }

        function generateToken(data) {
            data.actionType = tokenActionTypes.generate;

            return $http.post(invitationUrl, data);
        }
    }
})(window.angular);