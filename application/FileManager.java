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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

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
    BufferedReader reader;
    reader = new BufferedReader(new FileReader(file));
    String line = reader.readLine();
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
    // Get the integer weight and farmID String
    String farmID = recordInStrings[1];
    int weight = Integer.valueOf(recordInStrings[2]);

    if (weight < 0) {
      // invalid weight
      throw new IllegalRecordException("weight is negative");
    }

    if (recordInStrings.length != 3) { // Detect invalid length
      throw new IllegalArgumentException("Data misses information");
    }

    GregorianCalendar cal;
    // Parse String to date
    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
    Date date = df.parse(recordInStrings[0]);
    cal = (GregorianCalendar) GregorianCalendar.getInstance();
    cal.setTime(date);

    return new Record(cal, farmID, weight);
  }

  /**
   * Export different reports to the file system in csv format
   * 
   * @param list - the list of record to be exported
   * @param file - the file to be exported to
   * 
   * @throws FileNotFoundException - if the exported file cannot be found
   */
  public void exportFile(List<Record> list, File file) throws FileNotFoundException {
    // Create a PrintWriter
    PrintWriter writer = new PrintWriter(file);
    // Write the first line to the file
    writer.println("date,farm_id,weight");
    // Loop through the list
    for (Record record : list) {
      // Write the records into the file
      writer.print(record.getDate().get(Calendar.YEAR) + "-");
      writer.print(record.getDate().get(Calendar.MONTH) + "-");
      writer.print(record.getDate().get(Calendar.DATE) + ",");
      writer.print(record.getFarmID() + ",");
      writer.println(record.getWeight());
    }
    // close the print writer
    writer.close();
  }
}
