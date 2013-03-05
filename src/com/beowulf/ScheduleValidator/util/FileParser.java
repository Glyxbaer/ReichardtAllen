package com.beowulf.ScheduleValidator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.allen.temporalintervalrelationships.ConstraintNetwork;

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
					String[] cons = data[2].split(",");
					Relation rel = new Relation(l1, l2);
					for (int x = 0; x < cons.length; x++) {
						switch (cons[x]) {
						case "=":
							rel.addConstraint(ConstraintNetwork.bin_equals);
						case "<":
							rel.addConstraint(ConstraintNetwork.bin_before);
						case ">":
							rel.addConstraint(ConstraintNetwork.bin_after);
						case "d":
							rel.addConstraint(ConstraintNetwork.bin_during);
						case "di":
							rel.addConstraint(ConstraintNetwork.bin_contains);
						case "o":
							rel.addConstraint(ConstraintNetwork.bin_overlaps);
						case "oi":
							rel.addConstraint(ConstraintNetwork.bin_overlappedby);
						case "m":
							rel.addConstraint(ConstraintNetwork.bin_meets);
						case "mi":
							rel.addConstraint(ConstraintNetwork.bin_metby);
						case "s":
							rel.addConstraint(ConstraintNetwork.bin_starts);
						case "si":
							rel.addConstraint(ConstraintNetwork.bin_startedby);
						case "f":
							rel.addConstraint(ConstraintNetwork.bin_finishes);
						case "fi":
							rel.addConstraint(ConstraintNetwork.bin_finishedby);
						}

						rel.addConstraint(cons[x]);
					}

					uni.addRelation(rel);
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
