package ca.bcit.comp2613.battleship.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.bcit.comp2613.battleship.model.Player;

public interface PlayerRepository extends CrudRepository<Player, String> {
	
	

}
