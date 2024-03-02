
var ticTacToe = angular.module('ticTacToe',['ngRoute']);

ticTacToe.config(['$routeProvider',function($routeProvider){
    $routeProvider.
       when('/game/:id',{
            templateUrl:'/views/game-board.html',
            controller:'gameController'
        }).
        when('/player-panel',{
            templateUrl: '/views/player-panel.html',
            controller: 'newGameController'
        }).
        otherwise({
            redirectTo: '/player-panel'
        })
}])