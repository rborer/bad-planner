package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class GameDefinition extends Model {

	@Required
	@MaxSize(60)
	public String description;

	@ManyToMany
	@Required
	@JoinTable(name = "gamedefinition_managers")
	public List<User> managers;

	@ManyToMany
	@JoinTable(name = "gamedefinition_participants")
	public List<User> participants;

	@OneToMany(mappedBy = "gameDefinition", cascade = CascadeType.ALL)
	public List<TimeSlot> timeSlots;

	public GameDefinition(final String description, final List<User> managers) {
		super();

		this.description = description;
		this.managers = managers;
	}
}
