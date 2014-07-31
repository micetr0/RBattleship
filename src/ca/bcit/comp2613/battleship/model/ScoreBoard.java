package ca.bcit.comp2613.battleship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ScoreBoard {
	
	@Id
	private String theId;
	private static int count;
	
	@OneToMany
	public static List<Player> players;

	public ScoreBoard() {
		super();
		theId = Integer.toString(count);
		count++;
	}
	
	
}