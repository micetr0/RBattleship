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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Player {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private Integer score;
	private Integer hitRatio;
	private Integer missRatio;

	private Player() {

	}

	public Player(String id, String firstName, String lastName, Integer score,
			Integer hitRatio, Integer missRatio) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = score;
		this.hitRatio = hitRatio;
		this.missRatio = missRatio;
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
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getHitRatio() {
		return hitRatio;
	}

	public void setHitRatio(Integer hitRatio) {
		this.hitRatio = hitRatio;
	}

	public Integer getMissRatio() {
		return missRatio;
	}

	public void setMissRatio(Integer missRatio) {
		this.missRatio = missRatio;
	}

}
