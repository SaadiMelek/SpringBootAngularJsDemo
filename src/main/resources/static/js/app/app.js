var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/SpringBootMainApplication',
    ENTERPRISE_SERVICE_API : 'http://localhost:8080/SpringBootMainApplication/api/enterprise/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'EnterpriseController',
                controllerAs:'ctrl',
                resolve: {
                    enterprises: function ($q, EnterpriseService) {
                        console.log('Load all enterprises');
                        var deferred = $q.defer();
                        EnterpriseService.loadAllEnterprises().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

