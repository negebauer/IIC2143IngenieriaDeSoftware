package backend.courses;

import java.util.ArrayList;

import backend.interfaces.IProfessors;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Professor;

/**
 * Class that represents a Laboratory that is dictated.
 */
public class Laboratory implements IProfessors {

	private ArrayList<Professor> professors;
	private Classroom classroom;
	private Schedule schedule;

	/**
	 * Creates an instance of Laboratory.
	 * 
	 * @param professors
	 *            The professors in charge of the Laboratory.
	 * @param classroom
	 *            The classroom where the Laboratory takes place.
	 * @param schedule
	 *            The schedule in which the Laboratory takes place.
	 */
	public Laboratory(ArrayList<Professor> professors, Classroom classroom, Schedule schedule) {
		this.professors = professors != null ? professors : new ArrayList<Professor>();
		this.classroom = classroom;
		this.schedule = schedule;
	}

	// IProfessors methods
	@Override
	public void addProfessor(Professor professor) {
		this.professors.add(professor);
	}

	@Override
	public void removeProfessor(Professor professor) {
		this.professors.remove(professor);
	}

	@Override
	public ArrayList<Professor> getProfessors() {
		return this.professors;
	}

	@Override
	public Professor getProfessor(int index) {
		return this.professors.get(index);
	}

	// ICourse methods
	@Override
	public Classroom getClassroom() {
		return this.classroom;
	}

	@Override
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@Override
	public Schedule getSchedule() {
		return this.schedule;
	}

	@Override
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public String toString() {
		return Messages.getUILabel(UILabel.LABORATORY);
	}
}
