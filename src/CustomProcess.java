//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: CustomProcess
// Files: CustomProcess.java
// Semester: Fall 2019
//
// Author: Sion Wilks
// Email: sion@cs.wisc.edu
// CS Login: sion
// Lecturer's Name: Garry
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * allows for the creation of an object that stores the details of a processor procedure
 * @author sionc
 *
 */

public class CustomProcess implements java.lang.Comparable<CustomProcess> {

  private static int nextProcessId = 1; // stores the id to be assigned to the next process
                                       // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution

  /**
   * constructor of custom process given a intiial burst time
   * @param burstTime // time required by the process for CPU execution
   * @throws IllegalArgumentException thrown if burstTime is not positive
   */
  public CustomProcess(int burstTime) throws IllegalArgumentException{ 
    if(burstTime <= 0) {
      throw new IllegalArgumentException("provided burstTime is not positive");
      
    }
    this.burstTime = burstTime; 
    PROCESS_ID = nextProcessId;
    nextProcessId++;
  }
  
  /**
   * returns custom process ID
   * @return Process ID
   */
  public int getProcessId() {
    return PROCESS_ID;
  }
  
  /**
   * returns custom process burst time
   * @return burstTime
   */
  public int getBurstTime() {
    return burstTime;
  }
  
  /**
  * Returns a String representation of this CustomProcess
  * @return a string representation of this CustomProcess
  */
  public String toString() {
  return "p" + this.PROCESS_ID + "(" + this.burstTime + ")";
  } 
  
  
  
  
  @Override
  /**
   * Compares the burst times of two different custom processes. Returning < 0 means the object 
   * being compared to the other is smaller. > 0 The opposite is true and 0 means they are the same
   * @returns the comparison value
   */
  public int compareTo(CustomProcess o) {
    Integer p1 = this.burstTime; // burst time of process being compared
    Integer p2 = o.burstTime; // burst time of process that p1 is compared to
    if(p1.compareTo(p2) == 0) { // check if times are the same
      if(this.PROCESS_ID < o.getProcessId()) { // check this time is smaller
        return -1;
      } else if(this.PROCESS_ID > o.getProcessId()) {
        return 1; // this ID is bigger
      } else
        return 0; // ID's are the same
    }
    
    return p1.compareTo(p2);
  }

}
