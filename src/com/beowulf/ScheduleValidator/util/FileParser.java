package com.beowulf.ScheduleValidator.util;

import java.io.File;

import com.beowulf.ScheduleValidator.model.*;

public class FileParser {

	File file;

	public FileParser(File file) {
		this.file = file;
	}

	public University parse() {

		University uni = new University();

		// TODO fill uni

		return uni;

	}

}
