package com.beowulf.ScheduleValidator.model;

import java.util.ArrayList;

public class University {

	private ArrayList<Student> students;
	private ArrayList<Prof> profs;

	public University() {
		students = new ArrayList<Student>();
		profs = new ArrayList<Prof>();
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public ArrayList<Prof> getProfs() {
		return profs;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public void setProfs(ArrayList<Prof> profs) {
		this.profs = profs;
	}

	public void addStudent(Student s) {
		students.add(s);
	}

	public void addProf(Prof p) {
		profs.add(p);
	}

}
