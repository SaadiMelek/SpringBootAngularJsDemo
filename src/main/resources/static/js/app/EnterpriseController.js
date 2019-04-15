'use strict';

angular.module('crudApp').controller('EnterpriseController',
    ['EnterpriseService', '$scope',  function( EnterpriseService, $scope) {

        var self = this;
        self.enterprise = {};
        self.enterprises=[];

        self.submit = submit;
        self.getAllEnterprises = getAllEnterprises;
        self.createEnterprise = createEnterprise;
        self.updateEnterprise = updateEnterprise;
        self.removeEnterprise = removeEnterprise;
        self.editEnterprise = editEnterprise;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.enterprise.id === undefined || self.enterprise.id === null) {
                console.log('Saving New Enterprise', self.enterprise);
                createEnterprise(self.enterprise);
            } else {
                updateEnterprise(self.enterprise, self.enterprise.id);
                console.log('Enterprise updated with id ', self.enterprise.id);
            }
        }

        function createEnterprise(enterprise) {
            console.log('About to create enterprise');
            EnterpriseService.createEnterprise(enterprise)
                .then(
                    function (response) {
                        console.log('Enterprise created successfully');
                        self.successMessage = 'Enterprise created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.enterprise={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Enterprise');
                        self.errorMessage = 'Error while creating Enterprise: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateEnterprise(enterprise, id){
            console.log('About to update enterprise');
            EnterpriseService.updateEnterprise(enterprise, id)
                .then(
                    function (response){
                        console.log('Enterprise updated successfully');
                        self.successMessage='Enterprise updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Enterprise');
                        self.errorMessage='Error while updating Enterprise '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeEnterprise(id){
            console.log('About to remove Enterprise with id '+id);
            EnterpriseService.removeEnterprise(id)
                .then(
                    function(){
                        console.log('Enterprise '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing enterprise '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllEnterprises(){
            return EnterpriseService.getAllEnterprises();
        }

        function editEnterprise(id) {
            self.successMessage='';
            self.errorMessage='';
            EnterpriseService.getEnterprise(id).then(
                function (enterprise) {
                    self.enterprise = enterprise;
                },
                function (errResponse) {
                    console.error('Error while removing enterprise ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.enterprise={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);