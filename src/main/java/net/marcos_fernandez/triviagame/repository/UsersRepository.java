package net.marcos_fernandez.triviagame.repository;

import net.marcos_fernandez.triviagame.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
}
