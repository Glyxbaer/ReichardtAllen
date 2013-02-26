package com.beowulf.ScheduleValidator.model;

import java.util.HashMap;

public class Prof {

	private String name;
	private HashMap<String, Lecture> lectures;

	public Prof(String name) {
		this.name = name;
		this.lectures = new HashMap<String, Lecture>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, Lecture> getLectures() {
		return lectures;
	}

	public void addLecture(String key, Lecture lec) {
		lectures.put(key, lec);
	}

}
