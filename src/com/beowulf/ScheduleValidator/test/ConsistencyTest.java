package com.beowulf.ScheduleValidator.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.allen.temporalintervalrelationships.Constraint;
import org.allen.temporalintervalrelationships.ConstraintNetwork;
import org.allen.temporalintervalrelationships.Node;

import com.beowulf.ScheduleValidator.model.*;

public class ConsistencyTest
{

    // Test class that provides static test-methods, which all take a
    // University-Object as parameter

    static public ArrayList<String> testConstraintsOnly(University uni)
    {
        return testDefinitions(uni, true);
    }

    static public ArrayList<String> testConstraintsWithTimetable(University uni)
    {
        return testDefinitions(uni, false);
    }

    static private ArrayList<String> testDefinitions(University uni, boolean defsOnly)
    {

        ConstraintNetwork<Lecture> myConstraintNetwork = new ConstraintNetwork<Lecture>();
        HashMap<String, Node<Lecture>> hashedmap = new HashMap<String, Node<Lecture>>();
        HashMap<String, Constraint<Lecture>> constraintmap = new HashMap<String, Constraint<Lecture>>();
        ArrayList<String> errorMessages = new ArrayList<String>();

        String idBoth, id1, id2;

        for (Relation rel : uni.getRelations())
        {
            Node<Lecture> nodeA;
            Node<Lecture> nodeB;

            id1 = rel.getX1().getName() + rel.getX1().getProf() + rel.getX1().getCourse();
            id2 = rel.getX2().getName() + rel.getX2().getProf() + rel.getX2().getCourse();
            idBoth = id1 + id2;

            if ((nodeA = hashedmap.get(id1)) == null)
            {

                nodeA = new Node<Lecture>(rel.getX1());
                hashedmap.put(id1, nodeA);
                myConstraintNetwork.addNode(nodeA);

            }

            if ((nodeB = hashedmap.get(id2)) == null)
            {

                nodeB = new Node<Lecture>(rel.getX2());
                hashedmap.put(id2, nodeB);
                myConstraintNetwork.addNode(nodeB);
            }

            Short vektor = 0;

            if (constraintmap.get(idBoth) != null)
            {
                vektor = constraintmap.get(idBoth).getConstraints();

                if (!defsOnly)
                {
                    short allen = 0;
                    for (Short cons : rel.getCons())
                    {
                        allen = (short) (allen | cons);
                    }
                    if ((short) (vektor & allen) == 0)
                    {

                        errorMessages.add("[Conflict] The constraint says: " + rel.getX1().getName() + ", " + rel.getX1().getProf() + " " + rel.getX1().getCourse()
                                + myConstraintNetwork.getConstraintStringFromConstraintShort(vektor) + " " + rel.getX2().getName() + ", " + rel.getX2().getProf() + " "
                                + rel.getX2().getCourse());

                    }
                    vektor = 0;
                }

            }

            for (Short allen : rel.getCons())
            {
                vektor = (short) (vektor | allen);
            }

            constraintmap.put(idBoth, new Constraint<Lecture>(nodeA, nodeB, vektor));

        }

        for (Constraint<Lecture> value : constraintmap.values())
        {
            myConstraintNetwork.addConstraint(value);
        }

        if (defsOnly)
        {
            if (myConstraintNetwork.pathConsistency())
            {
                errorMessages.add(">>> Constraints from the constraintsfile are consistent");
            }
            else
            {
                errorMessages.add("[ERROR] Constraints are NOT consistent");
            }
        }

        return errorMessages;

    }

    // Checks if every lecture starts AFTER the opening time
    public static boolean testOpening(University myUni, String pOpening)
    {

        String[] temp = pOpening.split(":");

        int opening = new Integer(temp[0] + temp[1]);

        for (Lecture lec : myUni.getLectures().values())
        {
            if (lec.getStart() < opening && lec.getStart() != 0)
            {
                return false;
            }

        }
        return true;
    }

    // Test if a course has 2 lectures at the same time
    public static HashMap<String, HashMap<String, String>> testCourses(University myUni)
    {
        HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
        for (String course : myUni.getCourses().values())
        {
            ArrayList<Lecture> tempLectures = new ArrayList<Lecture>();
            for (Lecture lec : myUni.getLectures().values())
            {
                if (course.equals(lec.getCourse()))
                    tempLectures.add(lec);
            }
            HashMap<String, String> faults = validateLectures(tempLectures, myUni.getRules());
            if (faults.size() > 0)
                errors.put(course, faults);
        }

        return errors;
    }

    // Test if a prof has 2 lectures at the same time
    public static HashMap<String, HashMap<String, String>> testProfessors(University myUni)
    {
        HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
        for (String prof : myUni.getProfs().values())
        {
            ArrayList<Lecture> tempLectures = new ArrayList<Lecture>();
            for (Lecture lec : myUni.getLectures().values())
            {
                if (prof.equals(lec.getProf()))
                    tempLectures.add(lec);
            }
            HashMap<String, String> faults = validateLectures(tempLectures, myUni.getRules());
            if (faults.size() > 0)
                errors.put(prof, faults);
        }

        return errors;
    }

    // Utility method to check if a list of lectures overlap
    private static HashMap<String, String> validateLectures(ArrayList<Lecture> lecs, ArrayList<Relation> rels)
    {
        HashMap<String, String> faults = new HashMap<String, String>();

        // Iterate over all lectures and compare the times with every other
        // lecture
        boolean valid;
        for (Lecture l1 : lecs)
        {
            for (Lecture l2 : lecs)
            {
                if (!(l1.getEnd() <= l2.getStart() || l1.getStart() >= l2.getEnd()) && l1.getId() != l2.getId())
                {
                    valid = false;
                    // Check if it is permitted by a
                    // during/contains/finishes/finishedby constraint
                    for (Relation r : rels)
                    {
                        if ((r.getX1().getId() == l1.getId() || r.getX1().getId() == l2.getId()) && (r.getX2().getId() == l1.getId() || r.getX2().getId() == l2.getId()))
                        {
                            for (Short constraint : r.getCons())
                            {
                                if (constraint == ConstraintNetwork.bin_during || constraint == ConstraintNetwork.bin_contains || constraint == ConstraintNetwork.bin_finishes
                                        || constraint == ConstraintNetwork.bin_finishedby)
                                    valid = true;
                            }
                        }
                    }

                    if (!valid)
                    {
                        String key = "";
                        if (l1.getId() < l2.getId())
                            key = String.valueOf(l1) + String.valueOf(l2);
                        else
                            key = String.valueOf(l2) + String.valueOf(l1);
                        faults.put(key, l1.toString() + " and " + l2.toString());
                    }
                }
            }
        }
        return faults;
    }

}
