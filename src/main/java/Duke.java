import java.io.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;

public class Duke {
    public static String toDateString(String datetime){
        try {
            String result = "";
            String[] s = datetime.split(" ");
            String[] date = s[0].split("/");
            int intDay = Integer.parseInt(date[0]);
            int intMonth= Integer.parseInt(date[1]);
            int year= Integer.parseInt(date[2]);
            String day ="";
            int intTime = Integer.parseInt(s[1]);
            String time = "";
            if (intDay>3) {
                day = intDay + "th";
            } else if(intDay == 1) {
                day = intDay + "st";
            } else if (intDay == 2) {
                day = intDay + "nd";
            } else if (intDay == 3){
                day = intDay + "rd";
            }
            int intMinutes = intTime%100;
            String minutes = "";
            if (intMinutes/10 == 0){
                minutes = "0" + intMinutes;
            }

            if (intTime > 1259) {
                intTime -= 1200;
                time = intTime/100 + "." + minutes + "pm";
            } else if (intTime >1159) {
                time = intTime/100 + "." + minutes + "pm";
            } else if (intTime <1159){
                time = intTime/100 + "." + minutes + "am";
            }

            String month = new DateFormatSymbols().getMonths()[intMonth-1];
            result = day + " of " + month + " " + year +", " + time;
            return result;

        }catch(Exception e){return null;}

    }
    public static void list(ArrayList<Task> tasks){
        int size = tasks.size();
        //may have to catch error if no items in list
        StringBuilder listOfTask = new StringBuilder();
        for (int i = 0; i < size; i++) {
            listOfTask.append(i+1+". " +tasks.get(i)+"\n" + "     ");
        }
        print(listOfTask.toString());
    }

    public static void print(String message) {
        System.out.println(
                "    ____________________________________________________________\n" +
                "     " + message + "\n" +
                "    ____________________________________________________________");
    }
    public static void main(String[] args) throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        print("Hello! I am Duke\n" +
                "     What can I do for you?");
        ArrayList<Task> tasks = new ArrayList<>();
        storage.load(tasks);
        list(tasks);

        while (!ui.getInput().equals("bye")) {
            ui.setInput();
            String[] temp = ui.getInput().split(" ");
            if (ui.getInput().equals("list")) {
               list(tasks);
            } else if(ui.getInput().equals("bye")) {
                print("Bye. Hope to see you again soon!");

            }else if (temp[0].equals("done")) {
                try {
                    //no input number
                    if (temp.length == 1) {
                        throw new NumberFormatException();
                    }
                    int num = Integer.parseInt(temp[1]);
                    //invalid num, will index out of bounds
                    if (num > tasks.size()) {
                        throw new NumberFormatException();
                    } else {
                        tasks.get(num - 1).markAsDone();
                        storage.save(tasks);
                    }
                }catch(NumberFormatException e){
                    print("☹ OOPS!!! Please input a valid number.");
                }

            }else {
                Task task = null;
                if(temp[0].equals("todo")){
                    //trim so that cannot pass with just spaces
                    String desc = ui.getInput().substring(4).trim();
                    if (desc.equals("")) {
                        print("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        task = new Todo(desc);

                    }

                } else if (temp[0].equals("deadline")) {
                    int num = ui.getInput().indexOf("/by");
                    //length == 1 means only has 'deadline', and temp[1] equal /by means no desc as well
                    if (temp.length == 1 || temp[1].equals("/by")) {
                        print("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    //-1 means /by is not found
                    else if (num == -1){
                       print("☹ OOPS!!! Please type /by before inputting the deadline.");

                    }else {
                        String desc = ui.getInput().substring(9, num);
                        //trim so that cannot pass with just spaces
                        String by = ui.getInput().substring(num + 3).trim();
                        //no input time after /by
                        if (by.equals("")) {
                           print("☹ OOPS!!! Please input a deadline after /by");
                        }else {
                            task = new Deadline(desc, by);
                        }
                    }
                } else if (temp[0].equals("event")) {
                    int num = ui.getInput().indexOf("/at");
                    //length == 1 means only has 'event', and temp[1] equal /at means no desc as well
                    if (temp.length == 1 || temp[1].equals("/at")) {
                        print("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    //-1 means /at is not found
                    else if (num == -1) {
                        print("☹ OOPS!!! Please type /at before inputting the time.");
                    } else {
                        String desc = ui.getInput().substring(6, num);
                        //trim so that cannot pass with just spaces
                        String at = ui.getInput().substring(num + 3).trim();
                        //no input time after /at
                        if (at.equals("")) {
                            print("☹ OOPS!!! Please input a time after /at");
                        } else {
                            task = new Event(desc, at);
                        }
                    }
                }else if (temp[0].equals("delete")) {
                    try {
                        //no input number
                        if (temp.length == 1) {
                            throw new NumberFormatException();
                        }
                        int num = Integer.parseInt(temp[1]);
                        //invalid num, will index out of bounds
                        if (num > tasks.size()) {
                            throw new NumberFormatException();
                        } else {
                            print("Noted. I've removed this task:\n" +
                                    "     " + tasks.get(num-1) + "\n" +
                                    "     Now you have " + (tasks.size() - 1) + " tasks in the list.");
                           tasks.remove(num-1);
                        }
                    }catch(NumberFormatException e){
                        print("☹ OOPS!!! Please input a valid number.");
                    }
                } else {
//                     if anything else other than todo, deadline or event as first word, don't recognise
                    print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
                if (task == null) {
                    // if task is still null do nothing
                } else {
                    tasks.add(task);
                    print("Got it. I've added this task:\n" +
                            "       " + task + "\n" +
                            "     Now you have " + tasks.size() + " tasks in the list.");

                }
                storage.save(tasks);

            }
        }

    }
//    @Override
//    public void start(Stage stage) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        Label label = new Label(logo); // Creating a new Label control
//        Scene scene = new Scene(label); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//    }
}
