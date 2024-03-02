package ru.nightmare.tictak3d.controllers.RestControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nightmare.tictak3d.DTO.CreateMoveDTO;
import ru.nightmare.tictak3d.DTO.CreateMoveResponseDTO;
import ru.nightmare.tictak3d.services.GameService;
import ru.nightmare.tictak3d.services.PlayerService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/move")
public class MoveRestController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CreateMoveResponseDTO createMove(@RequestBody CreateMoveDTO createMoveDTO){

        return gameService.move(createMoveDTO.getBoardX(),createMoveDTO.getBoardY(),createMoveDTO.getBoardZ(),createMoveDTO.getGameId());
    }

}
