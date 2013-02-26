package com.beowulf.ScheduleValidator.model;

import java.util.HashMap;

public class University {

	private HashMap<String, Course> courses;
	private HashMap<String, Prof> profs;

	public University() {
		profs = new HashMap<String, Prof>();
	}

	public HashMap<String, Course> getCourses() {
		return courses;
	}

	public HashMap<String, Prof> getProfs() {
		return profs;
	}

	public void setStudents(HashMap<String, Course> students) {
		this.courses = students;
	}

	public void setProfs(HashMap<String, Prof> profs) {
		this.profs = profs;
	}

	public void addStudent(String key, Course s) {
		courses.put(key, s);
	}

	public void addProf(String key, Prof p) {
		profs.put(key, p);
	}

}
