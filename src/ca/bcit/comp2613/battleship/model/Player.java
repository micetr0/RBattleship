package ca.bcit.comp2613.battleship.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Player {

	@Id
	private String id;
	private static int count = 1;
	private String firstName;
	private String lastName;
	private float score;
	private float hitRatio;
	private float missRatio;
	
	@ManyToOne
	private ScoreBoard scoreboard;

	@SuppressWarnings("unused")
	private Player() {

	}

	public Player(Integer score, Integer hitRatio, Integer missRatio, String firstName, String lastName) {
		super();
		id = Integer.toString(count);
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = score;
		this.hitRatio = hitRatio;
		this.missRatio = missRatio;
		count++;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public float getHitRatio() {
		return hitRatio;
	}

	public void setHitRatio(float hitRatio) {
		this.hitRatio = hitRatio;
	}

	public float getMissRatio() {
		return missRatio;
	}

	public void setMissRatio(float missRatio) {
		this.missRatio = missRatio;
	}

}
