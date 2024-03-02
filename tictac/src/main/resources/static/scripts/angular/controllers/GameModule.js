var jq = $.noConflict();

/*
<tr ng-repeat="move in movesInGame">
    <td>{{move.userName}}</td>
    <td>Board row: {{move.boardX}}, Board column: {{move.boardY}}, Board layer: {{move.boardZ}}</td>
    <td>{{move.created | date:'MM-dd-yyyy HH:mm:ss'}}</td>
</tr>
 */
let active = false;

function validateCreateGame(){
    let pieceType = jq('#piece :selected');

    if(pieceType.text()){
        jq('.piece-validation-message').addClass('hidden');
    }
    else{
        jq('.piece-validation-message').removeClass('hidden');
    }
}
ticTacToe.controller('newGameController',['$rootScope','$scope','$http','$location',

    function (rootScope,scope,http,location){

        rootScope.gameId = null;
        scope.newGameData = null;
        active = false;
        scope.item = {
            "turnId":"0",
            "turnName":"first"
        }
        scope.turns = [
            {
                "id":"0",
                "name":"first"
            },
            {
                "id":"1",
                "name":"second"
            }
        ]
        scope.createNewGame = function (){
            var data = {};
            for(var i=0;i<scope.turns.length;i++){
                if(scope.turns[i].id == scope.item.turnId){
                    data.turn = scope.turns[i].name;
                }
            }
            var params = JSON.stringify(data);

            http.post("/game/create",params,{
                headers:{
                    'Content-Type' : 'application/json; charset=UTF-8'
                }
            }).success(function (data,status,headers,config){
                rootScope.gameId = data.gameId;
                location.path('/game/'+rootScope.gameId);

            }).error(function(data,status,headers,config){
                location.path('/player-panel');
            })
        }
    }
]);

ticTacToe.controller('playerGamesController', ['$scope', '$http', '$location', '$routeParams','$rootScope',

    function(scope,http,location,routeParams,rootScope){

        scope.playerGames = [];

        http.get('/game/player/list').success(function(data){
            //console.log(data);
            scope.playerGames  = data;

        }).error(function(data,status,headers,config){
            location.path('/player-panel');
        })

        scope.loadGame = function (id){
            //console.log(id);
            rootScope.gameId = Object.keys(id)[0];
            location.path('/game/'+rootScope.gameId);
        }

    }

])


ticTacToe.controller('gameController', function($rootScope, $routeParams, $scope, $http){
    var gameStatus;
    getInitialData();

    function getInitialData(){
        $http.get('/game/' + $routeParams.id).success(function(data){
            //console.log(data)
            data = data.gameBoard;
            active = true;
            changeGif();
            drawCube(data);
        }).error(function (data,status,headers,config){
            $scope.errorMessage = "Failed to load game properties";
        });
    }


    function reloadRoute() {
        $route.reload();
    }

    // Watch for changes in the game ID
    $scope.$watch(function() {
        return $routeParams.id;
    }, function(newId, oldId) {
        if (newId !== oldId) {
            console.log(newId);
            console.log(oldId);
            reloadRoute();
        }
    });




    function changeGif() {
        let bg = document.getElementById("body");
        bg.style.backgroundImage = "url('/styles/bootstrap/images/("+(1+Math.round(Math.random()*31))+").gif')";
        bg.style.backgroundRepeat = "no-repeat";
        bg.style.backgroundSize = "cover";
        if (active)
            setTimeout(changeGif, Math.round(Math.random()*4500+500));
    }

    $scope.markPlayerMove = function (column){

        console.log(column);
        var boardX = parseInt(column.charAt(0));
        var boardY = parseInt(column.charAt(1));
        var boardZ = parseInt(column.charAt(2));
        var params = {'boardX':boardX,'boardY':boardY,'boardZ':boardZ,'gameId':$rootScope.gameId}
        $http.post("/move/create", params,{
            headers:{
                'Content-Type':'application/json; charset=UTF-8'
            }
        }).success(function(data){
            if(data.message != null){
                alert(data.message);
            } else {
                let history  = document.getElementById("history");
                let e = document.createElement("p");
                e.innerText="X:"+params.boardX+",Y:"+params.boardY+",Z:"+params.boardZ;
                history.append(e);
            }
            //console.log(data);
            drawCube(data.gameBoard);
        }).error(function (data,status,headers,config){
            $location.path('/playerGamesController');
            console.log(status);
            onGameDoesNotExist(status);
            $scope.errorMessage = "Can't send the move"
        });/*.catch(function (error){
            console.log(error);
            onGameDoesNotExist(error);
        });*/
    };

    function onGameDoesNotExist(statusCode) {
        if (statusCode === 500) {
            $location.path('/playerGamesController'); // Replace '/error500' with the route you want to redirect to
        }
    }

    function drawCube(cubeArray){
        let humanMoves = 0;
        let computerMoves = 0;
        for(let i=0; i<cubeArray.length; i++){
            for(let j=0; j<cubeArray.length; j++) {
                for(let k=0; k<cubeArray.length; k++) {
                    //console.log(cubeArray[i][j][k]);
                    if(cubeArray[i][j][k] === 1){
                        //jq('#'+i+j+k).css('background-color','rgb(235,140,130)');
                        jq('#'+i+j+k).css('background-image','url(/styles/bootstrap/images/cat2.jpg)');
                        jq('#'+i+j+k).css('background-repeat','no-repeat');
                        jq('#'+i+j+k).css('background-size','cover');
                        computerMoves++;
                    }else if(cubeArray[i][j][k] === 2){
                        //jq('#'+i+j+k).css('background-color','rgb(120,150,235)');
                        //jq('#'+i+j+k).css('background-image','url(/styles/bootstrap/images/dog2.jpg) cover no-repeat');
                        jq('#'+i+j+k).css('background-image','url(/styles/bootstrap/images/dog2.jpg)');
                        jq('#'+i+j+k).css('background-repeat','no-repeat');
                        jq('#'+i+j+k).css('background-size','cover');
                        humanMoves++;
                    }
                }
            }
        }

    }
});