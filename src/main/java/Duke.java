import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;

public class Duke {
    public static void print(String message) {
        System.out.println(
                "    ____________________________________________________________\n" +
                "     " + message + "\n" +
                "    ____________________________________________________________\n");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       print("Hello! I am Duke\n" +
                "     What can I do for you?");
        String input = "";
        ArrayList<Task> tasks = new ArrayList<>();
        while (!input.equals("bye")) {
            input = sc.nextLine();
            String[] temp = input.split(" ");
            if (input.equals("list")) {
                int size = tasks.size();
                //may have to catch error if no items in list
                StringBuilder listOfTask = new StringBuilder();
                for (int i = 0; i < size; i++) {
                   listOfTask.append(i+1+". " +tasks.get(i)+"\n" + "     ");
                }
                print(listOfTask.toString());
            } else if(input.equals("bye")) {
                print("Bye. Hope to see you again soon!");

           }else if (temp[0].equals("done")) {
                try {
                    if (temp.length == 1) {
                        throw new NumberFormatException();
                    }
                    int num = Integer.parseInt(temp[1]);
                    if (num > tasks.size()) {
                        throw new NumberFormatException();
                    } else {
                        tasks.get(num - 1).markAsDone();
                    }
                }catch(NumberFormatException e){
                    print("☹ OOPS!!! Please input a valid number.");
                } 

            }else {
                Task task = null;
                if(temp[0].equals("todo")){
                    //trim so that cannot pass with just spaces
                    String desc = input.substring(4).trim();
                    if (desc.equals("")) {
                        print("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        task = new Todo(desc);
                    }

                } else if (temp[0].equals("deadline")) {
                    int num = input.indexOf("/by");
                    //length == 1 means only has 'deadline', and temp[1] equal /by means no desc as well
                    if (temp.length == 1 || temp[1].equals("/by")) {
                        print("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    //-1 means /by is not found
                    else if (num == -1){
                       print("☹ OOPS!!! Please type /by before inputting the deadline.");

                    }else {
                        String desc = input.substring(9, num);
                        //trim so that cannot pass with just spaces
                        String by = input.substring(num + 3).trim();
                        if (by.equals("")) {
                           print("☹ OOPS!!! Please input a deadline after /by");
                        }else {
                            task = new Deadline(desc, by);
                        }
                    }
                } else if (temp[0].equals("event")) {
                    int num = input.indexOf("/at");
                    //length == 1 means only has 'event', and temp[1] equal /at means no desc as well
                    if (temp.length == 1 || temp[1].equals("/at")) {
                        print("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    //-1 means /at is not found
                    else if (num == -1){
                        print("☹ OOPS!!! Please type /at before inputting the time.");
                    }else {
                        String desc = input.substring(6, num);
                        //trim so that cannot pass with just spaces
                        String at = input.substring(num + 3).trim();

                        if (at.equals("")) {
                            print("☹ OOPS!!! Please input a time after /at");
                        } else {
                            task = new Event(desc, at);
                        }
                    }
                } else {
//                     if anything else other than todo, deadline or event as first word, don't recognise
                    print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
                if (task == null) {
                    // if task is still null do nothing
                } else {
                    tasks.add(task);
                    print("Got it. I've added this task: \n" +
                            "       " + task + "\n" +
                            "     Now you have " + tasks.size() + " tasks in the list.");

                }
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
