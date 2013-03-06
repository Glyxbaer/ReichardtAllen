package com.beowulf.ScheduleValidator.model;

import java.util.ArrayList;

import org.allen.temporalintervalrelationships.ConstraintNetwork;

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

	public void addConstraintsAsStrings(String[] consArray) {
		for (int x = 0; x < consArray.length; x++) {
			switch (consArray[x]) {
			case "=":
				addConstraint(ConstraintNetwork.bin_equals);
				break;
			case "<":
				addConstraint(ConstraintNetwork.bin_before);
				break;
			case ">":
				addConstraint(ConstraintNetwork.bin_after);
				break;
			case "d":
				addConstraint(ConstraintNetwork.bin_during);
				break;
			case "di":
				addConstraint(ConstraintNetwork.bin_contains);
				break;
			case "o":
				addConstraint(ConstraintNetwork.bin_overlaps);
				break;
			case "oi":
				addConstraint(ConstraintNetwork.bin_overlappedby);
				break;
			case "m":
				addConstraint(ConstraintNetwork.bin_meets);
				break;
			case "mi":
				addConstraint(ConstraintNetwork.bin_metby);
				break;
			case "s":
				addConstraint(ConstraintNetwork.bin_starts);
				break;
			case "si":
				addConstraint(ConstraintNetwork.bin_startedby);
				break;
			case "f":
				addConstraint(ConstraintNetwork.bin_finishes);
				break;
			case "fi":
				addConstraint(ConstraintNetwork.bin_finishedby);
				break;
			}
		}

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
