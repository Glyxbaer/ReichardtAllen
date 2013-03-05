package com.beowulf.ScheduleValidator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.allen.temporalintervalrelationships.ConstraintNetwork;

import com.beowulf.ScheduleValidator.model.*;

public class FileParser {

	private File constraintFile = null;
	private File definitionFile = null;
	private File planFile = null;

	public FileParser(File constraintFile, File definitionFile, File planFile) {
		this.constraintFile = constraintFile;
		this.definitionFile = definitionFile;
		this.planFile = planFile;
	}

	public University parse() {

		University uni = new University();
		BufferedReader br;
		String line;

		try {
			// Read the definitionFile and add each line as an object to uni
			br = new BufferedReader(new FileReader(definitionFile));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(";");
				Lecture lec = new Lecture(data[1], data[2], data[3]);
				uni.addLecture(data[0], lec);
			}
			br.close();

			// Read the constraintFile and add each line as an object to uni
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
						break;
					case "<":
						rel.addConstraint(ConstraintNetwork.bin_before);
						break;
					case ">":
						rel.addConstraint(ConstraintNetwork.bin_after);
						break;
					case "d":
						rel.addConstraint(ConstraintNetwork.bin_during);
						break;
					case "di":
						rel.addConstraint(ConstraintNetwork.bin_contains);
						break;
					case "o":
						rel.addConstraint(ConstraintNetwork.bin_overlaps);
						break;
					case "oi":
						rel.addConstraint(ConstraintNetwork.bin_overlappedby);
						break;
					case "m":
						rel.addConstraint(ConstraintNetwork.bin_meets);
						break;
					case "mi":
						rel.addConstraint(ConstraintNetwork.bin_metby);
						break;
					case "s":
						rel.addConstraint(ConstraintNetwork.bin_starts);
						break;
					case "si":
						rel.addConstraint(ConstraintNetwork.bin_startedby);
						break;
					case "f":
						rel.addConstraint(ConstraintNetwork.bin_finishes);
						break;
					case "fi":
						rel.addConstraint(ConstraintNetwork.bin_finishedby);
						break;
					}
				}
				uni.addRelation(rel);
			}
			br.close();

			// Read the planFile and add the values of each line to the
			// corresponding objects of uni
			br = new BufferedReader(new FileReader(planFile));
			HashMap<String, Lecture> lectures = uni.getlectures();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(";");
				lectures.get(data[2]).setStart(Integer.valueOf(data[0]));
				lectures.get(data[2]).setEnd(Integer.valueOf(data[1]));
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return uni;

	}

}
