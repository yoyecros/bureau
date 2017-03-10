angular.module('monApp').factory('Patients', ['$resource', function($resource) {
        
    //  voir https://docs.angularjs.org/api/ngResource/service/$resource pour la doc de cet objet
    return $resource('/bureau/webresources/generic/patients/:id', { id : '@ipp'} );
    
}]);

angular.module('monApp').factory('Venues', ['$resource', function($resource) {
        
    //  voir https://docs.angularjs.org/api/ngResource/service/$resource pour la doc de cet objet
    return $resource('/bureau/webresources/generic/venues/:id', { id : '@iep'} );
    
}]);

/*angular.module('monApp').factory('Mouvements', ['$resource', function($resource) {
    return $resource('/bureau/webresources/generic/venues/:iep/mouvements/:id', { iep : '@iep', id : '@id' } );
}]);*/

angular.module('monApp').factory('Mouvements', ['$resource', function($resource) {
    return $resource('/bureau/webresources/generic/mouvements/:id', { id : '@id' } );
}]);

angular.module('monApp').factory('PatientByVenue', ['$resource', function($resource) {
    //  voir https://docs.angularjs.org/api/ngResource/service/$resource pour la doc de cet objet
    return $resource('/bureau/webresources/generic/venues/:id/Patient', { id : '@iep'} );     
}]);

angular.module('monApp').factory('LitByMouvement', ['$resource', function($resource) {
    //  voir https://docs.angularjs.org/api/ngResource/service/$resource pour la doc de cet objet
    return $resource('/bureau/webresources/generic/mouvements/:id/lit', { id : '@iep'} );     
}]);

angular.module('monApp').factory('Services', ['$resource', function($resource) {
    return $resource('/bureau/webresources/generic/services/:id', { id : '@numero' } );
}]);