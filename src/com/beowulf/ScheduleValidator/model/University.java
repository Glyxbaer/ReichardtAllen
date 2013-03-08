package com.beowulf.ScheduleValidator.model;

import java.util.ArrayList;
import java.util.HashMap;

public class University {

	private HashMap<String, Lecture> lectures;
	private ArrayList<Relation> relations;
	private ArrayList<Relation> rules;

	public University() {
		lectures = new HashMap<String, Lecture>();
		relations = new ArrayList<Relation>();
		rules = new ArrayList<Relation>();
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
	
	public void addRule(Relation relation) {
		rules.add(relation);
	}

	public ArrayList<Relation> getRules() {
		return rules;
	}

	public void print() {

		System.out.println("University lectures:");
		for (Lecture l : lectures.values())
			System.out.println("\t" + l.toString());
		System.out.println("Time constraints:");
		for (Relation r : relations)
			System.out.println("\t" + r.toString());
	}

	public HashMap<String, String> getCourses() {
		HashMap<String, String> courses = new HashMap<String, String>();

		for (Lecture l : lectures.values())
			courses.put(l.getCourse(), l.getCourse());

		return courses;
	}

	public HashMap<String, String> getProfs() {
		HashMap<String, String> profs = new HashMap<String, String>();

		for (Lecture l : lectures.values())
			profs.put(l.getProf(), l.getProf());

		return profs;
	}

}
