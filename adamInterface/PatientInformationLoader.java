package adamInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import adamProtocol.BloodCounts;
import adamProtocol.Patient;
import adamProtocol.Prediction;
import adamProtocol.Predictor;
import adamProtocol.Dose;

class PatientInformationLoader {
	static Scanner scanInput;
	static Predictor predictor = new Predictor();

	static Patient createPatient(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		line = br.readLine();
		String id = line.split(",")[1];

		line = br.readLine();
		Date startMTDate = getDate(line.split(",")[1]);

		line = br.readLine();
		double height = Double.parseDouble(line.split(",")[1]);

		line = br.readLine();
		double weight = Double.parseDouble(line.split(",")[1]);

		// Read the header
		br.readLine();
		Patient testCase = new Patient(startMTDate, id, height, weight);


		while ((line = br.readLine()) != null) {
			parseAndProcessRecord(testCase, line);
		}
		br.close();
		return testCase;
	}

	private static void parseAndProcessRecord(Patient testCase, String line) {
		Prediction prediction;
		ArrayList<String> record = new ArrayList<>(Arrays.asList(line.split(",")));
		Date recordDate = getDate(record.get(InputHeader.CURRENT_DATE));

		BloodCounts bloodCount = new BloodCounts(Double.parseDouble(record.get(InputHeader.NEUTROPHIL))*BloodCounts.NEUTROPHIL_UNIT,
		Double.parseDouble(record.get(InputHeader.PLATELET))*BloodCounts.PLATELET_UNIT);
		testCase.addVisit(recordDate, bloodCount);
        testCase.setHistoricalDose(Dose.roundOff(Double.parseDouble(record.get(InputHeader.MTX)), 
				Double.parseDouble(record.get(InputHeader.SMP))));
	}

	private static Date getDate(String dateString) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
