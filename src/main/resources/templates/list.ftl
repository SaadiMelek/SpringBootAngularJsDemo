<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Enterprise information</span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.enterprise.id" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="ename">Name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.enterprise.name" id="ename" class="username form-control input-sm" placeholder="Enter Enterprise Name" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="age">Serial</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.enterprise.serial" id="serial" class="form-control input-sm" placeholder="Enter Enterprise Siren" required ng-pattern="ctrl.onlyIntegers"/>
                            </div>
                        </div>
                    </div>
     
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="capital">Capital</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.enterprise.capital" id="capital" class="form-control input-sm" placeholder="Enter Enterprise Capital" required ng-pattern="ctrl.onlyNumbers"/>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.enterprise.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>    
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Enterprises </span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>SERIAL</th>
                        <th>CAPITAL</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="e in ctrl.getAllEnterprises()">
                        <td>{{e.id}}</td>
                        <td>{{e.name}}</td>
                        <td>{{e.serial}}</td>
                        <td>{{e.capital}}</td>
                        <td><button type="button" ng-click="ctrl.editEnterprise(e.id)" class="btn btn-success custom-width">Edit</button></td>
                        <td><button type="button" ng-click="ctrl.removeEnterprise(e.id)" class="btn btn-danger custom-width">Remove</button></td>
                    </tr>
                    </tbody>
                </table>      
            </div>
        </div>
    </div>
</div>