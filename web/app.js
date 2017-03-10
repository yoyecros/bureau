/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('monApp', ['ngRoute', 'ngResource', 'ngMaterial', 'ngMaterialDatePicker', 'md.data.table']);

angular.module('monApp').config(['$routeProvider', function routeConfig($routeProvider) {
        $routeProvider
                .when('/services', {
                    controller: "ServicesController as ctrl",
                    templateUrl: 'listeServices.html'
                })
                .when('/services/:id', {
                    controller: "ServiceViewController as ctrl",
                    templateUrl: 'infosService.html'
                })
                .when('/patients', {
                    controller: "PatientsController as ctrl",
                    templateUrl: 'listePatient.html'
                })
                .when('/patients/:id', {
                    controller: "PatientViewController as ctrl",
                    templateUrl: 'infosPatient.html'
                })
                .when('/venues', {
                    controller: "VenuesController as ctrl",
                    templateUrl: 'listeVenues.html'
                })
                .when('/venues/:id', {
                    controller: "VenueViewController as ctrl",
                    templateUrl: 'infosVenue.html'
                })
                .when('/mouvement/new', {
                    controller: "MouvementNewController as ctrl",
                    templateUrl: 'newMouvement.html'
                })
                .when('/mouvement/edit/:id', {
                    controller: "MouvementEditController as ctrl",
                    templateUrl: 'editMouvement.html'
                })
                .when('/menu', {
                    templateUrl: 'menu.html'
                })
                .otherwise({redirectTo: '/menu'});
    }]);

