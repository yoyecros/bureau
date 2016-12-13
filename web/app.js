/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('monApp', ['ngRoute', 'ngResource']);

angular.module('monApp').config(['$routeProvider', function routeConfig($routeProvider) {
        $routeProvider
                .when('/crayons', {
                    controller: "CrayonsController as ctrl",
                    templateUrl: 'listeCrayon.html'
                })
                .when('/crayon/edit/:id', {
                    controller: "CrayonEditController as ctrl",
                    templateUrl: 'editCrayon.html'
                })
                .when('/crayon/new', {
                    controller: "CrayonNewController as ctrl",
                    templateUrl: 'newCrayon.html'
                })
                .when('/boites', {
                    controller: "BoitesController as ctrl",
                    templateUrl: 'listeBoites.html'
                })
                .when('/boite/edit/:id', {
                    controller: "BoiteEditController as ctrl",
                    templateUrl: 'editBoite.html'
                })
                .when('/boite/new', {
                    controller: "BoiteNewController as ctrl",
                    templateUrl: 'newBoite.html'
                })
                .otherwise({redirectTo: '/crayons'});
    }]);

