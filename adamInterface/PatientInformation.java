/**
 * 
 */
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
import adamProtocol.exceptions.OutOfBoundsDoseException;

/**
 * @author Tushar
 *
 */

class PatientInformation {

	static Scanner scanInput;
	static Predictor predictor = new Predictor();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		scanInput = new Scanner(System.in);
		// Patient patient;

		FileWriter outFileWriter = null;

		/* Start maintenance with new patient */
		String[] filePathInformation = getFilePathInformation();

		try {
			outFileWriter = new FileWriter(filePathInformation[1]);
			createPatient(filePathInformation[0], outFileWriter);
			outFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * InputStreamReader isr = new InputStreamReader(System.in); BufferedReader br =
		 * new BufferedReader(isr);
		 * 
		 * System.out.print("Enter Your Name: ");
		 * 
		 * try { String keyboardInput = br.readLine(); if
		 * (keyboardInput.trim().equals("")) { System.out.println("No input detected!");
		 * } System.out.print("Your Name is: " + keyboardInput); } catch (IOException e)
		 * { e.printStackTrace(); }
		 */

	}

	private static Patient createPatient(String fileName, FileWriter outFile) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			line = br.readLine();
			outFile.write(line + "\n");
			String id = line.split(",")[1];

			line = br.readLine();
			outFile.write(line + "\n");
			Date startMTDate = getDate(line.split(",")[1]);

			line = br.readLine();
			outFile.write(line + "\n");
			double height = Double.parseDouble(line.split(",")[1]);

			line = br.readLine();
			outFile.write(line + "\n");
			double weight = Double.parseDouble(line.split(",")[1]);

			// Read the header
			line = br.readLine();
			Patient testCase = new Patient(startMTDate, id, height, weight);
			String outFileHeader = String.join(",", "Cycle", "Week", "Current Date", "Neutrophil", "Platelets", "6MP",
					"MTX", "Predicted 6MP", "Predicted MTX", "Comments");

			outFile.write(outFileHeader + "\n");
			outFile.flush();

			while ((line = br.readLine()) != null) {
				String fileOutput = parseAndProcessRecord(testCase, line);
				outFile.write(fileOutput + "\n");
				outFile.flush();
			}
			br.close();
			return testCase;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print(
					"Patient maintenance data file does not seem to exit. Please ensure yo uhave followed the starting instructions while running the program");
			e.printStackTrace();
		}
		return null;
	}

	private static String parseAndProcessRecord(Patient testCase, String line) {
		Prediction prediction;
		ArrayList<String> record = new ArrayList<>(Arrays.asList(line.split(",")));
		Date recordDate = getDate(record.get(InputHeader.CURRENT_DATE));

		testCase.setCurrentDate(recordDate);
		testCase.setCount(Double.parseDouble(record.get(InputHeader.NEUTROPHIL))/1000*BloodCounts.BILLION,
			Double.parseDouble(record.get(InputHeader.PLATELET))*BloodCounts.BILLION);

		printTestCaseDetails(testCase);

		try {
			prediction = predictor.predictFor(testCase);
			Dose predictedDose = prediction.getDose();
			testCase.setCurrentDose(predictedDose);
			return String.join(",", String.valueOf(testCase.getCycle()), String.valueOf(testCase.getWeekInCycle()), 
				record.get(InputHeader.CURRENT_DATE), record.get(InputHeader.NEUTROPHIL), record.get(InputHeader.PLATELET), 
				record.get(InputHeader.SMP), record.get(InputHeader.MTX), 
				String.valueOf(predictedDose.getSmp()), String.valueOf(predictedDose.getMtx()), prediction.getComments());
		} catch (OutOfBoundsDoseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static void printTestCaseDetails(Patient testCase) {
		System.out.print("########## Current Visit Details ##########");
		System.out.print("\nCycle: " + testCase.getCycle());
		System.out.print("\nWeek: " + testCase.getWeekInCycle());
		BloodCounts bldCnts = testCase.getBloodCounts();
		System.out.print("\nNeutrophil Counts: " + bldCnts.neutrophilCount);
		System.out.print("\nPlatelet Counts: " + bldCnts.plateletCount);
		System.out.print("\n");
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

	static String[] getFilePathInformation() {
		System.out.println(
				"Hello! Please create a folder at " + System.getProperty("user.dir") + " and name it \"Patients\".  ");
		System.out.println("Please create a folder inside \"Patients\" folder with a name \"Results\" ");
		System.out
				.println("If above 2 steps are not done, please exit the program by pressing \"E\". Else press \"C\" ");
		if (scanInput.nextLine().equalsIgnoreCase("E"))
			System.exit(0);
		System.out.println("Please enter patient ID: ");

		String patId = scanInput.nextLine();

		String[] fileList = new String[2];
		fileList[0] = "./Patients/" + patId + ".csv";
		fileList[1] = "./Patients/Results/" + patId + ".csv";
		return fileList;
	}

}
