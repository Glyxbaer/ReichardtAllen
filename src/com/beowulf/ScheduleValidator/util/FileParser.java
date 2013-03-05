package com.beowulf.ScheduleValidator.util;

import java.io.BufferedReader;
import java.io.File;
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
		String line;

		if (planFile == null) {
			// Use Case 1: constraintFile and definitionFile

			try {
				// Read the definitionFile and add the lines as objects to uni
				br = new BufferedReader(new FileReader(definitionFile));
				while ((line = br.readLine()) != null) {
					String[] data = line.split(";");
					Lecture lec = new Lecture(data[1], data[2]);
					uni.addLecture(data[0], lec);
				}
				br.close();
				
				// Read the constraintFile and add the lines as objects to uni
				br = new BufferedReader(new FileReader(constraintFile));
				while ((line = br.readLine()) != null) {
					String[] data = line.split(";");
					Lecture l1 = uni.getlectures().get(data[0]);
					Lecture l2 = uni.getlectures().get(data[1]);
					String[] rels = data[2].split(",");
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
