package ru.nightmare.tictak3d.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import ru.nightmare.tictak3d.domain.Authority;
import ru.nightmare.tictak3d.domain.User;
import ru.nightmare.tictak3d.DTO.PlayerDTO;
import ru.nightmare.tictak3d.repository.PlayerRepository;

@Service
public class AuthenticationService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private StandardPasswordEncoder standardPasswordEncoder;

    public String registerUser(PlayerDTO playerDTO,BindingResult result){
        if(!playerDTO.getPassword().equals(playerDTO.getConfirmPassword())){
            result.rejectValue("email",null);

            return "views/userAuthentication/registration";
        }

        User player = playerRepository.findOneByUserNameOrEmail(playerDTO.getUserName(),playerDTO.getEmail());
        if(player == null){
            playerDTO.setPassword(standardPasswordEncoder.encode(playerDTO.getPassword()));
            player = new User(playerDTO.getUserName(),playerDTO.getEmail(),playerDTO.getPassword());
            Authority auth = new Authority(playerDTO.getUserName(),"ROLE_USER");
            player.setAuthority(auth);
            playerRepository.save(player);

            return "redirect:/login";
        }else{
            if (
                            player.getUserName().equals(playerDTO.getUserName())
                    &&
                            player.getEmail().equals(playerDTO.getEmail())
                    &&
                            player.getPassword().equals(standardPasswordEncoder.encode(playerDTO.getPassword()))
            ) {
                return "redirect:/login";
            } else {
                result.rejectValue("email",null);
                return "views/userAuthentication/registration";
            }
        }
    }
}
