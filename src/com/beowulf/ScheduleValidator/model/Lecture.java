package com.beowulf.ScheduleValidator.model;

public class Lecture {

	private int id;
	private String name;
	private int start;
	private int end;
	private String course;
	private String prof;

	public Lecture(int id, String name, String prof, String course) {
		this.id = id;
		this.name = name;
		this.prof = prof;
		this.course = course;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getProf() {
		return prof;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

	public String toString() {
		return name + "(" + prof + ", " + course + "): " + start + "-" + end;
	}

}
