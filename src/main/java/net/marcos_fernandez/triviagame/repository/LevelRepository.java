package net.marcos_fernandez.triviagame.repository;

import net.marcos_fernandez.triviagame.model.Level;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LevelRepository extends MongoRepository<Level, String> {
}
