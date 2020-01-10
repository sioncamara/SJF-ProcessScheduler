//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: WaitingProcessQueue
// Files: WaitingProcessQueue.java
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
 * allows for the creation of a priority queue that prioritizes the time of it takes to process a
 * command.
 * 
 * @author sionc
 *
 */
public class WaitingProcessQueue implements WaitingQueueADT {

  private static final int INITIAL_CAPACITY = 20; // the initial capacity of this
                                                  // waiting process queue
  private CustomProcess[] data; // min heap-array storing the CustomProcesses
                                // inserted in this WaitingProcessQueue.
                                // data is an oversize array
  private int size; // number of CustomProcesses stored in this WaitingProcessQueue

  /**
   * constructs a WaitingProcessQueue with a data array with initial_capacity
   */
  public WaitingProcessQueue() {
    data = new CustomProcess[INITIAL_CAPACITY];
  }

  @Override
  /**
   * inserts a newObject in this waiting queue.
   * 
   * @param newObject to insert in this waiting queue
   */
  public void insert(Comparable newObject) {
    
    if(data.length == size) { // check if heap need to be expanded
      data = doubleStorage(data); // expand
    }
    data[size] = (CustomProcess) newObject; // insert new process into first open position in queue
    minHeapPercolateUp(size); // compare object added to its parents and swap when necessary
    size++;
  }
  
  /**
   * Doubles the amount of data the data array can hold
   * @param oldData data two be copied
   * @return array with the same size with double the storage
   */
  private CustomProcess[] doubleStorage(CustomProcess[] oldData) {
    CustomProcess[] newData = new CustomProcess[size * 2]; // array twice the size of given
    for(int i = 0; i < size; i ++) { // copy each element into new array
      newData[i] = oldData[i];
    }
    return newData;
  }

  @Override
  /**
   * removes and returns the element with the highest priority.
   * 
   * @return the removed element
   * @throws java.util.NoSuchElementException with a descriptive error message if this waiting queue
   *                                          is empty
   */
  public Comparable removeBest() {
    if (isEmpty()) {
      throw new NoSuchElementException("There are no more processes available in the heap");
    }

    CustomProcess removed = data[0];
    data[0] = data[size - 1]; // replaced highest priority process with lowest
    data[size - 1] = null;
    size--;
    minHeapPercolateDown(0); // check new highest priority process with children and swap when
                             // when appropriate

    return removed;
  }

  @Override
  /**
   * returns without removing the element with the highest priority.
   * 
   * @return the element with the highest priority
   * @throws java.util.NoSuchElementException with a descriptive error message if this waiting queue
   *                                          is empty
   */
  public Comparable peekBest() {
    if (isEmpty()) {
      throw new NoSuchElementException("There are no more processes available in the heap");
    }
    // there is at least one process within que return highest priority one
    return data[0];
  }

  @Override
  /**
   * returns size of priority queue
   * 
   * @return the size of priority queue
   */
  public int size() {
    return size;
  }

  @Override
  /**
   * checks whether this waiting queue is empty or not.
   * 
   * @return true if this waiting queue is empty, false otherwise
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  private void minHeapPercolateUp(int index) {
    int pI = (index - 1) / 2; // index of parent process
    
    // check if child has higher priority
    if (data[index].compareTo(data[pI]) < 0 && data[index] != data[pI]) {
      
      // swap
      CustomProcess temp = data[pI];
      data[pI] = data[index];
      data[index] = temp;
      
      minHeapPercolateUp(pI);// check if current process should be swapped with parent
    }
  }

  private void minHeapPercolateDown(int index) {
    while (hasChild(index)) { // check if given node is a leaf
      CustomProcess lC = data[2 * index + 1]; // left child
      CustomProcess rC = null;
      CustomProcess hPC; // highest priority child
      int hPCIndex; // highest priority child's index

      int cCompVal = 0; // comparison value of children
      if (hasRTChild(index)) {
        rC = data[2 * index + 2]; // right child
        cCompVal = lC.compareTo(rC); // negative if left has a higher priority
      }

      if (cCompVal != 0) { // parent has 2 children
        if (cCompVal < 0) {
          hPC = lC; // left child has greater priority
          hPCIndex = 2 * index + 1;
        } else {
          hPC = rC; // right child has greater priority
          hPCIndex = 2 * index + 2;
        }
      } else { // there is no right child
        hPC = lC;
        hPCIndex = 2 * index + 1;
      }

      if (hPC.compareTo(data[index]) < 0) {
        // left child has greater priority than parent so swap
        CustomProcess temp = data[index];
        data[index] = hPC;
        data[hPCIndex] = temp;
        index = hPCIndex;
      } else { // highest priority child has lower priority exit loop
        break;
      }
    }
  }

  /**
   * help method to check if a  node has a child
   * @param index position of node
   * @return true of node has child
   */
  private Boolean hasChild(int index) {

    CustomProcess lC = data[2 * index + 1]; // left child

    if (lC == null) { // check if left child exist
      return false;
    }
    return true; // left child exist so is not a leaf
  }

  /**
   * check if node has a right child
   * @param index position of node
   * @return true if node has a right child
   */
  private Boolean hasRTChild(int index) {

    CustomProcess rC = data[2 * index + 2]; // right child
    if (rC == null) { // child does not exist
      return false;
    }
    return true; // right child exist
  }

  @Override
  /**
   * returns a String representation of all the non-null elements (custom processes) stored within
   * data array
   */
  public String toString() {

    String allData = ""; // string that will return the data within the queue
    if (isEmpty()) {
      return " ";
    } else {

      for (CustomProcess cP : data) { // add each custom process to string
        if (cP != null)
          allData += cP.toString() + System.lineSeparator();
      }
    }

    return allData;
  }

  /**
   * main method to self test queue
   * @param args not used
   */
  public static void main(String[] args) {

    // test below
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
    String eResult = test.toString();
    System.out.println(eResult);

   



  }


}
