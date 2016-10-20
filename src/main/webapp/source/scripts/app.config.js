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