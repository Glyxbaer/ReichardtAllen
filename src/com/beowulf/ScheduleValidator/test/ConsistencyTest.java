package com.beowulf.ScheduleValidator.test;

import com.beowulf.ScheduleValidator.model.*;

public class ConsistencyTest {

	private boolean result;
	private String msg;

	public ConsistencyTest(University uni) {

		test(uni);

	}

	public boolean getResult() {
		return result;
	}

	public String getMsg() {
		return msg;
	}

	private void test(University uni) {

		// TODO test everything for consistency here
		result = false;
		msg = "Alles falsch";

	}

}
