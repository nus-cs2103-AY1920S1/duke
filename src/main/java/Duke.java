import org.w3c.dom.html.HTMLImageElement;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private Storage storage;
    //private TaskList tasks;
    private Ui ui;

    /////////////////////////// Date & Time Converter: returns the converted format as a string //////////////////////////
    static String Convert(String timeframe){

          int in = timeframe.indexOf('/');                           //find first instance of '/'
          int day = Integer.parseInt(timeframe.substring(0, in));    //sift out day
          String sub1 = timeframe.substring(in+1);                   //substring: month onwards
          int in2 = sub1.indexOf('/');
          int month =  Integer.parseInt(sub1.substring(0, in2));     //sift out month
          String sub2 = sub1.substring(in2+1);                       //substring: year onwards
          String year = sub2.substring(0, 4);                        //sift out year
          String time = sub2.substring(5);                           //sift out time (24hr clock format)

          String converted_day;
          switch (day){
              case 1:
              case 21:
              case 31:
                  converted_day = Integer.toString(day) + "st";
                  break;
              case 2:
              case 22:
                  converted_day = Integer.toString(day) + "nd";
                  break;
              case 3:
              case 33:
                  converted_day = Integer.toString(day) + "rd";
                  break;
              default:
                  converted_day = Integer.toString(day) + "th";
                  break;
          }

          String converted_month;
          switch (month){
              case 1:
                  converted_month = "January"; break;
              case 2:
                  converted_month = "February"; break;
              case 3:
                  converted_month = "March"; break;
              case 4:
                  converted_month = "April"; break;
              case 5:
                  converted_month = "May"; break;
              case 6:
                  converted_month = "June"; break;
              case 7:
                  converted_month = "July"; break;
              case 8:
                  converted_month = "August"; break;
              case 9:
                  converted_month = "September"; break;
              case 10:
                  converted_month = "October"; break;
              case 11:
                  converted_month = "November"; break;
              case 12:
                  converted_month = "December"; break;
              default:
                  converted_month = ""; break;
          }

          String period;                                 //indicate AM or PM
          int hour = Integer.parseInt(time.substring(0,2));
          if(hour>=12)
              period = "pm";
          else
              period = "am";

        String converted_hr;
        if((Integer.parseInt(time.substring(0,2))) > 12) {
              converted_hr = Integer.toString((Integer.parseInt(time.substring(0, 2))) - 12);
        }
        else{
            converted_hr = Integer.toString((Integer.parseInt(time.substring(0, 2))));
        }

          String converted_min;
          int min = Integer.parseInt(time.substring(2));
          if(min>0)
              converted_min = ":" + time.substring(2);
          else
              converted_min = "";

          return converted_day + " of " + converted_month + " " + year + ", " + converted_hr + converted_min + period ;
    }

    /////////////////////////////// End of DATE & TIME Converter ///////////////////////////////////////////////////


    /////////////////////////////// Duke constructor  ///////////////////////////////////////////////////
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage("D:\\madae\\School\\cs2103T\\IdeaProjects\\DUKE\\DukesDiary.txt");
        /*try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
    }
    /////////////////////////////// End of Duke constructor //////////////////////////////////////////


    //////////////////////////////////// start of run //////////////////////////////////////////////
    public void run() throws IOException {
        String userInput;
        int no_of_task;
        ArrayList<Task> taskList = new ArrayList<Task>();

        taskList = storage.load();            //load file onto arraylist
        no_of_task = storage.get_no_task();   //get number of tasks

        while(true) {
            userInput = ui.read();                 //read user input
            //////////////////////////
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again.");
                break;
            }


            if (userInput.contains("todo")) {
                no_of_task++;
                String sub = userInput.substring(5);        //get task description
                if (sub.isEmpty()) {
                    System.out.println("OOPS!! The description of a todo cannot be empty.");
                } else {
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [ ][ ]" + sub);
                    System.out.println("Now you have " + no_of_task + " tasks in the list.");
                    Task t = new Task(sub, 'T', 0, "");
                    taskList.add(t);
                    storage.AutoSave(taskList, no_of_task);
                }
            } else {

                if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i < no_of_task; i++) {
                        if((taskList.get(i).timeframe).equals("")){
                            System.out.println((i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description);
                        }
                        else
                            System.out.println((i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " (" + taskList.get(i).timeframe + ")");
                    }
                } else {
                    if (userInput.contains("done")) {
                        String taskNumber = userInput.substring(5);

                        //iterate through the tasks Arraylist until task is found
                        for (int i = 1; i <= taskList.size(); i++) {
                            if (i == (Integer.parseInt(taskNumber))) {
                                System.out.println("Nice! I've marked this task as done: ");
                                System.out.println("  [" + "\u2713" + "] " + taskList.get(i - 1).description);
                                taskList.get(i - 1).changeStatus(1);
                                System.out.println("New status: " + taskList.get(i - 1).status);
                                Storage.AutoSave(taskList, no_of_task);
                            }
                        }
                    } else {
                        if (userInput.contains("deadline")) {
                            int index = 0;
                            index = userInput.indexOf('/'); //iterate through the input to find the '/' char

                            String timeFrame = userInput.substring(index + 1);
                            String temp = timeFrame.substring(3);
                            String sub = userInput.substring(9, index - 1);

                            String new_timeFrame = Convert(temp);
                            Task t = new Task(sub, 'D', 0, new_timeFrame);
                            taskList.add(t);
                            no_of_task++;
                            storage.AutoSave(taskList, no_of_task);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
                            System.out.println("Now you have " + no_of_task + " tasks in the list.");
                        } else {
                            if (userInput.contains("event")) {
                                int index = 0;
                                index = userInput.indexOf('/');   //iterate through the input to find the '/' char

                                String timeFrame = userInput.substring(index + 1);
                                String temp = timeFrame.substring(3);
                                String sub = userInput.substring(6, index - 1);

                                String new_timeFrame = Convert(temp);
                                Task t = new Task(sub, 'E', 0, new_timeFrame);
                                taskList.add(t);
                                no_of_task++;
                                storage.AutoSave(taskList, no_of_task);
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
                                System.out.println("Now you have " + no_of_task + " tasks in the list.");
                            } else {
                                if (userInput.contains("delete")) {
                                    int index = Integer.parseInt(userInput.substring(7));  //task to be deleted
                                    Task t = taskList.get(index - 1);
                                    no_of_task--;

                                    ui.print_delete("Delete", no_of_task, t);            //print statements
                                    taskList.remove(index - 1);
                                    storage.AutoSave(taskList, no_of_task);

                                } else {
                                    if (userInput.contains("find")){
                                        int num=0;                                       //position of tasks, to be printed
                                        String keyword = userInput.substring(5);         //keyword to be found
                                        ui.print_find(num, taskList, 0, 1);
                                        //System.out.println("Here are the matching tasks in your list: ");

                                        //Iterate the Task ArrayList to get the tasks
                                        for (int i = 0; i < taskList.size(); i++){
                                            if((taskList.get(i).description).contains(keyword)) {    //if task description contains the keyword
                                                num++ ;
                                                ui.print_find(num, taskList, i, 2);
                                                //System.out.println(num + ".[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " " + taskList.get(i).timeframe);
                                            }
                                        }
                                    }
                                    else
                                        System.out.println("OOPS!! I'm sorry, but I don't know what that means.");
                                }
                            }
                        }
                    }
                }
            }
            ////////////////////end of event handling ///////////////////////////////

        }
        //////////////////////// end of while(true)  ////////////////////////////////
    }
    //////////////////////////// end of run() method   //////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?\n");

        new Duke("D:\\madae\\School\\cs2103T\\IdeaProjects\\DUKE\\DukesDiary.txt").run();
    }

}


