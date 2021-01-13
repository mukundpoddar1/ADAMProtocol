package adamInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import adamProtocol.BloodCounts;
import adamProtocol.DisplayMessage;
import adamProtocol.Dose;
import adamProtocol.Patient;
import adamProtocol.Prediction;
import adamProtocol.Predictor;

/**
 * Used for live Patient in the clinic
 * @author mukund
 *
 */
public class LivePatient {
   
	// static Scanner s;
	// static final int WEEK=7;
	// static Predictor predictor = new Predictor();
	// static boolean isInformationComplete = false;
	// /**
	//  * Will create patient from supplied file and then continue with a new visit.
	//  * Will write details to a new file in folders output
	//  * @param args
	//  */
	// public static void main(String[] args) {
	// 	s = new Scanner(System.in);
	// 	Patient patient;
	// 	String file[] = getFilePaths();
	// 	FileWriter outFileWriter = null;
	// 	try {
	// 		outFileWriter = new FileWriter(file[1]);
	// 		DisplayMessage.suppressed = true;
	// 		patient = createPatientFromFile(file[0], outFileWriter);
	// 		DisplayMessage.suppressed = false;
	// 		outFileWriter.write(currentVisit(patient)+"\n");
	// 		outFileWriter.flush();
	// 		outFileWriter.close();
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	} catch (MissingValueException e) {
	// 		e.printStackTrace();
	// 	}
	// }
	
	// /**
	//  * 
	//  * @param patient
	//  * @return CSV output of details of current visit after conclusion
	//  */
	// private static String currentVisit(Patient patient) {
	// 	patient.setCurrentDate(Calendar.getInstance().getTime());
	// 	System.out.println("Enter current neutrophil and platelet count: ");
	// 	patient.setCount(s.nextInt(), s.nextInt());
	// 	Prediction prediction = predictor.predictFor(patient);
	// 	Dose predictedDose = prediction.getDose();
	// 	System.out.println("Predicted " + predictedDose);
	// 	System.out.println("-------------------------");
		
	// 	formattedPrint(patient);
		
	// 	System.out.println("What dose do you prescribe? Write as 6mp <space> mtx:");
	// 	Dose prescribedDose = getDoctorDose();
	// 	patient.setCurrentDose(prescribedDose);
		
	// 	return String.join(",", String.valueOf(patient.getCycle()), String.valueOf(patient.getWeekInCycle()), formatDate(patient.getCurrentDate()), String.valueOf(patient.getCount().neutrophil), String.valueOf(patient.getCount().platelets), String.valueOf(patient.getCurrentDose().getSmp()), String.valueOf(patient.getCurrentDose().getMtx()), String.valueOf(patient.getCurrentDose().isItmtx()), String.valueOf(predictedDose.getSmp()), String.valueOf(predictedDose.getMtx()), prediction.getComments());
	// }

	// private static String formatDate(Calendar currentDate) {
	// 	DateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
	// 	return format.format(currentDate.getTime());
	// }

	// /**
	//  * Creates a Patient with all relevant details
	//  * @param fileName
	//  * @param outFileWriter
	//  * @return The object Patient with all details
	//  * @throws MissingValueException
	//  */
	// private static Patient createPatientFromFile(String fileName, FileWriter outFileWriter) throws MissingValueException {
	// 	try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
	// 		String line;
	// 		line = br.readLine();
	// 		outFileWriter.write(line+"\n");
	// 		String id = line.split(",")[1];
	// 		line = br.readLine();
	// 		outFileWriter.write(line+"\n");
	// 		Date startDate = getDate(line.split(",")[1]);
	// 		line = br.readLine();
	// 		outFileWriter.write(line+"\n");
	// 		double height = Double.parseDouble(line.split(",")[1]);

	// 		line = br.readLine();
	// 		outFileWriter.write(line + "\n");
	// 		double weight = Double.parseDouble(line.split(",")[1]);

	// 		Patient patient = new Patient(startDate, id, height, weight);
			
	// 		String headerRow = String.join(",", "Cycle", "Week", "Current Date", "Neutrophil", "Platelets", "6MP", "MTX", "Predicted 6MP", "Predicted MTX", "Comments");
	// 		outFileWriter.write(headerRow + "\n");
	// 		outFileWriter.flush();
			
	// 		br.readLine(); //To ignore the header row from the input file
	// 		return patient;
	// 	} catch (IOException e) {
	// 		System.out.println("File does not seem to exist. Please check full path of file.");
	// 		e.printStackTrace();
	// 	}
	// 	return null;
	// }
	
	// /**
	//  * Takes a line from CSV file, and adds values in Patient as a record for that visit
	//  * @param test
	//  * @param line
	//  * @throws MissingValueException
	//  */
	// private static void processRecord(Patient test, String line) {
	// 	ArrayList<String> record = new ArrayList<>(Arrays.asList(line.split(",")));
	// 	if(record.size()<5) 
	// 		throw new MissingValueException();
	// 	Date recordDate = getDate(record.get(MinimalColumns.CURRENT_DATE));
	// 	Dose dose = new Dose(Double.parseDouble(record.get(MinimalColumns.MTX)), Double.parseDouble(record.get(MinimalColumns.SMP)));
		
	// 	test.setCurrentDate(recordDate);
	// 	test.setCount(Integer.parseInt(record.get(MinimalColumns.NEUTROPHIL)), Integer.parseInt(record.get(MinimalColumns.PLATELET)));
	// 	test.setCurrentDose(dose);
	// }
	
	// private static Date getDate(String dateString) {
	// 	DateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
	// 	Date date = null;
	// 	try {
	// 		date = format.parse(dateString);
	// 	} catch (ParseException e) {
	// 		e.printStackTrace();
	// 	}
	// 	return date;
	// }
	
	// /**
	//  * Mentions instructions to follow and asks for Patient for whom to run live program
	//  * @return Input and output file paths
	//  */
	// private static String[] getFilePaths() {
	// 	System.out.println("Please create a folder in "+System.getProperty("user.dir")+" and name it \"Patients\". Store all patient CSV files inside it");
	// 	System.out.println("Please create a folder inside \"Patients\" folder called \"Outputs\". If done, proceed with next step, otherwise exit the program.");
	// 	System.out.print("Please enter patient ID: ");
	// 	String fileName = s.nextLine() + ".csv";
	// 	String parentPath = "./Patients/";
	// 	String[] file = new String[2];
	// 	file[0] = parentPath + fileName;
	// 	file[1] = parentPath + "Outputs/" + fileName;
	// 	return file;
	// }
	
	// /**
	//  * Prints relevant facts from current visit to provide at-a-glance-info
	//  * @param pat
	//  */
	// private static void formattedPrint(Patient pat) {
	// 	System.out.println("\n\n\n");
	// 	System.out.println("Cycle: " + pat.getCycle());
	// 	System.out.println("Week: " + pat.getWeekInCycle());
	// 	Count count = pat.getCount();
	// 	System.out.println("Neutrophil Count: " + count.neutrophil);
	// 	System.out.println("Platelet Count: " + count.platelets);
	// 	System.out.println("\n");
	// }
	
	// private static Dose getDoctorDose() {
	// 	double smp = s.nextDouble();
	// 	double mtx = s.nextDouble();
	// 	return new Dose(mtx, smp);
	// }
}
