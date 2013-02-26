package com.beowulf.ScheduleValidator.model;

import java.util.Date;

public class Lecture {

	private String name;
	private Date date;
	private int duration;

	public Lecture(String name, Date date, int duration) {
		this.name = name;
		this.date = date;
		this.duration = duration;

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

}
