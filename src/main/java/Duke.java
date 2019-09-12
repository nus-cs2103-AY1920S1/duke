import org.w3c.dom.html.HTMLImageElement;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class Duke{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    //private TaskList tasks;
    private Ui ui;

    /////////////////////////////// Duke constructors  ///////////////////////////////////////////////////
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

    }
    public Duke() {
        this("src/main/java/Duke.txt");
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

                            ConvertDateTime new_timeFrame = new ConvertDateTime(temp);
                            Task t = new Task(sub, 'D', 0, new_timeFrame.str);
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

                                ConvertDateTime new_timeFrame = new ConvertDateTime(temp);
                                Task t = new Task(sub, 'E', 0, new_timeFrame.str);
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

            new Duke().run();
        }

        public static String getResponse(String input){
        return "hi";
        }


}


