

angular.module('monApp').factory('Crayons', ['$resource', function($resource) {
        
    //  voir https://docs.angularjs.org/api/ngResource/service/$resource pour la doc de cet objet
    return $resource('/bureau/webresources/generic/crayons/:id?', { id : '@id'} );
    
}]);

angular.module('monApp').factory('Boites', ['$resource', function($resource) {
        
    //  voir https://docs.angularjs.org/api/ngResource/service/$resource pour la doc de cet objet
    return $resource('/bureau/webresources/generic/boites/:id', { id : '@id'} );
    
}]);
