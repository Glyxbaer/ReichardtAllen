package com.beowulf.ScheduleValidator.model;

import java.util.ArrayList;

public class Person {
	private String name;
	private ArrayList<Lecture> lectures;

	public Person(String name) {
		this.name = name;
		this.lectures = new ArrayList<Lecture>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Lecture> getLectures() {
		return lectures;
	}

	public void addLecture(Lecture lec) {
		lectures.add(lec);
	}

}
