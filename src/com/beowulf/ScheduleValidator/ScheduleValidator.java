package com.beowulf.ScheduleValidator;

import java.io.File;

import com.beowulf.ScheduleValidator.model.*;
import com.beowulf.ScheduleValidator.test.ConsistencyTest;
import com.beowulf.ScheduleValidator.util.FileParser;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class ScheduleValidator {

	public static void main(String[] args) {

		FileParser myParser = new FileParser(
				new File("conf\\constraints.conf"), new File(
						"conf\\definitions.conf"), new File("conf\\plan.conf"));

		University myUni = myParser.parseConstraintsOnly();
		University myUni2 = myParser.parse();

		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the opening time (Format: HH:MM): ");

		if (!ConsistencyTest.testOpening(myUni, input.next())) {
			System.out
					.println(">>> Opening hours do not fit the timetable. The University has to be opened BEFORE the first lecture starts.");
		}

		if (ConsistencyTest.testDefinitions(myUni, true)) {
			System.out.println(">>> Constraints are consistent.. ");

			if (ConsistencyTest.testDefinitions(myUni2, false)) {
				System.out
						.println("[ERROR] There were conflicts in the timetable. Please refer to the errormessages above.");
			} else {
				System.out
						.println(">>> There were no conflicts in the timetable..");
			}
		} else {
			System.out.println("Definitions are NOT consistent.");
		}

		// Course-Lecture testing
		HashMap<String, HashMap<String, String>> courseErrors = ConsistencyTest
				.testCourses(myUni2);
		if (courseErrors.size() > 0) {
			System.out
					.println("[ERROR] There were conflicts in the course-lecture correlation:");
			for (Entry<String, HashMap<String, String>> entry : courseErrors
					.entrySet()) {
				System.out.println("\t[Conflict] The course " + entry.getKey()
						+ " cannot have two lectures at the same time:");
				for (String string : entry.getValue().values())
					System.out.println("\t\t" + string);
			}
		}

		// Professor-Lecture testing
		HashMap<String, HashMap<String, String>> profErrors = ConsistencyTest
				.testProfessors(myUni2);
		if (profErrors.size() > 0) {
			System.out
					.println("[ERROR] There were conflicts in the professor-lecture correlation:");
			for (Entry<String, HashMap<String, String>> entry : profErrors
					.entrySet()) {
				System.out.println("\t[Conflict] The professor "
						+ entry.getKey()
						+ " cannot have two lectures at the same time:");
				for (String string : entry.getValue().values())
					System.out.println("\t\t" + string);
			}
		}

	}

}
