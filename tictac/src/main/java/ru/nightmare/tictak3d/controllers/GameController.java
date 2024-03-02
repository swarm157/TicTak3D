package ru.nightmare.tictak3d.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GameController {
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String game() {
        return "views/index";
    }
}
