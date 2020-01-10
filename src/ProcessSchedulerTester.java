//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: ProcessSchedulerTester
// Files: ProcessSchedulerTester.java
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

import java.util.NoSuchElementException;

/**
 * public class that test various functions of the objects used to create a processScheduler
 * 
 * @author sionc
 *
 */
public class ProcessSchedulerTester {

/**
 * checks the correctness of the insert operation
 * implemented in the WaitingProcessQueue class
 * @return true if implementation is correct
 */
public static boolean testInsertWaitingProcessQueue() {
  
  // test variables
  WaitingProcessQueue test = new WaitingProcessQueue();
  CustomProcess cP1 = new CustomProcess(1);
  CustomProcess cP2 = new CustomProcess(2);
  CustomProcess cP3 = new CustomProcess(3);
  CustomProcess cP4 = new CustomProcess(4);
  CustomProcess cP5 = new CustomProcess(5);
  CustomProcess cP6 = new CustomProcess(6);
  CustomProcess cP7 = new CustomProcess(7);
  CustomProcess cP8 = new CustomProcess(8);
  CustomProcess cP9 = new CustomProcess(9);
  CustomProcess cP10 = new CustomProcess(10);
  
  test.insert(cP8);
  test.insert(cP10);
  test.insert(cP5);
  test.insert(cP7);
  test.insert(cP6);
  test.insert(cP2);
  test.insert(cP6);
  test.insert(cP1);

  CustomProcess expect1 = cP1; // expected result for first test
  CustomProcess expect2 = cP2; // expected result for second test

  if(test.removeBest() != cP1) { // first element in scheduler should be cP1
    return false;
  }
  
  if(test.peekBest() != cP2) { // first element in now modified queue should be cP2
    return false;
  }
  return true;
}

/**
 * checks the correctness of the removeBest operation
 * implemented in the WaitingProcessQueue class
 * @return true if implementation is correct
 */
public static boolean testRemoveBestWaitingProcessQueue() {
  
  // test values 
  WaitingProcessQueue test = new WaitingProcessQueue();
  CustomProcess cP1 = new CustomProcess(1);
  CustomProcess cP2 = new CustomProcess(2);
  CustomProcess cP3 = new CustomProcess(3);
  CustomProcess cP4 = new CustomProcess(4);
  CustomProcess cP5 = new CustomProcess(5);
  CustomProcess cP6 = new CustomProcess(6);
  CustomProcess cP7 = new CustomProcess(7);
  CustomProcess cP8 = new CustomProcess(8);
  CustomProcess cP9 = new CustomProcess(9);
  CustomProcess cP10 = new CustomProcess(10);
  
  test.insert(cP8);
  test.insert(cP10);
  test.insert(cP5);
  
  test.removeBest();
  if(test.size() != 2) { // size should be 2 after one item removed
    return false;
  }
  
  test.removeBest();
  if(test.size() != 1) { // size should be 1 after two item removed
    return false;
  }
  
  test.removeBest();
  if(test.size() != 0) { // size should be 0 after all items removed
    return false;
  }
  
  try {
    test.removeBest();
  } catch(NoSuchElementException e ) { // check no such element is thrown when removing  trying to  
                                      // remove an element from a empty queue
    return true;
  }
  return false;
}

/**
 * checks the correctness of the peekBest operation
 * implemented in the WaitingProcessQueue class
 * @return true if implementation is correct
 */
public static boolean testPeekBestWaitingProcessQueue() {
  
  // test values
  WaitingProcessQueue test = new WaitingProcessQueue();
  CustomProcess cP1 = new CustomProcess(1);
  CustomProcess cP2 = new CustomProcess(2);
  CustomProcess cP3 = new CustomProcess(3);
  CustomProcess cP4 = new CustomProcess(4);
  CustomProcess cP5 = new CustomProcess(5);
  CustomProcess cP6 = new CustomProcess(6);
  CustomProcess cP7 = new CustomProcess(7);
  CustomProcess cP8 = new CustomProcess(8);
  CustomProcess cP9 = new CustomProcess(9);
  CustomProcess cP10 = new CustomProcess(10);
  
  test.insert(cP8);
  test.insert(cP10);
  test.insert(cP5);
  
  //check that the item peeked is the highest priority item along with that the toString method 
 // Functions correctly
  if(!test.peekBest().toString().contentEquals(cP5.toString())) {  
    return false;
  }
  return true;
}

/**
 * checks the correctness of the isEmpty operation
 * implemented in the WaitingProcessQueue class
 * @return
 */
public static boolean testIsEmptyWaitingProcessQueue() {
  WaitingProcessQueue test = new WaitingProcessQueue(); // create empty queue
  if(!test.isEmpty()) { // check that list is in fact emtpy
    return false;
  }
  return true;
  
}

/**
 * main method runs all tests and print boolean values that represent the test passing or failing
 * @param args not used
 */
public static void main(String[] args) {
  
  // tests
  System.out.println(testInsertWaitingProcessQueue());
  System.out.println(testRemoveBestWaitingProcessQueue());
  System.out.println(testPeekBestWaitingProcessQueue());
  System.out.println(testIsEmptyWaitingProcessQueue());
}

}
