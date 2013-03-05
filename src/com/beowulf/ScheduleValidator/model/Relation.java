package com.beowulf.ScheduleValidator.model;

import java.util.ArrayList;

public class Relation {
	private Lecture x1;
	private Lecture x2;
	private ArrayList<Short> cons;

	public Relation(Lecture p1, Lecture p2) {
		x1 = p1;
		x2 = p2;
		cons = new ArrayList<Short>();
	}

	public ArrayList<Short> addConstraint(Short pConstraint) {
		cons.add(pConstraint);
		return cons;

	}

	public Lecture getX1() {
		return x1;
	}

	public void setX1(Lecture x1) {
		this.x1 = x1;
	}

	public Lecture getX2() {
		return x2;
	}

	public void setX2(Lecture x2) {
		this.x2 = x2;
	}

	public ArrayList<Short> getCons() {
		return cons;
	}

	public void setCons(ArrayList<Short> cons) {
		this.cons = cons;
	}

	public String toString() {
		String front = x1.getName() + " and " + x2.getName() + ": ";
		StringBuffer end = new StringBuffer();
		for (Short s : cons)
			end.append(s + ",");
		return front + end.substring(0, end.length() - 1);
	}
}
