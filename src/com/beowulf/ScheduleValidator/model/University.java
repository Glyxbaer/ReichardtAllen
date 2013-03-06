package com.beowulf.ScheduleValidator.model;

import java.util.ArrayList;
import java.util.HashMap;

public class University {

	private HashMap<String, Lecture> lectures;
	private ArrayList<Relation> relations;

	public University() {
		lectures = new HashMap<String, Lecture>();
		relations = new ArrayList<Relation>();
	}

	public HashMap<String, Lecture> getLectures() {
		return lectures;
	}

	public void setlectures(HashMap<String, Lecture> lectures) {
		this.lectures = lectures;
	}

	public void addLecture(String key, Lecture p) {
		lectures.put(key, p);
	}

	public void addRelation(Relation pRelation) {
		relations.add(pRelation);
	}

	public ArrayList<Relation> getRelations() {
		return relations;
	}

	public void print() {

		System.out.println("University lectures:");
		for (Lecture l : lectures.values())
			System.out.println("\t" + l.toString());
		System.out.println("Time constraints:");
		for (Relation r : relations)
			System.out.println("\t" + r.toString());
	}

}
