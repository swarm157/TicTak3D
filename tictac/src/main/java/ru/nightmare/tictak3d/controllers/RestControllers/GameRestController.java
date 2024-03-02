package ru.nightmare.tictak3d.controllers.RestControllers;


import ru.nightmare.tictak3d.DTO.CreateNewGameDTO;
import ru.nightmare.tictak3d.DTO.GameDTO;
import ru.nightmare.tictak3d.services.GameService;
import ru.nightmare.tictak3d.services.PlayerService;
import ru.nightmare.tictak3d.services.AI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameRestController {
    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateNewGameDTO createNewGame(@RequestBody GameDTO gameDTO){
        CreateNewGameDTO createGameDTO = new CreateNewGameDTO();
        createGameDTO.setGameId(gameService.createNewGame(playerService.getLoggedUser(),gameDTO));

        return createGameDTO;
    }

    @RequestMapping(value = "/player/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, AI>> getPlayerGames(){
        List<Map<String, AI>> games = gameService.getPlayerGames(playerService.getLoggedUser());
        if(games != null){
            return games;
        }
        return new ArrayList<Map<String, AI>>();
    }

    @RequestMapping(value = "/{id}")
    public AI getGameProperties(@PathVariable String id){
        httpSession.setAttribute("gameId", id);

        return gameService.getGame(id);
    }
}
