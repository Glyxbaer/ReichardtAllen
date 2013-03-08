package com.beowulf.ScheduleValidator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.beowulf.ScheduleValidator.model.Lecture;
import com.beowulf.ScheduleValidator.model.Relation;
import com.beowulf.ScheduleValidator.model.University;

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
			while ((line = br.readLine()) != null && !line.equals("")) {
				if (!(line.substring(0, 2).equals("//"))) {
					String[] data = line.split(";");
					Lecture lec = new Lecture(Integer.valueOf(data[0]),
							data[1], data[2], data[3]);
					uni.addLecture(data[0], lec);
				}
			}
			br.close();

			// Read the constraintFile and add each line as an object to uni
			br = new BufferedReader(new FileReader(constraintFile));
			while ((line = br.readLine()) != null && !line.equals("")) {
				if (!(line.substring(0, 2).equals("//"))) {
					String[] data = line.split(";");
					Lecture l1 = uni.getLectures().get(data[0]);
					Lecture l2 = uni.getLectures().get(data[1]);
					String[] cons = data[2].split(",");
					Relation rel = new Relation(l1, l2);
					rel.addConstraintsAsStrings(cons);
					uni.addRelation(rel);
					uni.addRule(rel);
				}
			}
			br.close();

			// Read the planFile and add the values of each line to the
			// corresponding objects of uni
			br = new BufferedReader(new FileReader(planFile));
			HashMap<String, Lecture> lectures = uni.getLectures();
			while ((line = br.readLine()) != null && !line.equals("")) {
				if (!(line.substring(0, 2).equals("//"))) {
					String[] data = line.split(";");
					lectures.get(data[2]).setStart(Integer.valueOf(data[0]));
					lectures.get(data[2]).setEnd(Integer.valueOf(data[1]));
				}
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return convertTimesToConstraints(uni);

	}

	public University parseConstraintsOnly() {

		University uni = new University();
		BufferedReader br;
		String line;

		try {
			// Read the definitionFile and add each line as an object to uni
			br = new BufferedReader(new FileReader(definitionFile));
			while ((line = br.readLine()) != null && !line.equals("")) {
				if (!(line.substring(0, 2).equals("//"))) {
					String[] data = line.split(";");
					Lecture lec = new Lecture(Integer.valueOf(data[0]),
							data[1], data[2], data[3]);
					uni.addLecture(data[0], lec);
				}
			}
			br.close();

			// Read the constraintFile and add each line as an object to uni
			br = new BufferedReader(new FileReader(constraintFile));
			while ((line = br.readLine()) != null && !line.equals("")) {
				if (!(line.substring(0, 2).equals("//"))) {
					String[] data = line.split(";");
					Lecture l1 = uni.getLectures().get(data[0]);
					Lecture l2 = uni.getLectures().get(data[1]);
					String[] cons = data[2].split(",");
					Relation rel = new Relation(l1, l2);
					rel.addConstraintsAsStrings(cons);
					uni.addRelation(rel);
					uni.addRule(rel);
				}
			}
			br.close();

			// Read the planFile and add the values of each line to the
			// corresponding objects of uni
			br = new BufferedReader(new FileReader(planFile));
			HashMap<String, Lecture> lectures = uni.getLectures();
			while ((line = br.readLine()) != null && !line.equals("")) {
				if (!(line.substring(0, 2).equals("//"))) {
					String[] data = line.split(";");
					lectures.get(data[2]).setStart(Integer.valueOf(data[0]));
					lectures.get(data[2]).setEnd(Integer.valueOf(data[1]));
				}
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return uni;

	}

	// Iterate over all lectures and convert their starttime and endtime to
	// constraints
	private University convertTimesToConstraints(University uni) {

		ArrayList<Relation> rels = uni.getRelations();
		HashMap<String, Lecture> lecs = uni.getLectures();
		// Compare the times of every lecture to every the times of every other
		// lecture
		for (Entry<String, Lecture> e1 : lecs.entrySet()) {
			String l1_key = e1.getKey();
			Lecture l1_value = e1.getValue();
			for (Entry<String, Lecture> e2 : lecs.entrySet()) {
				String l2_key = e2.getKey();
				Lecture l2_value = e2.getValue();
				// if it's not the same lecture, make a relation
				if (!l1_key.equals(l2_key)) {
					Relation r = new Relation(l1_value, l2_value);

					int l1_start = l1_value.getStart();
					int l1_end = l1_value.getEnd();
					int l2_start = l2_value.getStart();
					int l2_end = l2_value.getEnd();

					// equals
					if (l1_start == l2_start && l1_end == l2_end)
						r.addConstraintsAsStrings(new String[] { "=" });
					// meets
					if (l1_end == l2_start)
						r.addConstraintsAsStrings(new String[] { "m" });
					// met by
					if (l1_start == l2_end)
						r.addConstraintsAsStrings(new String[] { "mi" });
					// before
					if (l1_end < l2_start)
						r.addConstraintsAsStrings(new String[] { "<" });
					// after
					if (l1_start > l2_end)
						r.addConstraintsAsStrings(new String[] { ">" });
					// overlaps
					if (l1_start < l2_start && l2_start < l1_end
							&& l2_end > l1_end)
						r.addConstraintsAsStrings(new String[] { "o" });
					// overlapped by
					if (l1_start > l2_start && l1_start < l2_end
							&& l1_end > l2_end)
						r.addConstraintsAsStrings(new String[] { "oi" });
					// finishes
					if (l1_start > l2_start && l1_end == l2_end)
						r.addConstraintsAsStrings(new String[] { "f" });
					// finished by
					if (l1_start < l2_start && l1_end == l2_end)
						r.addConstraintsAsStrings(new String[] { "fi" });
					// during
					if (l1_start > l2_start && l1_end < l2_end)
						r.addConstraintsAsStrings(new String[] { "d" });
					// contains
					if (l1_start < l2_start && l1_end > l2_end)
						r.addConstraintsAsStrings(new String[] { "di" });

					rels.add(r);
				}

			}

		}

		return uni;
	}

}
