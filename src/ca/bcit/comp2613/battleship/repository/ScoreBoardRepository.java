package ca.bcit.comp2613.battleship.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.bcit.comp2613.battleship.model.Player;
import ca.bcit.comp2613.battleship.model.ScoreBoard;

public interface ScoreBoardRepository extends CrudRepository<ScoreBoard, String> {
	
	

}
