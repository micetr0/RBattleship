package ca.bcit.comp2613.battleship.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import ca.bcit.comp2613.battleship.Menu;
import ca.bcit.comp2613.battleship.model.Player;


public class PlayerUtil {
	
	
	//display players in score table
	public void createPlayer() {
		//need to tweak menu to link the players
		ArrayList<Player>players = new ArrayList<>();

		 Player player = new Player(null, null, null, null, null);

		players.add(player);
	}

	public static void save(List<Player> players, Player player) {
		
		boolean foundUpdate = false;
		for (Player playerLoop :players) {
			if (playerLoop.getId() == (player.getId())) {
				playerLoop.setFirstName(player.getFirstName());
				playerLoop.setLastName(player.getLastName());
				foundUpdate = true;
				break;
			}
		}
		if (!foundUpdate) { // do an insert
			players.add(player);
		}
	}
	
	public static void delete(List<Player> players, Player player) {
		Iterator<Player> playList = players.iterator();
		while (playList.hasNext()) {
			Player playerLoop = playList.next();
			if(playerLoop.getId()==player.getId()) {
				playList.remove();
				break;
			}
			
		}
	}

}
