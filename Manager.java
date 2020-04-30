/**
 * Manager.java created by aTeam 147 in Proudly_Cheese project
 * 
 * Author: Hairong Yin (hyin55@wisc.edu) (Lec 002), Haonan Shen
 * (hshen37@wisc.edu) (Lec 001), Xiaoxi Sun (xsun279@wisc.edu) (Lec 002), Zhiwei
 * Song (zsong96@wisc.edu) (Lec 002)
 * 
 * Date: 04/19/2020
 * 
 * Course: CS400 Semester: Spring 2020
 * 
 * List Collaborators:None
 * 
 * Other Credits: None
 * 
 * Known Bugs: None
 */

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

/**
 * Manager - manages two objects, one is FarmIDManager, the other is DateManager
 * 
 * @author aTeam 147 (2020)
 */
public class Manager {
	// Manager for Farm by FarmID
	private FarmIDManager farmIDManager;
	// Manager for Farm by date
	private DateManager dateManager;
	// Manager for Input files
	private FileManager fileManager;
	// A final String array to store the month information
	private final String[] DAY = new String[] { "Jan", "Feb", "Mar", "Apr", "May",
			"June", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	// A String array to store the possible user choice when loading
	private final String[] loadFunction = new String[] {
			"Append to the existing data", "Add as a new set of data" };

	/**
	 * Constructor - create the Manager objects
	 */
	public Manager() {
		// Initialize all the instance variables
		this.farmIDManager = new FarmIDManager();
		this.dateManager = new DateManager();
		this.fileManager = new FileManager();
	}

	/**
	 * Retrieve the farm report
	 * 
	 * @param farmID - the ID from input
	 * 
	 * @return List of results
	 * 
	 * @throws IllegalNullKeyException - if key argument is null
	 */
	public ArrayList<ArrayList<String>> getFarmReport(String farmID,
			GregorianCalendar date) throws IllegalNullKeyException {
		// if the date is null
		if (date == null)
			throw new IllegalNullKeyException("null date input");
		// if the farmID is null
		if (farmID == null)
			throw new IllegalNullKeyException("null farmID input");

		// Create a 2D String array to store the result
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		// Initialize a sum to store the total weight for a particular farm in one
		// month
		long sumOfMonth = 0;
		// Sum of Farm
		long sumOfFarm = 0;
		// Maximum Farm
		List<String> max = new ArrayList<String>();
		// Minimum Farm
		List<String> min = new ArrayList<String>();

		// Formating Double values
		DecimalFormat df = new DecimalFormat("#.00");

		// Loop through the 12 months
		for (int i = 0; i < 12; i++) {
			result.add(new ArrayList<String>());
			for (int j = 0; j < 3; j++) {
				result.get(i).add(new String());
			}

			// Initialize the sum
			sumOfMonth = 0;
			// Set the info in the first column
			result.get(i).set(0, DAY[i]);
			// Create a new GregorianCalendar to represent a new month
			GregorianCalendar newMonth = new GregorianCalendar(
					date.get(Calendar.YEAR), 0, 1, 0, 0, 0);
			newMonth.set(Calendar.MONTH, i);
			// Retrieve the total weight
			long weight = dateManager.getFarmMonthlyWeight(farmID, newMonth);
			// Store in the second column
			result.get(i).set(1, Long.toString(weight));

			// Create an arraylist to store all the farm records in one month
			ArrayList<ArrayList<String>> allFarm = getMonthlyReport(newMonth);
			// Retrieve the weight for each farm
			for (ArrayList<String> farm : allFarm) {
				sumOfMonth += Long.parseLong(farm.get(1));
			}

			// Calculate the percent
			if (!(sumOfMonth == 0) && !(Long.parseLong(result.get(i).get(1)) == 0)) {
				result.get(i).set(2,
						df.format(100.0 * Long.parseLong(result.get(i).get(1)) / sumOfMonth)
								+ "%");
			} else {
				result.get(i).set(2, "0.00%");
			}

			// If i = 0, set it to both min and max
			if (i == 0) {
				max.add(DAY[i]);
				max.add(Long.toString(weight));
				max.add(result.get(i).get(2));

				min.add(DAY[i]);
				min.add(Long.toString(weight));
				min.add(result.get(i).get(2));
			} else {
				// Check if it is less than min
				if (weight - Long.parseLong(min.get(1)) < 0) {
					min.set(0, DAY[i]);
					min.set(1, Long.toString(weight));
					min.set(2, result.get(i).get(2));
				}

				// Check if itis larger than max
				if (weight - Long.parseLong(max.get(1)) > 0) {
					max.set(0, DAY[i]);
					max.set(1, Long.toString(weight));
					max.set(2, result.get(i).get(2));
				}
			}
			// Add to the farm total
			sumOfFarm += weight;
		}

		// Add the results to the last line of arraylist
		ArrayList<String> lastLine = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			lastLine.add(max.get(i));
		}

		for (int i = 0; i < 3; i++) {
			lastLine.add(min.get(i));
		}

		lastLine.add(df.format(1.0 * sumOfFarm / 12));

		// Add the arraylist to the result
		result.add(lastLine);
		return result;
	}

	/**
	 * Retrieve the monthly report
	 * 
	 * @param date - the input date
	 * 
	 * @return List of results
	 * 
	 * @throws IllegalNullKeyException - if key argument is null
	 */
	public ArrayList<ArrayList<String>> getMonthlyReport(GregorianCalendar date)
			throws IllegalNullKeyException {
		// Retrieve the record list
		List<Record> records = dateManager.getMonthlyReport(date);
		// Create a Hash table to store records
		Hashtable<String, Long> monthlyRecords = new Hashtable<String, Long>();
		// Initialize a sum to store the total weight for a particular farm in one
		// month
		long sumOfWeights = 0;

		// creates local variable to keep tracking min and max
		ArrayList<String> max = new ArrayList<String>();
		ArrayList<String> min = new ArrayList<String>();
		long minWeight = Long.MAX_VALUE;
		long maxWeight = 0;

		// Traverse all of the records to and their total weight
		for (Record r : records) {
			sumOfWeights += r.getWeight();
			if (monthlyRecords.containsKey(r.getFarmID()))
				monthlyRecords.put(r.getFarmID(),
						monthlyRecords.get(r.getFarmID()) + r.getWeight());
			else
				monthlyRecords.put(r.getFarmID(), Long.valueOf(r.getWeight()));

			if (r.getWeight() > maxWeight) {
				max.set(0, r.getFarmID());
				maxWeight = r.getWeight();
				max.set(1, Long.toString(maxWeight));
			}
			if (r.getWeight() < minWeight) {
				min.set(0, r.getFarmID());
				minWeight = r.getWeight();
				min.set(1, Long.toString(minWeight));
			}
		}

		// An ArrayList to store the result
		ArrayList<ArrayList<String>> reportOfMonth = new ArrayList<ArrayList<String>>();

		// Formating Double values
		DecimalFormat df = new DecimalFormat("#.00");

		// Get statistics of monthly report
		for (String s : monthlyRecords.keySet()) {
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add(s);
			tempList.add(monthlyRecords.get(s).toString());
			if (!(sumOfWeights == 0) && !(monthlyRecords.get(s) == 0)) {
				tempList.add(
						df.format((100.0 * monthlyRecords.get(s) / sumOfWeights)) + "%");
			} else {
				tempList.add("0.00%");
			}
			reportOfMonth.add(tempList);
		}

		max.set(2, df.format((100.0 * maxWeight / sumOfWeights)) + "%");
		min.set(2, df.format((100.0 * minWeight / sumOfWeights)) + "%");

		// Sort the ArrayList
		Collections.sort(reportOfMonth, (a, b) -> {
			return a.get(0).compareTo(b.get(0));
		});

		// append summary info to the report
		ArrayList<String> summary = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			summary.add(max.get(i));
		}

		for (int i = 0; i < 3; i++) {
			summary.add(min.get(i));
		}

		reportOfMonth.add(summary);

		return reportOfMonth;
	}

	/**
	 * Retrieve the annual report
	 * 
	 * @param date - the input date
	 * 
	 * @return List of results
	 * 
	 * @throws IllegalNullKeyException - if key argument is null
	 */
	public ArrayList<ArrayList<String>> getAnnualReport(GregorianCalendar date)
			throws IllegalNullKeyException {
		// Retrieve the record list
		List<Record> records = dateManager.getAnnualReport(date);
		// Create a Hash table to store records
		Hashtable<String, Long> annualRecords = new Hashtable<String, Long>();
		// Initialize a sum to store the total weight for a particular farm in one
		// year
		long sumOfWeights = 0;

		// creates local variable to keep tracking min and max
		ArrayList<String> max = new ArrayList<String>();
		ArrayList<String> min = new ArrayList<String>();
		long minWeight = Long.MAX_VALUE;
		long maxWeight = 0;

		// Traverse all of the records to get all FarmID and their total weight
		for (Record r : records) {
			sumOfWeights += r.getWeight();
			if (annualRecords.containsKey(r.getFarmID()))
				annualRecords.put(r.getFarmID(),
						annualRecords.get(r.getFarmID()) + r.getWeight());
			else
				annualRecords.put(r.getFarmID(), Long.valueOf(r.getWeight()));
			if (r.getWeight() > maxWeight) {
				max.set(0, r.getFarmID());
				maxWeight = r.getWeight();
				max.set(1, Long.toString(maxWeight));
			}
			if (r.getWeight() < minWeight) {
				min.set(0, r.getFarmID());
				minWeight = r.getWeight();
				min.set(1, Long.toString(minWeight));
			}

		}

		// An ArrayList to store the result
		ArrayList<ArrayList<String>> reportOfYear = new ArrayList<ArrayList<String>>();

		// Formating Double values
		DecimalFormat df = new DecimalFormat("#.00");

		// Get statistics of yearly report
		for (String s : annualRecords.keySet()) {
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add(s);
			tempList.add(annualRecords.get(s).toString());
			if (!(sumOfWeights == 0) && !(annualRecords.get(s) == 0)) {
				tempList.add(
						df.format((100.0 * annualRecords.get(s) / sumOfWeights)) + "%");
			} else {
				tempList.add("0.00%");
			}
			reportOfYear.add(tempList);
		}

		max.set(2, df.format((100.0 * maxWeight / sumOfWeights)) + "%");
		min.set(2, df.format((100.0 * minWeight / sumOfWeights)) + "%");

		// Sort the ArrayList
		Collections.sort(reportOfYear, (a, b) -> {
			return a.get(0).compareTo(b.get(0));
		});

		// append summary info to the report
		ArrayList<String> summary = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			summary.add(max.get(i));
		}

		for (int i = 0; i < 3; i++) {
			summary.add(min.get(i));
		}

		reportOfYear.add(summary);

		return reportOfYear;
	}

	/**
	 * Retrieve the date range report
	 * 
	 * @param date - the input date
	 * 
	 * @return List of results
	 * 
	 * @throws IllegalNullKeyException - if key argument is null
	 */
	public ArrayList<ArrayList<String>> getDateReport(GregorianCalendar start,
			GregorianCalendar end) throws IllegalNullKeyException {
		// Retrieve the record list
		List<Record> records = dateManager.getDateRangeReport(start, end);
		// Create a Hash table to store records
		Hashtable<String, Long> rangeRecords = new Hashtable<String, Long>();
		// Initialize a sum to store the total weight for a particular farm in date
		// range
		long sumOfWeights = 0;

		// creates local variable to keep tracking min and max
		ArrayList<String> max = new ArrayList<String>();
		ArrayList<String> min = new ArrayList<String>();
		long minWeight = Long.MAX_VALUE;
		long maxWeight = 0;

		// Traverse all of the records to get all FarmID and their total weight
		for (Record r : records) {
			sumOfWeights += r.getWeight();
			if (rangeRecords.containsKey(r.getFarmID()))
				rangeRecords.put(r.getFarmID(),
						rangeRecords.get(r.getFarmID()) + r.getWeight());
			else
				rangeRecords.put(r.getFarmID(), Long.valueOf(r.getWeight()));
			if (r.getWeight() > maxWeight) {
				max.set(0, r.getFarmID());
				maxWeight = r.getWeight();
				max.set(1, Long.toString(maxWeight));
			}
			if (r.getWeight() < minWeight) {
				min.set(0, r.getFarmID());
				minWeight = r.getWeight();
				min.set(1, Long.toString(minWeight));
			}
		}

		// An ArrayList to store the result
		ArrayList<ArrayList<String>> reportOfRange = new ArrayList<ArrayList<String>>();

		// Formating Double values
		DecimalFormat df = new DecimalFormat("#.00");

		// Get statistics of date range report
		for (String s : rangeRecords.keySet()) {
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add(s);
			tempList.add(rangeRecords.get(s).toString());
			if (!(sumOfWeights == 0) && !(rangeRecords.get(s) == 0)) {
				tempList
						.add(df.format((100.0 * rangeRecords.get(s) / sumOfWeights)) + "%");
			} else {
				tempList.add("0.00%");
			}
			reportOfRange.add(tempList);
		}

		max.set(2, df.format((100.0 * maxWeight / sumOfWeights)) + "%");
		min.set(2, df.format((100.0 * minWeight / sumOfWeights)) + "%");

		// Sort the ArrayList
		Collections.sort(reportOfRange, (a, b) -> {
			return a.get(0).compareTo(b.get(0));
		});
		
		// append summary info to the report
		ArrayList<String> summary = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			summary.add(max.get(i));
		}

		for (int i = 0; i < 3; i++) {
			summary.add(min.get(i));
		}

		reportOfRange.add(summary);

		return reportOfRange;
	}

	/**
	 * Retrieve the number of records
	 * 
	 * @return the number of records
	 */
	public int getNumberOfRecords() {
		// Check whether the record is consistent
		if (farmIDManager.getTotalNumberOfRecords() == dateManager
				.getTotalNumberOfRecords()) {
			return farmIDManager.getTotalNumberOfRecords();
		}
		return -1;
	}

	/**
	 * Retrieve the number of farms
	 * 
	 * @return the number of farms
	 */
	public int getNumberOfFarms() {
		return farmIDManager.getNumberOfFarms();
	}

	/**
	 * Add records to the system
	 * 
	 * @param r - the record to be added
	 * 
	 * @throws IllegalNullKeyException - if key argument is null
	 * @throws DuplicateKeyException   - if the key is duplicated
	 */
	public void addRecords(Record r)
			throws IllegalNullKeyException, DuplicateKeyException {
		farmIDManager.addFarmRecord(r);
		dateManager.addFarmRecord(r);
	}

	/**
	 * Remove records to the system
	 * 
	 * @param r - the record to be removed
	 * 
	 * @return whether the record is successfully removed or not
	 * 
	 * @throws IllegalNullKeyException - if key argument is null
	 */
	public boolean removeRecords(Record r) throws IllegalNullKeyException {
		// Store the results of removal
		boolean result1 = farmIDManager.removeFarmRecord(r);
		boolean result2 = dateManager.removeFarmRecord(r);
		// Check the two results
		if (result1 && result2) {
			return true;
		}
		return false;
	}

	/**
	 * Change records to the system
	 * 
	 * @param oldRecord - the record to be removed
	 * @param newRecord - the record to be added
	 * 
	 * @return whether the record is successfully changed or not
	 * 
	 * @throws IllegalNullKeyException - if key argument is null
	 * @throws DuplicateKeyException   - if the key is duplicated
	 */
	public boolean changeRecords(Record oldRecord, Record newRecord)
			throws IllegalNullKeyException, DuplicateKeyException {
		if (oldRecord == null || newRecord == null) {
			throw new IllegalNullKeyException("The input is null");
		}

		if (oldRecord.compareTo(newRecord) == 0) // Record is the same
			return true;

		// get the result of the change operation
		boolean result1 = farmIDManager.changeFarmRecord(oldRecord, newRecord);
		boolean result2 = dateManager.changeFarmRecord(oldRecord, newRecord);
		// Check the two results
		if (result1 && result2) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to add records to storage after loading from file
	 * 
	 * @param recordList - list of records read from the file
	 * @param choice     - the user choice when loading data
	 * 
	 * @throws IllegalNullKeyException - if key argument is null
	 * @throws DuplicateKeyException   - if the key is duplicated
	 */
	public void importList(List<Record> recordList, String choice)
			throws IllegalNullKeyException, DuplicateKeyException {
		// Check the input parameter
		if (recordList == null || choice == null) {
			return;
		}

		// Check the user choice
		if (choice.equals(this.loadFunction[1])) {
			// Reset the Manager
			this.farmIDManager = new FarmIDManager();
			this.dateManager = new DateManager();
		}

		// add each of record in the list to storage
		for (Record record : recordList) {
			addRecords(record);
		}
	}

	/**
	 * This method is used to import the new file, and return a list of records
	 * 
	 * @param file - input file
	 * 
	 * @throws IOException              - the exception that may be thrown
	 * @throws IllegalRecordException   - when the record format is wrong
	 * @throws ParseException           - if the String representing date cannot
	 *                                  be interpreted
	 * @throws IllegalArgumentException - if there is lack of information in this
	 *                                  String
	 * @throws NumberFormatException    - if the String representing weight cannot
	 *                                  be interpreted as an integer
	 */
	public List<Record> importFile(File file)
			throws NumberFormatException, IllegalArgumentException, IOException,
			IllegalRecordException, ParseException {
		return this.fileManager.importFile(file);
	}

	/**
	 * Export different reports to the file system in csv format
	 * 
	 * @param list - the list of record to be exported
	 * @param file - the file to be exported to
	 * 
	 * @throws FileNotFoundException - if the exported file cannot be found
	 */
	public void exportReport(ArrayList<ArrayList<String>> result, File file,
			int index) throws FileNotFoundException {
		this.fileManager.exportReport(result, file, index);
	}

	/**
	 * Input record to from the user input
	 * 
	 * @param farmID - the farmID input
	 * @param date   - the date input
	 * @param weight - the weight input
	 * 
	 * @return the record if successful
	 * 
	 * @throws IllegalRecordException   - if the weight is negative
	 * @throws IllegalArgumentException - if there is lack of information in this
	 *                                  String
	 * @throws NumberFormatException    - if the String representing weight cannot
	 *                                  be interpreted as an integer
	 * @throws ParseException           - if the String representing date cannot
	 *                                  be interpreted
	 */
	public Record inputRecord(String farmID, String date, String weight)
			throws NumberFormatException, IllegalArgumentException,
			IllegalRecordException, ParseException {
		return this.fileManager.inputRecord(farmID, date, weight);
	}

}
