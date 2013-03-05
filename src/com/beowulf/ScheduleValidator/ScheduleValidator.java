package com.beowulf.ScheduleValidator;

import java.io.File;

import com.beowulf.ScheduleValidator.model.*;
import com.beowulf.ScheduleValidator.test.ConsistencyTest;
import com.beowulf.ScheduleValidator.util.FileParser;

public class ScheduleValidator {

	public static void main(String[] args) {

		FileParser myParser = new FileParser(
				new File("conf\\constraints.conf"), new File(
						"conf\\definitions.conf"));

		University myUni = myParser.parse();
		myUni.print();

		// ConsistencyTest myTest = new ConsistencyTest(myUni);
		//
		// if (myTest.getResult()) {
		// System.out.println("Timetable is consistent");
		// } else {
		// System.out.println("Errors in timetable:\n" + myTest.getMsg());
		// }

	}

}
