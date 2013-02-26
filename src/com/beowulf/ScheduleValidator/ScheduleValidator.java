package com.beowulf.ScheduleValidator;
import org.allen.temporalintervalrelationships.*;

import java.io.File;

import com.beowulf.ScheduleValidator.model.*;
import com.beowulf.ScheduleValidator.test.ConsistencyTest;
import com.beowulf.ScheduleValidator.util.FileParser;

public class ScheduleValidator {

	public static void main(String[] args) {

		FileParser myParser = new FileParser(new File(""));

		University myUni = myParser.parse();

		ConsistencyTest myTest = new ConsistencyTest(myUni);

		if (myTest.getResult()) {
			System.out.println("Timetable is consistent");
		} else {
			System.out.println("Errors in timetable:\n" + myTest.getMsg());
		}

	}

}
