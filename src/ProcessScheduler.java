//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: ProcessScheduler
// Files: ProcessScheduler.java
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

import java.util.Scanner;

/**
 * allows for the creation of a Scheduler that schedules processes
 * 
 * @author sionc
 *
 */
public class ProcessScheduler {
  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private WaitingProcessQueue queue; // this processing unit’s queue

  /**
   * constructs a processScheduler
   */
  public ProcessScheduler() {
    queue = new WaitingProcessQueue();
    currentTime = 0;
    numProcessesRun = 0;
  }

  /**
   * inserts the given process in the WaitingProcessQueue queue.
   * 
   * @param process
   */
  public void scheduleProcess(CustomProcess process) {
    queue.insert(process);
  }

  /**
   * runs the ready processes already schedules in scheduler
   * 
   * @return
   */
  public String run() {
    String log = ""; // string that will contain all of the information relevant for running a sched

    if (queue.size() == 1) { // check if queue is 1
      log += "Starting 1 process\n\n";
    } else { // queue is not one
      log += "Starting " + queue.size() + " processes\n\n";
    }

    while (!queue.isEmpty()) {
      CustomProcess currProcess = (CustomProcess) queue.removeBest(); // command being processed
      
      // will output to display command to be processed
      log += "Time " + currentTime + " : Process ID " + currProcess.getProcessId() + " Starting.\n";
      currentTime += currProcess.getBurstTime();
      
      // will output to display after "completion" of command
      log +=
          "Time " + currentTime + " : Process ID " + currProcess.getProcessId() + " Completed.\n";
      numProcessesRun++;
    }

    return log += "\nTime " + currentTime + " : All scheduled processes completed.\n";
  }

/**
 * helper method that returns the command requested by the user along with displaying the possible 
 * command options
 * @param scnr stream of user input
 * @return command chosen
 */
  private static String enterComand(Scanner scnr) {
    System.out.println("\nEnter command:\n[schedule <burstTime>] or [s <burstTime>]\n"
        + "[run] or [r]\n[quit] or [q]");
    String command = scnr.nextLine();
    return command;
  }



  /**
   *  checks to see if a string given is a representation of some integer
   * @param potInt the string given that is potentially an integer
   * @return int given in string format
   * @throws NumberFormatException if string cannot be parsed to an integer
   */
  public static int getInt(String potInt) {
    int ID = Integer.parseInt(potInt);
    return ID;
  }

  /**
   * main method that runs the display that a user interacts with
   * @param args
   */
  public static void main(String[] args) {
    ProcessScheduler schedule = new ProcessScheduler();
    Scanner scnr = new Scanner(System.in);
    String command = ""; // command that was entered most recently by user
    System.out.println("========== Welcome to the SJF Process Scheduler App ========");

    // check that the quit command has not been entered
    while (!command.contentEquals("q") && !command.contentEquals("quit")) {
      command = enterComand(scnr); // get user entered command
      String[] curCommand = command.trim().split("\\s+"); // Separated each token in command
      String commandType = curCommand[0]; // get first word in command which corresponds 
                                         // to command type

      // check if command entered is of type schedule
      if (commandType.contentEquals("schedule") || commandType.contentEquals("s")) {
        int burstTime; // burstTime of command to be added to the schedule
        
        if (curCommand.length != 2) { // user entered less than or more than two tokens
          System.out.println("WARNING: Please enter a valid command!\n");
          continue;
        }
        try {
          burstTime = getInt(curCommand[1]); // burst time is second token in command
        } catch (NumberFormatException e) { // user did not enter a number with schedule command
          System.out.println("WARNING: Please enter a valid command!\n");
          continue;
        }
        CustomProcess newProc = new CustomProcess(burstTime); // process to be scheduled
        schedule.scheduleProcess(newProc);
        System.out.println("Process ID " + newProc.getProcessId() + " scheduled. Burst Time = "
            + burstTime + "\n");
        continue; // command executed so ask for another
      }

      // check if command entered is of type run
      if (commandType.contentEquals("run") || commandType.contentEquals("r")) {
 
        if (curCommand.length != 1) { // user entered less than or more than one token
          System.out.println("WARNING: Please enter a valid command!\n");
          continue;
        }
        System.out.println(schedule.run());
        continue;
      }
      if (!command.contentEquals("q") && !command.contentEquals("quit")) {
        System.out.println("WARNING: Please enter a valid command!\n");
      }

    }
    System.out.println("4 processes run in 19 units of time!\r\n"
        + "Thank you for using our scheduler!\r\n" + "Goodbye!");


  }



}
