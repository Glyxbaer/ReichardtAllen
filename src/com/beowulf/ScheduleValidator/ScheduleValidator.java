package com.beowulf.ScheduleValidator;

import java.io.File;

import com.beowulf.ScheduleValidator.model.*;
import com.beowulf.ScheduleValidator.test.ConsistencyTest;
import com.beowulf.ScheduleValidator.util.FileParser;

public class ScheduleValidator {

	public static void main(String[] args) {

		FileParser myParser = new FileParser(
				new File("conf\\constraints.conf"), new File(
						"conf\\definitions.conf"), new File("conf\\plan.conf"));

		University myUni = myParser.parse();
		
		
		 if (ConsistencyTest.testDefinitions(myUni)) {
		 System.out.println("Timetable is consistent.");
		 } else {
		 System.out.println("Timetable is NOT consistent.");
		 }

	}

}
