/**
 * FileManager.java created by aTeam 147 in Proudly_Cheese project
 * 
 * Author: Hairong Yin (hyin55@wisc.edu) (Lec 002), Haonan Shen (hshen37@wisc.edu) (Lec 001), Xiaoxi
 * Sun (xsun279@wisc.edu) (Lec 002), Zhiwei Song (zsong96@wisc.edu) (Lec 002)
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * FileManager - used to manage to read and export csv files as well as reading records
 * 
 * @author aTeam 147 (2020)
 */
public class FileManager {
  /**
   * This method is used to import the new file, and return a list of records
   * 
   * @param file - input file
   * 
   * @throws IOException              - the exception that may be thrown
   * @throws IllegalRecordException   - when the record format is wrong
   * @throws ParseException           - if the String representing date cannot be interpreted
   * @throws IllegalArgumentException - if there is lack of information in this String
   * @throws NumberFormatException    - if the String representing weight cannot be interpreted as
   *                                  an integer
   */
  public List<Record> importFile(File file) throws IOException, IllegalRecordException,
      NumberFormatException, IllegalArgumentException, ParseException {
    // Check the input parameter
    if (file == null) {
      throw new NullPointerException("File is null");
    }

    // Create a BufferedReader variable
    BufferedReader reader;
    // Initialize it with a FileReader
    reader = new BufferedReader(new FileReader(file));
    // Skip the first line
    reader.readLine();

    // Store the second line
    String line = reader.readLine();
    // Create a list to store record
    List<Record> list = new LinkedList<Record>();

    // when the lines are not all read, continue adding to the list
    while (line != null) {
      list.add(converter(line));
      line = reader.readLine();
    }

    // close the reader
    reader.close();
    return list;
  }

  /**
   * Convert a String to one piece of record. will throw IllegalRecordException if the String cannot
   * be parsed.
   * 
   * @param input - String representing data
   * 
   * @return Record represented by the input String
   * 
   * @throws IllegalRecordException   - if the weight is negative
   * @throws IllegalArgumentException - if there is lack of information in this String
   * @throws NumberFormatException    - if the String representing weight cannot be interpreted as
   *                                  an integer
   * @throws ParseException           - if the String representing date cannot be interpreted
   */
  private Record converter(String input) throws IllegalRecordException, NumberFormatException,
      ParseException, IllegalArgumentException {
    // Split String
    String[] recordInStrings = input.split(",");

    // Check the input, if info is missing, throw IllegalArgumentException
    if (recordInStrings == null || (!(recordInStrings.length == 3))) {
      throw new IllegalArgumentException("Data misses information");
    }

    // Get the integer weight and farmID String
    String farmID = recordInStrings[1];
    int weight = Integer.valueOf(recordInStrings[2]);

    if (weight < 0) {
      // invalid weight
      throw new IllegalRecordException("weight is negative");
    }

    GregorianCalendar cal;
    // Parse String to date
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date date = df.parse(recordInStrings[0]);
    cal = (GregorianCalendar) GregorianCalendar.getInstance();
    cal.setTime(date);

    return new Record(cal, farmID, weight);
  }

  /**
   * Export farm reports to the file system in csv format
   * 
   * @param list - the list of record to be exported
   * @param file - the file to be exported to
   * 
   * @throws FileNotFoundException - if the exported file cannot be found
   */
  public void exportReport(ArrayList<ArrayList<String>> result, File file, int index)
      throws FileNotFoundException {
    // Check the input parameter
    if (file == null) {
      throw new FileNotFoundException("The export file cannot be found");
    }

    // Create a PrintWriter
    PrintWriter writer = new PrintWriter(file);
    // Write the first line to the file
    if (index == 0) {
      writer.println("month,weight,percent");
    } else if (index == 1) {
      writer.println("farmID,weight,percent");
    }
    // Loop through the list
    for (int i = 0; i < result.size(); i++) {
      // Write the records into the file
      writer.print(result.get(i).get(0) + ",");
      writer.print(result.get(i).get(1) + ",");
      writer.println(result.get(i).get(2));
    }
    // close the print writer
    writer.close();
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
   * @throws IllegalArgumentException - if there is lack of information in this String
   * @throws NumberFormatException    - if the String representing weight cannot be interpreted as
   *                                  an integer
   * @throws ParseException           - if the String representing date cannot be interpreted
   */
  public Record inputRecord(String farmID, String date, String weight) throws NumberFormatException,
      IllegalArgumentException, IllegalRecordException, ParseException {
    // Convert the String together
    String input = date + "," + farmID + "," + weight;
    // Run the converter
    Record record = converter(input);
    return record;
  }
}
