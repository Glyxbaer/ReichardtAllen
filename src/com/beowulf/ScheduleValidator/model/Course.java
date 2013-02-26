package com.beowulf.ScheduleValidator.model;

import java.util.HashMap;

public class Course {

	private String name;
	private HashMap<String, Lecture> lectures;

	public Course(String name) {
		this.name = name;
		this.lectures = new HashMap<String, Lecture>();
	}

	public String getName() {
		return name;
	}

	public HashMap<String, Lecture> getLectures() {
		return lectures;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLectures(HashMap<String, Lecture> lectures) {
		this.lectures = lectures;
	}

	public void addLecture(String key, Lecture l) {
		lectures.put(key, l);
	}

}
