package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class TimeSlot extends Model {

	@ManyToOne
	@Required
	public GameDefinition gameDefinition;

	@ManyToMany
	public List<User> participants;
}
