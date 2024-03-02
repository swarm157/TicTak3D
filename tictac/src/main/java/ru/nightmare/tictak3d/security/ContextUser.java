package ru.nightmare.tictak3d.security;

import ru.nightmare.tictak3d.domain.User;
import com.google.common.collect.ImmutableSet;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class ContextUser extends org.springframework.security.core.userdetails.User {

    private final User player;

    public ContextUser(User player) {
        super(player.getUserName(),
                player.getPassword(),
                true,
                true,
                true,
                true,
                ImmutableSet.of(new SimpleGrantedAuthority("create")));

        this.player = player;

    }

    public User getPlayer() {
        return  player;
    }
}
