import com.sun.javafx.logging.JFRInputEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Ui ui = new Ui();
        String underscore = "    ____________________________________________________________" + "\n" ;

        // Creates and initialises variables
        ArrayList<Task> tasks = new ArrayList<Task>();
        String output = ""; String taskType = ""; String description = ""; String extraDescription = ""; int taskNum = -1;

        // Hard code the txt file location
        String filepath = "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt";
        //String filepath = "data/tasks.txt";

        // Creates the Storage class and reads / creates txt file
        Storage storage = new Storage(filepath);
        try {
            // Creates the storage class
            storage.createFile(filepath);

            // Reads any data from the txt file into the tasks ArrayList
            Scanner scanner = new Scanner(new File(filepath));
            ArrayList<String> inputsFromFile = new ArrayList<>();
            while (scanner.hasNextLine()){
                inputsFromFile.add(scanner.nextLine());
            }
            for (String input: inputsFromFile){
                // possible input string: "D | 0 | CS2103 Ip  | Wed 2359"
                String[] words = input.split("\\|") ;
                Boolean isDone;


                if (words[0].length() < 3 ){

                    if (words[0].contains("T")) { // Will avoid header
                        // Create a Todo class

                        if (words[1].contains("1")) {
                            isDone = true;
                        } else if (words[1].contains("0")) {
                            isDone = false;
                        }

                        description = words[2];

                        Todo newTodo = new Todo(description);
                        tasks.add(newTodo);

                    } else if (words[0].contains("E")) {
                        // Create an Event class

                        if (words[1].contains("1")) {
                            isDone = true;
                        } else if (words[1].contains("0")) {
                            isDone = false;
                        }

                        description = words[2];
                        extraDescription = words[3];

                        Event newEvent = new Event(description, extraDescription);
                        tasks.add(newEvent);

                    } else if (words[0].contains("D")) {
                        // Create a Deadline class

                        if (words[1].contains("1")) {
                            isDone = true;
                        } else if (words[1].contains("0")) {
                            isDone = false;
                        }

                        description = words[2];
                        extraDescription = words[3];

                        Deadline newDeadline = new Deadline(description, extraDescription);
                        tasks.add(newDeadline);
                    }
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }


        // Creates scanner object to handle input
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();

        while ( true ){

            taskType = command.split(" ")[0]; // taskType contains the first word of the command input string

            try {

                if (taskType.equals(possibleTasks.LIST.toString().toLowerCase())) {

                    output = underscore + "     Here are the tasks in your list:\n";
                    for (int i = 0; i < tasks.size(); i++) {
                        output += "     " + (i + 1) + "." + tasks.get(i).toString() + "\n";
                    }
                    output += underscore;

                    System.out.println(output);

                } else if (taskType.equals(possibleTasks.DONE.toString().toLowerCase())) {

                    taskNum = Integer.parseInt(command.substring(5)); // NTS: check for index outofbounds
                    taskNum--; // ArrayList index == taskNum - 1
                    tasks.get(taskNum).setDone();

                    output = underscore + "     Nice! I've marked this task as done:\n" +
                            "       [" + tasks.get(taskNum).getStatusIcon() + "] " + tasks.get(taskNum).description +
                            "\n" + underscore;

                    System.out.println(output);

                } else if (taskType.equals(possibleTasks.TODO.toString().toLowerCase())) {

                    if (command.length() < 5){
                        throw new DukeException ("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    description = command.substring(5);

                    Todo newTodo = new Todo(description);
                    tasks.add(newTodo);

                    output = underscore + "     Got it. I've added this task:\n       "
                            + newTodo.toString() + "\n     Now you have " +
                            tasks.size() + " tasks in the list.\n" + underscore;

                    System.out.println(output);
                    //storage.writeToFile( newTodo.toSaveString());

                } else if (taskType.equals(possibleTasks.DEADLINE.toString().toLowerCase())) {

                    if ( ( command.length() < 9 )){ // Input is only "deadline"
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if ( (command.lastIndexOf('/') < 1) || (  4+command.lastIndexOf('/') > command.length()   ) )  {
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");
                    }

                    description = command.substring(9, command.indexOf('/'));
                    extraDescription = command.substring(4 + command.indexOf('/'));

                    Deadline newDeadline = new Deadline(description, extraDescription);
                    tasks.add(newDeadline);

                    output = underscore + "     Got it. I've added this task:\n       "
                            + newDeadline.toString() + "\n     Now you have " +
                            tasks.size() + " tasks in the list.\n" + underscore;

                    System.out.println(output);
                    //storage.writeToFile(newDeadline.toSaveString());

                } else if (taskType.equals(possibleTasks.EVENT.toString().toLowerCase())) {

                    if ( ( command.length() < 6 )){ // Input is only "event"
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if ( (command.lastIndexOf('/') < 1) || (  4+command.lastIndexOf('/') > command.length()   ) )  {
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");
                    }

                    description = command.substring(6, command.lastIndexOf('/'));
                    extraDescription = command.substring(4 + command.lastIndexOf('/'));

                    Event newEvent = new Event(description, extraDescription);
                    tasks.add(newEvent);

                    output = underscore + "     Got it. I've added this task:\n       "
                            + newEvent.toString() + "\n     Now you have " +
                            tasks.size() + " tasks in the list.\n" + underscore;

                    System.out.println(output);
                    //storage.writeToFile(newEvent.toSaveString() );

                } else if (taskType.equals(possibleTasks.DELETE.toString().toLowerCase())){

                    taskNum = Integer.parseInt(command.substring(7)); // NTS: check for index outofbounds
                    taskNum--; // ArrayList index == taskNum - 1

                    if (taskNum >= tasks.size()){
                        throw new DukeException("Task no. " + (taskNum+1) + " does not exist");
                    }

                    Task taskToDelete = tasks.get(taskNum);
                    tasks.remove(taskNum);

                    output = underscore + "     Noted. I've removed this task.\n       " +
                            taskToDelete.toString() + "\n     Now you have " +
                            tasks.size() + " tasks in the list.\n" + underscore;

                    System.out.println(output);
                    taskToDelete = null;


                } else if (taskType.equals(possibleTasks.BYE.toString().toLowerCase())){

                    // Prints goodbye sequence
                    output = underscore + "\n" + "     " + "Bye. Hope to see you again soon!" + "\n" + underscore + "\n";
                    System.out.print(output);

                    // Saves the task arraylist to the txt file
                    storage.clearFileBeforeSaving();
                    for ( Task task:tasks){
                        storage.writeToFile(task.toSaveString());
                    }

                    break;

                } else { // An invalid task command is given
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            } catch (StringIndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            } catch (IOException e){
                System.out.println(e.getCause());
            }

            command = in.nextLine().trim();
            output = "";
        }

    }
    enum possibleTasks{
        BYE,
        LIST,
        DONE,
        DELETE,
        EVENT,
        TODO,
        DEADLINE
    }

}



