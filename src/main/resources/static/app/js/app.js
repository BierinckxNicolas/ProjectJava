angular.module('crescendo', [
    'crescendo.controllers',
    'crescendo.services',
    'ui.router',
    'ngResource',
    'ngStorage',
    'ngMaterial',
    'ui.tinymce',
    'ngMessages',
    'chart.js',
    'ngAnimate',
    'toastr',
    'ngCookies'
])
    .config(function(toastrConfig) {
        angular.extend(toastrConfig, {
            closeButton: true,
            autoDismiss: false,
            containerId: 'toast-container',
            maxOpened: 0,
            timeOut: 2500,
            newestOnTop: true,
            positionClass: 'toast-top-center',
            preventDuplicates: false,
            preventOpenDuplicates: false,
            target: 'body'
        });
    })

    .config(function($mdDateLocaleProvider){
        $mdDateLocaleProvider.months = ['januari', 'februari', 'maart', 'april', 'mei', 'juni', 'juli', 'augustus', 'september', 'oktober', 'november', 'december'];
        $mdDateLocaleProvider.shortMonths = ['jan', 'feb', 'maa', 'apr', 'mei', 'jun', 'jul', 'aug', 'sep', 'okt', 'nov', 'dec'];
        $mdDateLocaleProvider.days = ['zondag', 'maandag', 'dinsdag', 'woensdag', 'donderdag', 'vrijdag', 'zaterdag'];
        $mdDateLocaleProvider.shortDays = ['Zo', 'Ma', 'Di', 'Wo', 'Do', 'Vr', 'Za'];
        $mdDateLocaleProvider.firstDayOfWeek = 1;
        $mdDateLocaleProvider.weekNumberFormatter = function(weekNumber) {
            return 'Week ' + weekNumber;
        };
        $mdDateLocaleProvider.msgCalendar = 'Kalender';
        $mdDateLocaleProvider.msgOpenCalendar = 'Open de kalender';
        $mdDateLocaleProvider.formatDate = function(date) {
            return moment(date).format('YYYY-MM-DD');
        }
    })

    .config(function ($stateProvider, $urlRouterProvider) {

                $urlRouterProvider.otherwise('/login');

                $stateProvider
                    .state('login', {
                        url: '/login',
                        templateUrl: 'app/views/login.html',
                        controller: 'LoginCtrl'
                    })
                    .state('app', {
                        url: '/app',
                        abstract: true,
                        templateUrl: 'app/views/navigation.html',
                        controller: 'AppCtrl'
                    })
                    .state('app.dashboard', {
                        url: '/dashboard',
                        templateUrl: 'app/views/dashboard.html',
                        controller: 'DashCtrl'
                    })
                    .state('app.servicebeheer', {
                        url: '/servicebeheer',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/servicebeheer.html',
                        controller: 'ServiceCtrl'
                    })
                    .state('app.mailbeheer', {
                        url: '/mailbeheer',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/mailbeheer.html',
                        controller: 'MailCtrl'
                    })
                    .state('app.puntenbeheer', {
                        url: '/puntenbeheer',
                        templateUrl: 'app/views/puntenbeheer.html',
                        controller: 'PuntenCtrl'
                    })
                    .state('app.serverinfo', {
                        url: '/serverinfo',
                        templateUrl: 'app/views/serverinfo.html',
                        controller: 'ServerCtrl'
                    })
                    .state('app.loginfo', {
                        url: '/loginfo',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/loginfo.html',
                        controller: 'LogCtrl'
                    })
                    .state('app.puntenlijstWijzigen', {
                        url: '/puntenlijstWijzigen',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/puntenlijstWijzigen.html',
                        controller: 'wijzigPtnLijstCtrl'
                    })
                    .state('app.smartschoolmailWijzigen', {
                        url: '/smartschoolmailWijzigen',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/smartschoolmailWijzigen.html',
                        controller: 'wijzigSsMailCtrl'
                    })
                    .state('app.welkomstmailWijzigen', {
                        url: '/welkomstmailWijzigen',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/welkomstmailWijzigen.html',
                        controller: 'wijzigWelkomstMailCtrl'
                    })
                    .state('app.puntenpdfwijzigen', {
                        url: '/puntenpdfwijzigen',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/puntenlijstpdfWijzigen.html',
                        controller: 'puntenpdfCtrl'
                    })
                    .state('app.downloadpdf', {
                        url: '/downloadpdf',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/downloadpdf.html',
                        controller: 'downloadpdfCtrl'
                    })
                    .state('app.page-not-found', {
                        url: '/page-not-found',
                        templateUrl: 'app/views/page-not-found.html',
                        controller: 'PageNotFoundCtrl'
                    })
                    .state('app.access-denied', {
                        url: '/access-denied',
                        templateUrl: 'app/views/access-denied.html',
                        controller: 'AccessDeniedCtrl'
                    })
                    .state('app.users', {
                        url: '/users',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/users.html',
                        controller: 'UsersCtrl'
                    })
                    .state('app.config', {
                        url: '/config',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/config.html',
                        controller: 'ConfigCtrl'
                    })
                    .state('app.verzendwelkomstmail', {
                        url: '/verzendwelkomstmail',
                        data: {
                            role: 'ADMIN'
                        },
                        templateUrl: 'app/views/verzendwelkomstmail.html',
                        controller: 'verzendwelkomstmailCtrl'
                    })
            })

    .run(function($rootScope, $state, $window, $timeout, $http) {
        $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
            if ($window.sessionStorage.length === 0) {
                if (toState.name !== 'login') {
                    event.preventDefault();
                    $state.go('login');
                }
            } else {
                $timeout(function(){
                    $rootScope.$broadcast('LoginSuccessful');
                }, 200);
                $http.defaults.headers.common['Authorization'] = $window.sessionStorage.getItem("token");
                if (toState.data && toState.data.role) {
                    var hasAccess = false;
                    var temp = JSON.parse($window.sessionStorage.getItem("user"));
                    var roles = temp.roles;
                    for (var i = 0; i < roles.length; i++) {
                        var role = roles[i];
                        if (toState.data.role === role) {
                            hasAccess = true;
                            break;
                        }
                    }
                    if (!hasAccess) {
                        event.preventDefault();
                        $state.go('app.access-denied');
                    }
                }
            }
        });
    })
