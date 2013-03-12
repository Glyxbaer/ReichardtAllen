package com.beowulf.ScheduleValidator;

import java.io.File;

import com.beowulf.ScheduleValidator.model.*;
import com.beowulf.ScheduleValidator.test.ConsistencyTest;
import com.beowulf.ScheduleValidator.util.FileParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class ScheduleValidator
{

    public static void main(String[] args)
    {

        FileParser myParser = new FileParser(new File("conf\\A001\\A001_Beziehungen.csv"), new File("conf\\A001\\A001_Veranstaltungen.csv"), new File(
                "conf\\A001\\A001_Stundenplan_A.csv"));

        University myUni = myParser.parse();

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the opening time (Format: HH:MM): ");

        // Check if the opening hours fit the timetable
        if (!ConsistencyTest.testOpening(myUni, input.next()))
        {
            System.out.println("[ERROR] Please open the university earlier. The University has to be opened BEFORE the first lecture starts.");
        }
        input.close();

        // Check if the constraints from the constraintsfile are consistent
        for (String msg : ConsistencyTest.testConstraintsOnly(myUni))
        {
            System.out.println(msg);
        }

        // Check if there exist any errors between the constraintsfile and the
        // timetable
        ArrayList<String> errMsgs = ConsistencyTest.testConstraintsWithTimetable(myUni);
        if (errMsgs.size() > 0)
        {
            System.out.println("[ERROR] There are conflicts between the constraints and the timetable:");
            for (String msg : errMsgs)
            {
                System.out.println("\t" + msg);
            }
        }
        else
        {
            System.out.println(">>> There are no conflicts between the constraints and the timetable.");
        }

        // Course-Lecture testing
        HashMap<String, HashMap<String, String>> courseErrors = ConsistencyTest.testCourses(myUni);
        if (courseErrors.size() > 0)
        {
            System.out.println("[ERROR] There are conflicts in the course-lecture correlation:");
            for (Entry<String, HashMap<String, String>> entry : courseErrors.entrySet())
            {
                System.out.println("\t[Conflict] The course " + entry.getKey() + " cannot have two lectures at the same time:");
                for (String string : entry.getValue().values())
                    System.out.println("\t\t" + string);
            }
        }
        else
        {
            System.out.println(">>> There are no interferences of the courses.");
        }

        // Professor-Lecture testing
        HashMap<String, HashMap<String, String>> profErrors = ConsistencyTest.testProfessors(myUni);
        if (profErrors.size() > 0)
        {
            System.out.println("[ERROR] There are conflicts in the professor-lecture correlation:");
            for (Entry<String, HashMap<String, String>> entry : profErrors.entrySet())
            {
                System.out.println("\t[Conflict] The professor " + entry.getKey() + " cannot have two lectures at the same time:");
                for (String string : entry.getValue().values())
                    System.out.println("\t\t" + string);
            }
        }
        else
        {
            System.out.println(">>> There are no interferences of the professors.");
        }

    }
}
