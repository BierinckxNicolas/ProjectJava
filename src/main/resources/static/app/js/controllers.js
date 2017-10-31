var app = angular.module('crescendo.controllers', [])

    /* LOGIN SCHERM CONTROLLER */
app.controller("LoginCtrl", function($scope, $state, $rootScope, $http, loginFactory, $timeout, toastr, $window) {
        if($state.is("login")){
            $("body").css("background-image", "url('/app/images/bgCvo.png')");
            $("body").css("background-size", "cover");
        }
        $scope.login = function() {
            // token aanvragen aan de hand van username/passwoord
            loginFactory.getTokenLogin($scope.username, $scope.password)
                .then(function(response) {
                    $scope.password = null;
                    //check dat de token er is
                    if (response.data.token) {
                        // bearer token met jwt
                        $http.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.token;

                        $window.sessionStorage.setItem("user",JSON.stringify(response.data.user));
                        $window.sessionStorage.setItem("token", 'Bearer ' + response.data.token);

                        $timeout(function(){
                            $rootScope.$broadcast('LoginSuccessful');
                        }, 200);

                        $state.go("app.dashboard");

                    } else {
                        // if the token is not present in the response then the
                        // authentication was not successful. Setting the error message.
                        toastr.error('De inloggegevens zijn niet correct!', 'Error');
                    }
                },function(error) {
                    // if authentication was not successful. Setting the error message.
                    toastr.error('De inloggegevens zijn niet correct!', 'Error');
                });
        };
    })
    /* NAVIGATION CONTROLLER */
app.controller('AppCtrl', function ($scope, $rootScope, $state, $window) {
        $("body").css("background-image", "none");
        $scope.goLogInfo = function(){
            $state.go("app.loginfo");
        };

        $scope.toggleSideBar = function() {
            if ($('#page-wrapper').hasClass('show-sidebar')) {
                // Do things on Nav Close
                $('#page-wrapper').removeClass('show-sidebar');
            } else {
                // Do things on Nav Open
                $('#page-wrapper').addClass('show-sidebar');
            }
        };

        $scope.$on('LoginSuccessful', function() {
            $scope.user = JSON.parse($window.sessionStorage.getItem("user"));
        });

        $scope.$on('LogoutSuccessful', function() {
            $scope.user = null;
        });
        $scope.logout = function() {
            $window.sessionStorage.clear();
            $rootScope.$broadcast('LogoutSuccessful');
            $state.go('login');
        };

    })
    /* DASHBOARD SCHERM CONTROLLER */
app.controller("DashCtrl", function($scope, $timeout, statisticFactory, logFactory, toastr) {

        $scope.ssUsers = "";
        $scope.informatCursisten = "";
        $scope.hasNewStudents = true;

        function getStatistics() {
            statisticFactory.getSsUsers()
                .then(function (response) {
                    if(response.data != 0) {
                        $scope.hasNewStudents = true;
                        localStorage.setItem('smartschoolUsers', response.data);
                    } else {
                        localStorage.removeItem('smartschoolUsers');
                        $scope.hasNewStudents = false;
                    }
                }, function (error) {
                    console.log(error);
                });
            statisticFactory.getInformatCursisten()
                .then(function (response) {
                    if(response.data != 0) {
                        $scope.hasNewStudents = true;
                        localStorage.setItem('informatCursisten', response.data);
                    } else {
                        localStorage.removeItem('informatCursisten');
                        $scope.hasNewStudents = false;
                    }
                }, function (error) {
                    console.log(error);
                });
            statisticFactory.getSyncDuration()
                .then(function (response) {
                    $scope.duration = timeConversion(response.data);
                }, function (error) {
                    console.log(error);
                });
            statisticFactory.getServerErrors()
                .then(function (response) {
                    $scope.errors = response.data;
                }, function (error) {
                    console.log(error);
                });
            statisticFactory.getSSCourses()
                .then(function (response) {
                    $scope.aantalSsCourses = response.data;
                }, function(error) {
                    console.log(error);
                });
            statisticFactory.getAantalCourses()
                .then(function (response) {
                    $scope.aantalCourses = response.data;
                }, function(error) {
                    console.log(error);
                });
            statisticFactory.getAantalCursisten()
                .then(function (response) {
                    $scope.aantalCursisten = response.data;
                }, function(error) {
                    console.log(error);
                });
            statisticFactory.getAantalFullCourses()
                .then(function (response) {
                    $scope.aantalFullCourses = response.data;
                }, function(error) {
                    console.log(error);
                });
            statisticFactory.getAantalSsUsers()
                .then(function (response) {
                    $scope.aantalSSusers = response.data;
                }, function(error) {
                    console.log(error);
                });
        }

        getStatistics();

        function setCharts() {
            var smartschoolUsers = localStorage.getItem("smartschoolUsers");
            var informatCursisten = localStorage.getItem("informatCursisten");
            $scope.data = [smartschoolUsers,informatCursisten];
        }

        $scope.downloadlogfile = function () {
            var now = moment(new Date()).format('YYYY-MM-DD');
            logFactory.getLogFile(now)
                .then(function(response){
                    toastr.info("De opgevraagde logfile wordt gedownload", "Info")
                    var file = new Blob([response.data], {type: "text/plain;charset=utf-8"});
                    saveAs(file, now + ".txt");
                }, function (error) {;
                    toastr.error("De opgevraagde logfile bestaat niet", "Error")
                })
        };

        $timeout(setCharts, 300);

        //DONUT CHART
        $scope.labels = ["# Nieuwe Smartschool users", "# Nieuwe informat cursisten"];
        $scope.options = {legend: {display: true}};
        $scope.colors = ["#15744a" , "#00688b"];

        $scope.onClick = function (points, evt) {
            console.log(points, evt);
        };
        $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }, { yAxisID: 'y-axis-2' }];

        $timeout(function() {
            if(localStorage.getItem('smartschoolUsers') === null && localStorage.getItem("informatCursisten") === null) {
                $(".fix").css("height", "75px");
            } else {
                $(".fix").css("height", "100%");
            }
        }, 200);

        //FUNCTIE OM MILLISECONDEN OM TE ZETTEN IN SECONDEN/MINUTEN/UREN/DAGEN
        function timeConversion(millisec) {

            var seconds = (millisec / 1000).toFixed(1);

            var minutes = (millisec / (1000 * 60)).toFixed(1);

            var hours = (millisec / (1000 * 60 * 60)).toFixed(1);

            var days = (millisec / (1000 * 60 * 60 * 24)).toFixed(1);

            if (seconds < 60) {
                return seconds + " Sec";
            } else if (minutes < 60) {
                return minutes + " Min";
            } else if (hours < 24) {
                return hours + " Hrs";
            } else {
                return days + " Days"
            }
        }
    })
    /* SERVICE SCHERM CONTROLLER */
app.controller('ServiceCtrl', function ($scope, $mdDialog, checkBoxFactory, $rootScope, syncFactory, toastr, statisticFactory) {

        //Below line is to initialize toggle butten before dom load
        $('input[type=checkbox][data-toggle^=toggle]').bootstrapToggle();

        //Initializ the state
        checkBoxFactory.getServiceToggleState()
            .then(function(response){
                var serviceToggleState = response.data.checkboxState;
                if(serviceToggleState){
                    $('#serviceToggle').bootstrapToggle("on");
                } else {
                    $('#serviceToggle').bootstrapToggle("off");
                }
            },function(error){
                console.log(error);
            });
        checkBoxFactory.getSsmailToggleState()
            .then(function(response){
                var ssmailToggleState = response.data.checkboxState;
                if(ssmailToggleState){
                    $('#ssmailToggle').bootstrapToggle("on");
                } else {
                    $('#ssmailToggle').bootstrapToggle("off");
                }
            },function(error){
                console.log(error);
            });
        checkBoxFactory.getWelkomMailToggleState()
            .then(function(response){
                var welkomMailToggleState = response.data.checkboxState;
                if(welkomMailToggleState) {
                    $('#welkomMailToggle').bootstrapToggle("on");
                } else {
                    $('#welkomMailToggle').bootstrapToggle("off");
                }
            },function(error){
                console.log(error);
            });

        //reflect change in state to databank
        $(function() {
            $('#serviceToggle').change(function() {
                var serviceToggle = $(this).prop('checked');
                checkBoxFactory.setServiceToggleState(serviceToggle,$rootScope.token)
                    .then(function(response){
                        $('#serviceToggle').bootstrapToggle(serviceToggle);
                    },function(error){
                        console.log(error);
                    });
            })
        });
        $(function() {
            $('#ssmailToggle').change(function() {

                var ssmailToggle = $(this).prop('checked');
                checkBoxFactory.setSsmailToggleState(ssmailToggle)
                    .then(function(response){
                        $('#serviceToggle').bootstrapToggle(ssmailToggle);
                    },function(error){
                        console.log(error);
                    });
            })
        });
        $(function() {
            $('#welkomMailToggle').change(function() {
                var welkomMailToggle = $(this).prop('checked');
                checkBoxFactory.setWelkomMailToggleState(welkomMailToggle)
                    .then(function(response){
                        $('#serviceToggle').bootstrapToggle(welkomMailToggle);
                    },function(error){
                        console.log(error);
                    });
            })
        });

        statisticFactory.getLastSync()
            .then(function (response) {
                if(response.data !== ""){
                    $scope.lastSync = response.data;
                    $scope.lastSync = $scope.lastSync.replace("T", " ");
                }
            }, function(error) {
                console.log(error);
            });


        /* initial value */
        $scope.turnOffBtnTxt = "Stop alle services";

        // turn on/off all services + confirm box!
        $scope.turnAllOff = function (ev) {
            if ($scope.turnOff){
                    var confirmStop = $mdDialog.confirm()
                        .title('Bent u zeker dat u alle services wil stopzetten?')
                        .textContent('Alle services worden op stop gezet!')
                        .ariaLabel('Lucky day')
                        .targetEvent(ev)
                        .ok('Ik ben zeker!')
                        .cancel('Annuleer!');

                    $mdDialog.show(confirmStop).then(function() {
                        $scope.turnOffBtnTxt = "Start alle services";
                        $('#ssmailToggle').bootstrapToggle('off');
                        $('#welkomMailToggle').bootstrapToggle('off');
                        $('#serviceToggle').bootstrapToggle('off');
                        $scope.turnOff = false;
                    }, function() {

                    });
            } else {
                var confirmStart = $mdDialog.confirm()
                    .title('Bent u zeker dat u alle services wil starten?')
                    .textContent('Alle services worden opnieuw gestart!')
                    .ariaLabel('Lucky day')
                    .targetEvent(ev)
                    .ok('Ik ben zeker!')
                    .cancel('Annuleer!');

                $mdDialog.show(confirmStart).then(function() {
                    $scope.turnOffBtnTxt = "Stop alle services";
                    $('#ssmailToggle').bootstrapToggle('on');
                    $('#welkomMailToggle').bootstrapToggle('on');
                    $('#serviceToggle').bootstrapToggle('on');
                    $scope.turnOff = true;
                }, function() {

                });

            }
        };
        //manuele sync
        $scope.doSync = function(ev) {
            var confirmSync = $mdDialog.confirm()
                .title('Er zal een manuele sync uitgevoerd worden!')
                .textContent('Bent u zeker dat u de manuele synchronisatie wil starten? Dit kan enige tijd in beslag nemen!')
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Ik ben zeker!')
                .cancel('Annuleer!');
            $mdDialog.show(confirmSync).then(function() {
                $mdDialog.show({
                    scope: $scope,
                    preserveScope: true,
                    template: '<md-dialog>' +
                    '<md-dialog-content>' +
                    '<div style="min-width: 100%;">' +
                    '<p style="padding: 20px 20px 10px 20px; text-align: center; width: 100%; color:#555;">De synchronisatie wordt uitgevoerd. Even geduld alstublief...</p>' +
                    '<img src="/app/images/loading.gif" style="display: block; margin: 10px auto; width: 80px; height: 80px;">' + '</div>' +
                    '</md-dialog-content>' +
                    '</md-dialog>'});
                syncFactory.doFullSync()
                    .then(function(response){
                        toastr.info("De manuele synchronisatie is voltooid, duur ", "Error")
                        $mdDialog.hide();
                    },function(error){
                        toastr.error("Er is iets fout gelopen tijden de manuele synchronisatie", "Error");
                        $mdDialog.hide();
                        console.log(error);
                    });
            }, function() {

            });

        }
    })
    /* MAILBEHEER SCHERM CONTROLLER */
app.controller('MailCtrl', function ($scope,$state, downloadFactory, toastr) {
    $scope.downloadInfNoEmailFile = function () {
        downloadFactory.getNoEmailFile("Informat_No_Email")
            .then(function(response){
            toastr.info("De opgevraagde file wordt gedownload", "Info")
            var file = new Blob([response.data], {type: "text/plain;charset=utf-8"});
            saveAs(file, "Informat_Geen_Email.txt");
        }, function (error) {
            toastr.error("De opgevraagde file bestaat niet", "Error")
        })
    };
    $scope.downloadSsNoEmailFile = function () {
        downloadFactory.getNoEmailFile("SSUsers_No_Email")
            .then(function(response){
                toastr.info("De opgevraagde file wordt gedownload", "Info")
                var file = new Blob([response.data], {type: "text/plain;charset=utf-8"});
                saveAs(file, "SSUsers_Geen_Email.txt");
            }, function (error) {
                toastr.error("De opgevraagde file bestaat niet", "Error")
            })
    };
    $scope.downloadPuntenNoEmailFile = function () {
        downloadFactory.getNoEmailFile("geenEmailPuntenlijst")
            .then(function(response){
                toastr.info("De opgevraagde file wordt gedownload", "Info")
                var file = new Blob([response.data], {type: "text/plain;charset=utf-8"});
                saveAs(file, "Geen_Email_Puntenlijst.txt");
            }, function (error) {
                toastr.error("De opgevraagde file bestaat niet", "Error")
            })
    };

      if (localStorage.getItem('madepdf') == true) {
          $scope.showdownload = true;
      } else {
          $scope.showdownload = false;
      }
    });
    /* PUNTENBEHEER SCHERM CONTROLLER */
app.controller('PuntenCtrl', function ($scope,$state, studentsFactory,$mdDialog, toastr, $filter) {
    $scope.choiceSet = {
        choices: []
    };

    $scope.choiceSet.choices = [];

    $scope.choiceSetSelect = {
        choicesSelect: []
    };

    $scope.choiceSetSelect.choicesSelect = [];


    $scope.addNewChoice = function() {
        $scope.choiceSet.choices.push('');
        $scope.choiceSetSelect.choicesSelect.push('');
    };
    $scope.removeChoice = function(z) {
        $scope.choiceSet.choices.splice(z, 1);
        $scope.choiceSetSelect.choicesSelect.splice(z, 1);
    };

    $scope.PuntenlijstWijzigen = function(){
        $state.go( "app.puntenlijstWijzigen");
    };

    $scope.addNewChoice();


    $scope.myDate = new Date();
    $scope.isOpen = false;

    $scope.lijst = {
        singleSelect: null,
        option1: 'option-1'
    };

    $scope.listOfOptions = ['Selecteer filter', 'Administratieve groep', 'Studiegebied','Lector','Modulenaam',
        'Vestigingsplaats','Cursuscode','Cursusnaam','Semester','Cursistnaam', 'Gebruikersnaam'];

    var filterWords = {
        "Administratieve_groep": "null",
        "Studiegebied": "null",
        "Semester": "null",
        "Lector": "null",
        "Modulenaam": "null",
        "Vestigingsplaats": "null",
        "Cursuscode": "null",
        "Cursusnaam": "null",
        "Cursistnaam": "null",
        "Gebruikersnaam": "null"
    };


    function hasDuplicates(array) {
        var valuesSoFar = Object.create(null);
        for (var i = 0; i < array.length; ++i) {
            var value = array[i];
            if (value in valuesSoFar) {
                return true;
            }
            valuesSoFar[value] = true;
        }
        return false;
    }

    $scope.test = function () {

        var keys = Object.keys(filterWords);
        var lengte =  $scope.choiceSetSelect.choicesSelect.length;
        var values = [];

        for(i=0; i< lengte; i++) {
            var select = $scope.choiceSetSelect.choicesSelect[i];
            values.push(select);
        }

        var element =  document.getElementsByClassName('panda').length;

        if (element > 0)
        {


            for (var j = 0; j < lengte; j++ ) {
                if ($scope.choiceSetSelect.choicesSelect[j] == $scope.listOfOptions[0]) {
                    toastr.error("Een optie moet geselecteerd zijn", "Fout");
                    break;
                }

                else if(hasDuplicates(values) == true){
                    toastr.error("Geen duplicate filters mogelijk", "Fout");
                    break;
                }

                else if ($scope.choiceSet.choices[j] == "") {
                    toastr.error("De filter moet ingevuld zijn", "Fout");
                    break;
                }


                else {
                    filterWords.Administratieve_groep = "null";
                    filterWords.Cursistnaam = "null";
                    filterWords.Cursuscode = "null";
                    filterWords.Cursusnaam = "null";
                    filterWords.Gebruikersnaam = "null";
                    filterWords.Lector = "null";
                    filterWords.Modulenaam = "null";
                    filterWords.Studiegebied = "null";
                    filterWords.Vestigingsplaats = "null";
                    filterWords.Semester = "null";
                    for (var i = 0; i < keys.length; i++) {
                        var index = $scope.choiceSetSelect.choicesSelect.indexOf(keys[i].replace("_", / /g));
                        if (index >= 0) {
                            filterWords[keys[i]] = $scope.choiceSet.choices[index].toLowerCase();
                        }
                    }

                    $scope.showLoader = true;
                    $('.previewtable').css('visibility', 'hidden');
                    studentsFactory.getHistories(filterWords.Cursistnaam, filterWords.Cursusnaam, filterWords.Administratieve_groep, filterWords.Studiegebied, filterWords.Lector, filterWords.Modulenaam, filterWords.Vestigingsplaats, filterWords.Cursuscode, filterWords.Semester, filterWords.Gebruikersnaam)
                        .then(function (response) {
                            $scope.data = response.data;
                            $scope.showLoader = false;
                            $('.previewtable').css('visibility', 'visible');


                            $scope.gap = 1;
                            $scope.filteredItems = [];
                            $scope.groupedItems = [];
                            $scope.itemsPerPage = 10;
                            $scope.pagedItems = [];
                            $scope.currentPage = 0;


                            var searchMatch = function (haystack, needle) {
                                if (!needle) {
                                    return true;
                                }
                                return haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
                            };

                            $scope.search = function () {
                                $scope.filteredItems = $filter('filter')($scope.data, function (student) {
                                    for (var attr in student) {
                                        if (searchMatch(student[attr], $scope.query))
                                            return true;
                                    }
                                    return false;
                                });
                                $scope.groupToPages();
                            };

                            $scope.groupToPages = function () {
                                $scope.pagedItems = [];

                                for (var i = 0; i < $scope.filteredItems.length; i++) {
                                    if (i % $scope.itemsPerPage === 0) {
                                        $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [$scope.filteredItems[i]];
                                    } else {
                                        $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
                                    }
                                }
                            };

                            $scope.range = function (size, start, end) {
                                var ret = [];
                                console.log(size, start, end);

                                if (size < end) {
                                    end = size;
                                    start = size - $scope.gap;
                                }
                                for (var i = start; i < end; i++) {
                                    ret.push(i);
                                }
                                console.log(ret);
                                return ret;
                            };

                            $scope.prevPage = function () {
                                if ($scope.currentPage > 0) {
                                    $scope.currentPage--;
                                }
                            };

                            $scope.nextPage = function () {
                                if ($scope.currentPage < $scope.pagedItems.length - 1) {
                                    $scope.currentPage++;
                                }
                            };

                            $scope.setPage = function () {
                                $scope.currentPage = this.n;
                            };

                            $scope.search();

                        }, function (error) {
                            console.log(error);
                        });
                    break;
                }
            }

        }
        else {
            toastr.error("Je moet minstens 1 filter selecteren","Error");
        }

    };


    $scope.zenden = function (){
        localStorage.setItem('madepdf', 1);
        $mdDialog.show({
            scope: $scope,
            preserveScope: true,
            template: '<md-dialog>' +
            '<md-dialog-content>' +
            '<div style="min-width: 100%;">' +
            '<p style="padding: 20px 20px 10px 20px; text-align: center; width: 100%; color:#555;"> De puntenlijsten worden gegenereerd en verzonden. Even geduld alstublief...</p>' +
            '<img src="/app/images/loading.gif" style="display: block; margin: 10px auto; width: 80px; height: 80px;">' + '</div>' +
            '</md-dialog-content>' +
            '</md-dialog>'});
        studentsFactory.getPuntenlijst(filterWords.Cursistnaam, filterWords.Cursusnaam, filterWords.Administratieve_groep, filterWords.Studiegebied, filterWords.Lector, filterWords.Modulenaam, filterWords.Vestigingsplaats, filterWords.Cursuscode, filterWords.Semester, filterWords.Gebruikersnaam)
            .then(function (response) {
                console.log(response);
                $mdDialog.hide();
            }, function (error) {
                $mdDialog.hide();
                toastr.error("Er is een fout opgetreden tijdens het verzenden van de puntenlijsten", "error")
            });
    };



    })
    /* SERVERHEALTH SCHERM CONTROLLER */
app.controller('ServerCtrl', function ($scope,serverInfoFactory) {

        //SERVER STATUS UP OF DOWN
        serverInfoFactory.getServiceStatus()
            .then(function(response){
                $scope.status = response.data.status;
                if ($scope.status === "UP") {
                    $scope.status = "Up";
                    $(".status").css({"background-color":"#15744a"})
                } else {
                    $scope.status = "Down";
                    $(".status").css({"background-color":"#c40e1f"})
                }
            },function(error){
                console.log(error);
            });


        function getMetrics() {
            serverInfoFactory.getMetrics()
                .then(function (response) {
                    var serverData = response.data;
                    $scope.uptime = timeConversion(serverData["instance.uptime"]);
                    $scope.cpu = response.data.processors;
                    var freeMemory = serverData["mem.free"];
                    var usedHeapMemory = serverData["heap.used"];
                    var usedNonHeapMemory = serverData["nonheap.used"];
                    $scope.data = [(freeMemory / 1024).toFixed(), (usedHeapMemory / 1024).toFixed(), (usedNonHeapMemory / 1024).toFixed()];
                }, function (error) {
                    console.log(error);
                });
        }

        getMetrics();

        //DONUT CHART
        $scope.labels = ["Vrij geheugen Mb", "Heap geheugen in gebruik Mb", "Non-heap geheugen in gebruik Mb"];
        $scope.options = {legend: {display: true}};
        $scope.colors = ["#15744a" , "#00688b", "#00a1d7"];

        //FUNCTIE OM MILLISECONDEN OM TE ZETTEN IN SECONDEN/MINUTEN/UREN/DAGEN
        function timeConversion(millisec) {

            var seconds = (millisec / 1000).toFixed(1);

            var minutes = (millisec / (1000 * 60)).toFixed(1);

            var hours = (millisec / (1000 * 60 * 60)).toFixed(1);

            var days = (millisec / (1000 * 60 * 60 * 24)).toFixed(1);

            if (seconds < 60) {
                return seconds + " Sec";
            } else if (minutes < 60) {
                return minutes + " Min";
            } else if (hours < 24) {
                return hours + " Hrs";
            } else {
                return days + " Days"
            }
        }

    })
    /* LOGGINGINFO SCHERM CONTROLLER */
app.controller('LogCtrl', function ($scope, logFactory,toastr) {
        $scope.myDate = new Date();
        $scope.today = moment(new Date()).format('YYYY-MM-DD');
        $scope.isOpen = false;

        $scope.format = function() {
            $scope.formattedDate= moment($scope.myDate).format('YYYY-MM-DD');
        };
        
        $scope.downloadLogfile = function () {
            logFactory.getLogFile($scope.formattedDate)
                .then(function(response){
                    toastr.info("De opgevraagde logfile wordt gedownload", "Info")
                    var file = new Blob([response.data], {type: "text/plain;charset=utf-8"});
                    saveAs(file, $scope.formattedDate + ".txt");
                }, function (error) {
                    toastr.error("De opgevraagde logfile bestaat niet", "Error")
                })
        };

        $scope.getLog = function () {
            logFactory.getLogFile($scope.formattedDate)
                .then(function(response){
                    $scope.logtext = response.data;
                }, function (error) {
                    $scope.logtext = "";
                    toastr.error("De opgevraagde logfile bestaat niet", "Error")
                })
        };

    })
    /* WIJEZIGPNTNLIJST SCHERM CONTROLLER */
app.controller('wijzigPtnLijstCtrl', function ($scope, mailFactory, $mdDialog, toastr) {
    // GET MAIL CONTENT FROM SERVICE
    mailFactory.getPuntenMail()
        .then(function(response){
            $scope.tinymceModel = response.data.emailContent;
        },function(error){
            console.log(error);
        });
    //TINYMCE OPTIONS
    $scope.tinymceOptions = {
        theme: "modern",
        plugins: [
            "advlist autolink lists link image charmap print preview hr anchor pagebreak",
            "searchreplace wordcount visualblocks visualchars code fullscreen",
            "insertdatetime media nonbreaking save table contextmenu directionality",
            "emoticons template paste textcolor autoresize"
        ],
        toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
        toolbar2: "print preview media | forecolor backcolor",
        image_advtab: true,
        resize: false
    };
    //GET CURRENT CONTENT PREVIEW
    $scope.getPreview = function() {
        tinyMCE.activeEditor.execCommand('mcePreview');
    };
    //POPUP , IF CONFIRM SLAAG OP IN DB
    $scope.setContent = function(ev) {
        var confirm = $mdDialog.confirm()
            .title('Bent u zeker dat u de punten email template wil opslaan??')
            .textContent('De punten email template wordt opgeslagen')
            .ariaLabel('Lucky day')
            .targetEvent(ev)
            .ok('Ik ben zeker!')
            .cancel('Annuleer!');
        $mdDialog.show(confirm).then(function() {
            mailFactory.setPuntenMail($scope.tinymceModel)
                .then(function(response){
                    $scope.tinymceModel = response.data.emailContent;
                    toastr.info("De email is opgeslagen!", "Info")
                },function(error){
                    console.log(error);
                });
        }, function() {

        });

    };
    })
    /* WIJZIG PUNTEN PDF CONTROLLER */
app.controller('puntenpdfCtrl', function ($scope, mailFactory, $mdDialog, toastr) {
    // GET MAIL CONTENT FROM SERVICE
    mailFactory.getPuntenpdf()
        .then(function(response){
            $scope.tinymceModel = response.data.emailContent;
        },function(error){
            console.log(error);
        });
    //TINYMCE OPTIONS
    $scope.tinymceOptions = {
        theme: "modern",
        plugins: [
            "advlist autolink lists link image charmap print preview hr anchor pagebreak",
            "searchreplace wordcount visualblocks visualchars code fullscreen",
            "insertdatetime media nonbreaking save table contextmenu directionality",
            "emoticons template paste textcolor autoresize"
        ],
        toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
        toolbar2: "print preview media | forecolor backcolor",
        image_advtab: true,
        resize: false
    };
    //GET CURRENT CONTENT PREVIEW
    $scope.getPreview = function() {
        tinyMCE.activeEditor.execCommand('mcePreview');
    };
    //POPUP , IF CONFIRM SLAAG OP IN DB
    $scope.setContent = function(ev) {
        var confirm = $mdDialog.confirm()
            .title('Bent u zeker dat u de punten pdf inhoud template wil opslaan??')
            .textContent('De punten pdf inhoud template wordt opgeslagen')
            .ariaLabel('Lucky day')
            .targetEvent(ev)
            .ok('Ik ben zeker!')
            .cancel('Annuleer!');
        $mdDialog.show(confirm).then(function() {
            mailFactory.setPuntenpdf($scope.tinymceModel)
                .then(function(response){
                    $scope.tinymceModel = response.data.emailContent;
                    toastr.info("De email is opgeslagen!", "Info")
                },function(error){
                    console.log(error);
                });
        }, function() {

        });

    };
})
    /* WIJZIGMAIL SCHERM CONTROLLER */
app.controller('wijzigSsMailCtrl', function ($scope, mailFactory, $mdDialog, toastr) {
        // GET MAIL CONTENT FROM SERVICE
        mailFactory.getSmartschoolMail()
            .then(function(response){
                $scope.tinymceModel = response.data.emailContent;
            },function(error){
                console.log(error);
            });
        //TINYMCE OPTIONS
        $scope.tinymceOptions = {
            theme: "modern",
            plugins: [
                "advlist autolink lists link image charmap print preview hr anchor pagebreak",
                "searchreplace wordcount visualblocks visualchars code fullscreen",
                "insertdatetime media nonbreaking save table contextmenu directionality",
                "emoticons template paste textcolor autoresize"
            ],
            toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
            toolbar2: "print preview media | forecolor backcolor",
            image_advtab: true,
            resize: false
        };
        //GET CURRENT CONTENT PREVIEW
        $scope.getPreview = function() {
            tinyMCE.activeEditor.execCommand('mcePreview');
        };
        //POPUP , IF CONFIRM SLAAG OP IN DB
        $scope.setContent = function(ev) {
            var confirm = $mdDialog.confirm()
                .title('Bent u zeker dat u de smartschool email template wil opslaan??')
                .textContent('De smartschool email template wordt opgeslagen')
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Ik ben zeker!')
                .cancel('Annuleer!');
            $mdDialog.show(confirm).then(function() {
                mailFactory.setSmartschoolMail($scope.tinymceModel)
                    .then(function(response){
                        $scope.tinymceModel = response.data.emailContent;
                        toastr.info("De email is opgeslagen!", "Info")
                    },function(error){
                        console.log(error);
                    });
            }, function() {

            });

        };
    })
    /* WIJZIGWELKOMSTMAIL SCHERM CONTROLLER */
app.controller('wijzigWelkomstMailCtrl', function ($scope, mailFactory, $mdDialog, toastr) {
        // GET MAIL CONTENT FROM SERVICE
        mailFactory.getWelkomMail()
            .then(function(response){
                $scope.tinymceModel = response.data.emailContent;
            },function(error){
                console.log(error);
            });
        //TINYMCE OPTIONS
        $scope.tinymceOptions = {
            theme: "modern",
            plugins: [
                "advlist autolink lists link image charmap print preview hr anchor pagebreak",
                "searchreplace wordcount visualblocks visualchars code fullscreen",
                "insertdatetime media nonbreaking save table contextmenu directionality",
                "emoticons template paste textcolor autoresize"
            ],
            toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
            toolbar2: "print preview media | forecolor backcolor",
            image_advtab: true,
            resize: false
        };
        //GET CURRENT CONTENT PREVIEW
        $scope.getPreview = function() {
            tinyMCE.activeEditor.execCommand('mcePreview');
        };
        //POPUP , IF CONFIRM SLAAG OP IN DB
        $scope.setContent = function(ev) {
            var confirm = $mdDialog.confirm()
                .title('Bent u zeker dat u de welkomst email template wil opslaan??')
                .textContent('De welkomst email template wordt opgeslagen')
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Ik ben zeker!')
                .cancel('Annuleer!');
            $mdDialog.show(confirm).then(function() {
                mailFactory.setWelkomMail($scope.tinymceModel)
                    .then(function(response){
                        $scope.tinymceModel = response.data.emailContent;
                        toastr.info("De email is opgeslagen!", "Info")
                    },function(error){
                        console.log(error);
                    });
            }, function() {

            });

        };
    })
    /* PAGE NOT FOUND CONTROLLER */
app.controller('PageNotFoundCtrl', function (toastr,$state,$scope) {
    })
    /* ACCESS DENIED CONTROLLER */
app.controller('AccessDeniedCtrl', function ($scope, toastr) {
        toastr.error("De pagina is niet toegankelijk", "Error");
    })
    /* USER CONTROLLER */
app.controller('UsersCtrl', function ($scope, $http, toastr) {

        var edit = false;
        $scope.buttonText = 'maak gebruiker aan';
        var init = function() {
            $http.get('api/users')
                .then(function(res) {
                    $scope.users = res.data;
                    $scope.userForm.$setPristine();
                    $scope.message='';
                    $scope.appUser = null;
                    $scope.buttonText = 'Maak gebruiker aan';
                }, function(error){
                    $scope.message = error.message;
            });
        };
        $scope.initEdit = function(appUser) {
            edit = true;
            $scope.appUser = appUser;
            $scope.message='';
            $scope.buttonText = 'Wijzig gebruiker';
        };
        $scope.initAddUser = function() {
            edit = false;
            $scope.appUser = null;
            $scope.userForm.$setPristine();
            $scope.message='';
            $scope.buttonText = 'Maak gebruiker aan';
        };
        $scope.deleteUser = function(appUser) {
            $http.delete('api/users/'+appUser.id)
                .then(function(res) {
                    toastr.info("gebruiker succesvol verwijderd", "Info");
                    init();
                    location.reload();

                }, function(error) {
                    toastr.info("gebruiker succesvol verwijderd", "Info");
                    init();
                    location.reload();
                });
            };
        var editUser = function(){
            $http.put('api/users', $scope.appUser)
                .then(function(res) {
                    toastr.info("gebruiker " +  $scope.appUser.name + " succesvol gewijzigd", "Info");
                    $scope.appUser = null;
                    $scope.confirmPassword = null;
                    $scope.userForm.$setPristine();
                    init();
                }, function(error) {
                    toastr.error("Er is een fout opgetreden tijdens het wijzigen van de gebruiker", "Error");
                });
            };
        var addUser = function(){
            $http.post('api/users', $scope.appUser)
                .then(function(res) {
                    toastr.info("gebruiker " + $scope.appUser.name + " succesvol aangemaakt", "Info");
                    $scope.appUser = null;
                    $scope.confirmPassword = null;
                    $scope.userForm.$setPristine();
                    init();
                }, function(error) {
                    toastr.error("Er is een fout opgetreden tijdens het aanmaken van de gebruiker", "Error");
                });
            };
        $scope.submit = function() {
            if(edit){
                editUser();
            }else{
                addUser();
            }
        };
        init();

    })
    /* CONFIG CONTROLLER */
app.controller("ConfigCtrl", function($scope, configFactory, toastr, $mdDialog) {

        //GETTERS
        configFactory.getSchoolyear()
            .then(function(response){
                $scope.schooljaar = response.data;
            },function(error){
                console.log(error);
            });
        configFactory.getInstituteNumber()
            .then(function(response){
                $scope.instituutnummer = response.data;
            },function(error){
                console.log(error);
            });
        configFactory.getApiVersionInformat()
            .then(function(response){
                $scope.versioninformat = response.data;
            },function(error){
                console.log(error);
            });
        configFactory.getApiVersionSmartschool()
            .then(function(response){
                $scope.versionsmartschool = response.data;
            },function(error){
                console.log(error);
            });
        configFactory.getKeySmartschool()
            .then(function(response){
                $scope.keysmartschool = response.data;
            },function(error){
                console.log(error);
            });

        //SETTERS
        $scope.submit = function (ev) {
            event.preventDefault();
                var confirm = $mdDialog.confirm()
                    .title('Bent u zeker dat u deze configuratie wil opslaan?')
                    .textContent('De nieuwe configuratie zal worden opgeslagen')
                    .ariaLabel('Lucky day')
                    .targetEvent(ev)
                    .ok('Ik ben zeker!')
                    .cancel('Annuleer!');
                $mdDialog.show(confirm).then(function() {
                    configFactory.setSchoolyear($scope.schooljaar)
                        .then(function(response){
                            $scope.schooljaar = response.data;
                        },function(error){
                            toastr.error('Het schooljaar is niet correct ingevuld, kijk dit even na!', 'Error');
                        });
                    configFactory.setApiVersionInformat($scope.versioninformat)
                        .then(function(response){
                            $scope.urlinformat = response.data;
                        },function(error){
                            toastr.error('De informat versie is niet correct ingevuld, kijk dit even na!', 'Error');
                        });
                    configFactory.setInstituteNumber($scope.instituutnummer)
                        .then(function(response){
                            $scope.instituutnummer = response.data;
                        },function(error){
                            toastr.error('Het instituutnummer is niet correct ingevuld, kijk dit even na!', 'Error');
                        });
                    configFactory.setApiVersionSmartschool($scope.versionsmartschool)
                        .then(function(response){
                            $scope.urlsmartschool = response.data;
                        },function(error){
                            toastr.error('De smaschool versie is niet correct ingevuld, kijk dit even na!', 'Error');
                        });
                    configFactory.setKeySmartschool($scope.keysmartschool)
                        .then(function(response){
                            $scope.keysmartschool = response.data;
                        },function(error){
                            toastr.error('De smartschoolkey is niet correct ingevuld, kijk dit even na!', 'Error');
                        });
                    toastr.info("De nieuwe configuratie is succesvol opgeslagen");
                }, function() {

                });

            };
    })
    /* VERZEND WELKOMST MAIL CONTROLLER */
app.controller("verzendwelkomstmailCtrl", function($scope, toastr, $mdDialog, mailFactory){

        $scope.submitForm = function(isValid) {

            // check to make sure the form is completely valid
            if (isValid) {
                mailFactory.sendWelkomstmail($scope.voornaamEmail, $scope.emailadres)
                    .then(function (response) {
                        toastr.info("De email is succesvol verzonden naar " + $scope.emailadres, "info")
                    }, function (error) {
                        toastr.info("Er is iets misgegaan tijdens het verzenden van de email", "error")
                        console.log(error);
                    });
            }
        }
});
    /* DOWNLOAD PDF CONTORLLER */
app.controller("downloadpdfCtrl", function($scope, downloadf, downloadFactory, toastr){
    $scope.downloadpdf = function() {
        downloadFactory.getPdf($scope.voornaampnt, $scope.achternaampnt)
            .then(function(response){
                toastr.info("De opgevraagde file wordt gedownload", "Info")
                var file = new Blob([response.data], { type: 'application/pdf' });
                saveAs(file, $scope.voornaampnt + "_" +$scope.achternaampnt + ".txt");
            }, function (error) {
                toastr.error("De opgevraagde file bestaat niet", "Error")
            })
    }
})



