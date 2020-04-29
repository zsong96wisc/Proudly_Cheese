package application;

import javafx.beans.property.SimpleStringProperty; 

public class ResultRecord {
  private final SimpleStringProperty columnOne;
  private final SimpleStringProperty columnTwo;
  private final SimpleStringProperty columnThree;

  public ResultRecord(String columnOne, String columnTwo, String columnThree) {
    this.columnOne = new SimpleStringProperty(columnOne);
    this.columnTwo = new SimpleStringProperty(columnTwo);
    this.columnThree = new SimpleStringProperty(columnThree);
  }

  public String getColumnOne() {
    return columnOne.get();
  }

  public String getColumnTwo() {
    return columnTwo.get();
  }

  public String getColumnThree() {
    return columnThree.get();
  }

  public void setColumnOne(String columnOne) {
    this.columnOne.set(columnOne);
  }

  public void setColumnTwo(String columnTwo) {
    this.columnTwo.set(columnTwo);
  }

  public void setColumnThree(String columnThree) {
    this.columnThree.set(columnThree);
  }
}
