package net.marcos_fernandez.triviagame.repository;

import net.marcos_fernandez.triviagame.model.GameBoard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameBoardRepository extends MongoRepository<GameBoard, String> {
}
