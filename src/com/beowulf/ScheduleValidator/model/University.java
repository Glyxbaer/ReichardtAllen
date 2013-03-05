package com.beowulf.ScheduleValidator.model;

import java.util.HashMap;

public class University {

	private HashMap<String, Course> courses;
	private HashMap<String, Lecture> lectures;

	public University() {
		lectures = new HashMap<String, Lecture>();
	}

	public HashMap<String, Course> getCourses() {
		return courses;
	}

	public HashMap<String, Lecture> getlectures() {
		return lectures;
	}

	public void setStudents(HashMap<String, Course> students) {
		this.courses = students;
	}

	public void setlectures(HashMap<String, Lecture> lectures) {
		this.lectures = lectures;
	}

	public void addStudent(String key, Course s) {
		courses.put(key, s);
	}

	public void addLecture(String key, Lecture p) {
		lectures.put(key, p);
	}

}
