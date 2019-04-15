'use strict';

angular.module('crudApp').factory('EnterpriseService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllEnterprises: loadAllEnterprises,
                getAllEnterprises: getAllEnterprises,
                getEnterprise: getEnterprise,
                createEnterprise: createEnterprise,
                updateEnterprise: updateEnterprise,
                removeEnterprise: removeEnterprise
            };

            return factory;

            function loadAllEnterprises() {
                console.log('Fetching all enterprises');
                var deferred = $q.defer();
                $http.get(urls.ENTERPRISE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all enterprises');
                            $localStorage.enterprises = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading enterprises');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllEnterprises(){
                return $localStorage.enterprises;
            }

            function getEnterprise(id) {
                console.log('Fetching Enterprise with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.ENTERPRISE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Enterprise with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading enterprise with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createEnterprise(enterprise) {
                console.log('Creating Enterprise');
                var deferred = $q.defer();
                $http.post(urls.ENTERPRISE_SERVICE_API, enterprise)
                    .then(
                        function (response) {
                            loadAllEnterprises();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Enterprise : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateEnterprise(enterprise, id) {
                console.log('Updating Enterprise with id '+id);
                var deferred = $q.defer();
                $http.put(urls.ENTERPRISE_SERVICE_API + id, enterprise)
                    .then(
                        function (response) {
                            loadAllEnterprises();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Enterprise with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeEnterprise(id) {
                console.log('Removing Enterprise with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.ENTERPRISE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllEnterprises();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Enterprise with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);