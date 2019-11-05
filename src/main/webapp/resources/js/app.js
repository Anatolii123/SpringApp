angular.module("myApp",[])
    .controller("firstCtrl", function ($scope) {
        $scope.tempInput = "";

        $scope.tasksArray = ["1","2"];
        
        $scope.addNew = function () {
            if ($scope.tempInput) {
                $scope.tasksArray.push($scope.tempInput);
                $scope.tempInput = "";
            } else {
                console.log('no');
            }
        }
        
    });