package ru.nightmare.tictak3d.controllers.RestControllers;


import ru.nightmare.tictak3d.domain.User;
import ru.nightmare.tictak3d.services.PlayerService;
import ru.nightmare.tictak3d.services.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerRestController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public void getPlayers(){
        playerService.listPlayers();
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<User> getLoggedPlayer(){
        return new Response<>(playerService.getLoggedUser(),Response.Status.CREATED);
    }

}
