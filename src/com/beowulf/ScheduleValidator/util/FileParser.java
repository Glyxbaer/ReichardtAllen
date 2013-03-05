package com.beowulf.ScheduleValidator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.beowulf.ScheduleValidator.model.*;

public class FileParser {

	private File constraintFile = null;
	private File definitionFile = null;
	private File planFile = null;

	public FileParser(File constraintFile, File definitionFile) {
		this.constraintFile = constraintFile;
		this.definitionFile = definitionFile;
	}

	public FileParser(File planFile) {
		this.planFile = planFile;
	}

	public University parse() {

		University uni = new University();
		BufferedReader br;

		if (planFile == null) {
			// Use Case 1: constraintFile and definitionFile

			// Read the definitionFile and add the lines as objects to uni
			try {
				br = new BufferedReader(new FileReader(definitionFile));
				String line;
				while ((line = br.readLine()) != null) {
					String[] data = line.split(";");
					Prof prof = new Prof(data[2]);
					Lecture lec = new Lecture(data[1], prof);
					prof.addLecture(data[0], lec);
					
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			// Use Case 2: planFile

		}


		// TODO fill uni

		return uni;

	}

}
