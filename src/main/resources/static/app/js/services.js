angular.module('crescendo.services', [])
    .factory('mailFactory', function($http,$q) {
        return {
            getWelkomMail: function () {
                var deferred = $q.defer();
                var url = "api/email/1";
                $http.get(url)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            setWelkomMail: function (emailContent) {
                var deferred = $q.defer();
                var url = "api/email/1";
                var data = {
                    emailid: "1",
                    emailContent: emailContent
                };
                $http.post(url,data)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            getSmartschoolMail: function () {
                var deferred = $q.defer();
                var url = "api/email/2";
                $http.get(url)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            setSmartschoolMail: function (emailContent) {
                var deferred = $q.defer();
                var url = "api/email/2";
                var data = {
                    emailid: "2",
                    emailContent: emailContent
                };
                $http.post(url,data)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            sendWelkomstmail: function (firstname, email) {
                var deferred = $q.defer();
                var url = "api/sendwelkomstmail";
                var data = {
                    firstName: firstname,
                    email: email
                };
                $http.post(url, data)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            getPuntenMail: function () {
                var deferred = $q.defer();
                var url = "api/email/3";
                $http.get(url)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            setPuntenMail: function (emailContent) {
                var deferred = $q.defer();
                var url = "api/email/3";
                var data = {
                    emailid: "3",
                    emailContent: emailContent
                };
                $http.post(url,data)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            getPuntenpdf: function () {
                var deferred = $q.defer();
                var url = "api/email/4";
                $http.get(url)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            setPuntenpdf: function (emailContent) {
                var deferred = $q.defer();
                var url = "api/email/4";
                var data = {
                    emailid: "4",
                    emailContent: emailContent
                };
                $http.post(url,data)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            }
        }
    })
    .factory('checkBoxFactory', function($http,$q) {
        return {
            getServiceToggleState: function () {
                var deferred = $q.defer();
                var url = "api/checkbox/1";
                $http.get(url)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            setServiceToggleState: function (checkboxState) {
                var deferred = $q.defer();
                var url = "api/checkbox/1";
                var data = {
                    checkboxId : "1",
                    checkboxState: checkboxState
                };
                $http.post(url,data)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            getSsmailToggleState: function () {
                var deferred = $q.defer();
                var url = "api/checkbox/2";
                $http.get(url)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            setSsmailToggleState: function (checkboxState) {
                var deferred = $q.defer();
                var url = "api/checkbox/2";
                var data = {
                    checkboxId : "2",
                    checkboxState: checkboxState
                };
                $http.post(url,data)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            getWelkomMailToggleState: function () {
                var deferred = $q.defer();
                var url = "api/checkbox/3";
                $http.get(url)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            setWelkomMailToggleState: function (checkboxState) {
                var deferred = $q.defer();
                var url = "api/checkbox/3";
                var data = {
                    checkboxId : "3",
                    checkboxState: checkboxState
                };
                $http.post(url,data)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            }
        }
    })
    .factory('serverInfoFactory', function ($http, $q) {
        return {
            getServiceStatus: function () {
                var deferred = $q.defer();
                var url = "/health";
                $http.get(url)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            },
            getMetrics: function () {
                var deferred = $q.defer();
                var url = "/metrics";
                $http.get(url)
                    .then (function (response) {
                        deferred.resolve (response);
                    })
                    .catch (function (response) {
                        deferred.reject (response);
                    });
                return deferred.promise;
            }
    }})
    .factory('loginFactory', function($http, $q){
        return {
            getTokenLogin: function (username, password) {
                var deferred = $q.defer();
                var url = "authenticate?password="+password+"&username="+username;
                var data =
                    {
                        username: username,
                        password: password
                    };
                $http.post(url, data)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            }
        }
    })
    .factory('configFactory', function($http, $q) {
        return {
            getSchoolyear: function () {
                var deferred = $q.defer();
                var url = "api/config/schoolyear";
                $http.get(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            },
            setSchoolyear: function (schooljaar) {
                var deferred = $q.defer();
                var url = "api/config/schoolyear/" + encodeURIComponent(schooljaar);
                $http.post(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            },
            getApiVersionInformat: function () {
                var deferred = $q.defer();
                var url = "api/config/apiversioninformat";
                $http.get(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            },
            setApiVersionInformat: function (urlinformat) {
                var deferred = $q.defer();
                var url = "api/config/apiversioninformat/" + encodeURIComponent(urlinformat);
                $http.post(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            },
            getApiVersionSmartschool: function () {
                var deferred = $q.defer();
                var url = "api/config/apiversionss";
                $http.get(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            },
            setApiVersionSmartschool: function (urlsmartschool) {
                var deferred = $q.defer();
                var url = "api/config/apiversionss/" + encodeURIComponent(urlsmartschool);
                $http.post(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            },
            getKeySmartschool: function () {
                var deferred = $q.defer();
                var url = "api/config/sskey";
                $http.get(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            },
            setKeySmartschool: function (keysmartschool) {
                var deferred = $q.defer();
                var url = "api/config/sskey/" + encodeURIComponent(keysmartschool);
                $http.post(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            },
            getInstituteNumber: function () {
                var deferred = $q.defer();
                var url = "api/config/institutenr";
                $http.get(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            },
            setInstituteNumber: function (instituurnr) {
                var deferred = $q.defer();
                var url = "api/config/institutenr/" + encodeURIComponent(instituurnr);
                $http.post(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            }

        }
    })
    .factory('syncFactory', function($http, $q) {
        return {
            doFullSync: function () {
                var deferred = $q.defer();
                var url = "api/fullsync";
                $http.get(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            }
        }
})
    .factory('statisticFactory', function($http, $q) {
    return {
        getSyncDuration: function () {
            var deferred = $q.defer();
            var url = "api/syncduration";
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        },
        getServerErrors: function () {
            var deferred = $q.defer();
            var url = "api/servererrors";
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        },
        getSSCourses: function () {
            var deferred = $q.defer();
            var url = "api/aantalsscourses";
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        },
        getLastSync: function () {
            var deferred = $q.defer();
            var url = "api/lastsync";
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        },
        getSsUsers: function () {
            var deferred = $q.defer();
            var url = "api/newusers";
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        },
        getInformatCursisten: function () {
            var deferred = $q.defer();
            var url = "api/newcursisten";
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        },
        getAantalCourses: function () {
            var deferred = $q.defer();
            var url = "api/aantalcourses";
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        },
        getAantalCursisten: function () {
            var deferred = $q.defer();
            var url = "api/aantalcursisten";
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        },
        getAantalSsUsers: function () {
            var deferred = $q.defer();
            var url = "api/aantalssusers";
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        },
        getAantalFullCourses: function () {
            var deferred = $q.defer();
            var url = "api/aantalvollecourses";
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        }
    }
})
    .factory('logFactory', function($http, $q) {
        return {
            getLogFile: function (filename) {
                var deferred = $q.defer();
                var url = "api/logfile/" + filename;
                $http.get(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            }
        }
    })
    .factory('studentsFactory', function($http,$q) {
        return {
            getHistories: function(firstName, courseName, adminGroup, studyArea, teacher, moduleName, location, courseCode, semester, username) {

                var deferred = $q.defer();
                var url = "api/studenthistory/" + firstName  +  "/" + courseName + "/" + adminGroup + "/" + studyArea + "/" + teacher + "/" + moduleName + "/" + location + "/" + courseCode + "/" + semester + "/" + username;
                $http.get(url)
                    .then(function (response) {
                        console.log(response);
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;

            },
            getPuntenlijst: function(firstName, courseName, adminGroup, studyArea, teacher, moduleName, location, courseCode, semester, username) {

                var deferred = $q.defer();
                var url = "api/puntenlijst/" + firstName  +  "/" + courseName + "/" + adminGroup + "/" + studyArea + "/" + teacher + "/" + moduleName + "/" + location + "/" + courseCode + "/" + semester + "/" + username;
                $http.get(url)
                    .then(function (response) {
                        console.log(response);
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;

            }

        }

    })
    .factory('downloadFactory', function($http, $q) {
        return {
            getNoEmailFile: function (filename) {
                var deferred = $q.defer();
                var url = "api/noemailfiles/" + filename;
                $http.get(url)
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            },
            getPdf: function(voornaam,achternaam){
                var deferred = $q.defer();
                var url = "api/puntenpdf/" + voornaam + "/" + achternaam;
                $http.get(url, { responseType: 'arraybuffer' })
                    .then(function (response) {
                        deferred.resolve(response);
                    })
                    .catch(function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            }
        }
    })