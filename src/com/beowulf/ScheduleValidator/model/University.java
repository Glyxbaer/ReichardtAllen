package com.beowulf.ScheduleValidator.model;

import java.util.ArrayList;
import java.util.HashMap;

public class University {

	private HashMap<String, Course> courses;
	private HashMap<String, Lecture> lectures;
	private ArrayList<Relation> relations;
	
	public University() {
		lectures = new HashMap<String, Lecture>();
		courses = new HashMap<String, Course>();
	}

	public HashMap<String, Course> getCourses() {
		return courses;
	}

	public HashMap<String, Lecture> getlectures() {
		return lectures;
	}

	public void setStudents(HashMap<String, Course> students) {
		this.courses = students;
	}

	public void setlectures(HashMap<String, Lecture> lectures) {
		this.lectures = lectures;
	}

	public void addStudent(String key, Course s) {
		courses.put(key, s);
	}

	public void addLecture(String key, Lecture p) {
		lectures.put(key, p);
	}
	
	public void addRelation(Relation pRelation)
	{
	    relations.add(pRelation);
	    
	}

    public ArrayList<Relation> getRelations()
    {
        return relations;
     
    }

	public void print() {

		System.out.println("University courses:");
		for (Course c : courses.values())
			System.out.println("\t" + c.getName());
		System.out.println("University lectures:");
		for (Lecture l : lectures.values())
			System.out.println("\t" + l.getName() + "(" + l.getProf() + ")");
	}

}
