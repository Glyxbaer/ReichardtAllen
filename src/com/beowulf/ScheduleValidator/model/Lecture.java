package com.beowulf.ScheduleValidator.model;

import java.util.Date;

public class Lecture {

	private String name;
	private Date date;
	private int duration;
	private Course course;

	public Lecture(String name, Date date, int duration, Course course) {
		this.name = name;
		this.date = date;
		this.duration = duration;
		this.course = course;

	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public int getDuration() {
		return duration;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
