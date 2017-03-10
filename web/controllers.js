
angular.module('monApp')

        .controller('PatientsController', ['Patients',
            function (Patients) {
                this.patients = Patients.query();
            }
        ])
        
        .controller('PatientViewController', ['$routeParams', 'Patients', '$location', '$scope',
            function ($routeParams, Patients, $location, $scope) {
                this.p = Patients.get({id: $routeParams.id});
                
                $scope.selected = [];
                $scope.limitOptions = [5, 10, 15];
                
                $scope.query = {
                    order: 'IEP',
                    limit: 5,
                    page: 1
                };
                
                $scope.redirect = function(v) {
                    $location.path("/venues/"+v.iep);
                }
                
                $scope.toggleLimitOptions = function () {
                    $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
                };
            }
        ])
        
        .controller('VenuesController', ['Venues', 'Patients', '$timeout', '$q', '$location', '$timeout',
            function (Venues, Patients, $timeout, $q, $location, $timeout) {
                var self = this;
                
                self.venues = [];
                
                self.p = Patients.query().$promise.then(function(result) {
                    angular.forEach(result, function(p) {
                        angular.forEach(p.venues, function(v) {
                            self.venues.push({"iep" : v.iep, "nom" : p.nom, "prenom" : p.prenom});
                        });
                    });
                });
                self.querySearch = querySearch;
                self.selectedItemChange = selectedItemChange;
                
                function querySearch (query) {
                    var deferred = $q.defer();
                    $timeout(function() {
                        var results = self.venues.filter(function(v) {
                            return (v.iep.indexOf(query) !== -1);
                        });
                        deferred.resolve(results);
                    }, 500);

                    return deferred.promise;
                }
                
                function createFilterFor(query) {
                    return (self.venues.indexOf(query) !== -1 );
                }

                function selectedItemChange(item) {
                    $timeout(function () {
                        $location.path("/venues/"+item.iep);
                    });
                }
            }
        ])
        
        .controller('VenueViewController', ['$routeParams', 'Venues', '$location', 'PatientByVenue', 'Mouvements', 'Services', '$scope', '$http', '$window',
            function ($routeParams, Venues, $location, PatientByVenue, Mouvements, Services, $scope, $http, $window, $mdDialog) {
                var self = this;
                this.m = new Mouvements();
                this.v = Venues.get({id: $routeParams.id});
                this.v.$promise.then(function(result) {
                    if( result.mouvements[result.mouvements.length - 1].entree) {
                        self.m.entree = false;
                        self.m.service = result.mouvements[result.mouvements.length - 1].service;
                        self.m.chambre = result.mouvements[result.mouvements.length - 1].chambre;
                        self.m.lit = result.mouvements[result.mouvements.length - 1].lit;
                    }
                    else
                        self.m.entree = true;
                });
                this.p = PatientByVenue.get({id: $routeParams.id});
                this.s = Services.query();
                
                $scope.selected = [];
                $scope.limitOptions = [5, 10, 15];
                
                $scope.query = {
                    limit: 10,
                    page: 1
                };
                
                $scope.toggleLimitOptions = function () {
                    $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
                };
                
                this.addMouvement = function() {
                    // et en l'ajoutant à la liste des crayons disponibles
                    if(typeof this.m.id === 'undefined' && (this.v.mouvements.length == 0 || this.v.mouvements[this.v.mouvements.length - 1].entree == false) )
                        this.m.entree = true;
                    console.log(this.m);
                    this.v.mouvements.push(this.m);
                    var url = '/bureau/webresources/generic/venues/'+this.v.iep+'/mouvements/';
                    $window.location.reload();
                    return $http.post(url, this.m);
                }
                this.editMouvement = function() {
                    // et en l'ajoutant à la liste des crayons disponibles
                    console.log(this.m);
                    var url = '/bureau/webresources/generic/mouvements/'+this.m.id;
                    $window.location.reload();
                    return $http.post(url, this.m);
                }
                
                this.deleteMouvement = function(m) {
                    Mouvements.delete(m);
                    this.v.mouvements.splice(this.v.mouvements.indexOf(m), 1);
                }
                this.edit = function(m) {
                    this.m = m;
                }
                this.cancel = function() {
                    this.m = new Mouvements();
                    this.v.$promise.then(function(result) {
                    if( result.mouvements[result.mouvements.length - 1].entree) {
                        self.m.entree = false;
                        self.m.service = result.mouvements[result.mouvements.length - 1].service;
                        self.m.chambre = result.mouvements[result.mouvements.length - 1].chambre;
                        self.m.lit = result.mouvements[result.mouvements.length - 1].lit;
                    }
                    else
                        self.m.entree = true;
                });
                }
            }
        ])
        
        .controller('MouvementNewController', ['Mouvements',
            function (Mouvements) {
                this.m = new Mouvements();
                this.update = function () {
                    this.m.$save();
                };
            }
        ])

        .controller('MouvementEditController', ['$routeParams', 'Mouvements', '$location',
            function ($routeParams, Mouvements, $location) {
                this.m = Mouvements.get({id: $routeParams.id});
                this.update = function () {
                    // appel POST asynchrone au service web sur /crayons/{id} 
                    this.m.$save();
                    $location.path("/")
                };
            }
        ])
        
        .controller('ServicesController', ['Services', '$location', 
            function (Services, $location) {
                this.services = Services.query();
                
                this.goto = function(num) {
                    $location.path("/services/"+num);
                }
            }
        ])
        
        .controller('ServiceViewController', ['$routeParams', 'Services',
            function ($routeParams, Services) {
                this.s = Services.get({id: $routeParams.id});
                this.delete = function(s) {
                    Services.delete(s);
                    this.services.splice(this.services.indexOf(s), 1);
                }
            }
        ])