(function (angular){
     'use strict';
 
     angular
         .module('mllApp.arhome')
         .controller('ArhomeController', ArhomeController);
 
     ArhomeController.$inject =
         ['$scope',  'authenticationService'];
 
     function ArhomeController($scope,  authenticationService) {
 
         this.authService = authenticationService;
 
 
    $scope.myList = ["dirty songs"];
       $scope.new_playlist_data1 = true;
    $scope.add = function() {
      $scope.myList.push($scope.input);
      $scope.input = null;
      $scope.new_playlist_data = false;
       $scope.new_playlist_data1 = true;  
     };
     
     $scope.new_playlist_data = false;
     $scope.addplaylist = function(){
         $scope.new_playlist_data = true;
          $scope.new_playlist_data1 = false;
     };
 	}
})(window.angular);