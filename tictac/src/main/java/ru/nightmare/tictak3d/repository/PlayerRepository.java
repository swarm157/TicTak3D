package ru.nightmare.tictak3d.repository;

import ru.nightmare.tictak3d.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerRepository extends CrudRepository<User, Long> {
    User findOneByUserName(String userName);
    User findOneByUserNameOrEmail(String userName, String email);
}
