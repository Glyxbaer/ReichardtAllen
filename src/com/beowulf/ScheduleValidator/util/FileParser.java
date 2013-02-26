package com.beowulf.ScheduleValidator.util;

import java.io.File;

import com.beowulf.ScheduleValidator.model.*;

public class FileParser {

	File constraintFile;

	public FileParser(File constraintFile) {
		this.constraintFile = constraintFile;
	}

	public University parse() {

		University uni = new University();

		// TODO fill uni

		return uni;

	}

}
