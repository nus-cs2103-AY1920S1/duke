package duke;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public void run(){
        // Variable initialization
        String underscore = "    ____________________________________________________________" + "\n" ;
        String output = ""; String taskType = ""; String description = ""; String extraDescription = ""; int taskNum = -1;

        ui.showWelcome();

        try {
            this.tasks = this.storage.load();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        // Creates scanner object to handle input
        Scanner in = new Scanner(System.in);
        String inputLine = in.nextLine().trim();

        while ( true ){
            taskType = inputLine.split(" ")[0]; // taskType contains the first word of the command input string
            try {

                // LIST case
                if (taskType.equals(possibleTasks.LIST.toString().toLowerCase())) {

                    output = underscore + "     Here are the tasks in your list:\n";
                    for (int i = 0; i < tasks.size(); i++) {
                        output += "     " + (i + 1) + "." + tasks.get(i).toString() + "\n";
                    }
                    output += underscore;

                    System.out.println(output);

                    // DONE case
                } else if (taskType.equals(possibleTasks.DONE.toString().toLowerCase())) {

                    taskNum = Integer.parseInt(inputLine.substring(5)); // NTS: check for index outofbounds
                    taskNum--; // ArrayList index == taskNum - 1
                    tasks.get(taskNum).setDone();

                    output = underscore + "     Nice! I've marked this task as done:\n" +
                            "       [" + tasks.get(taskNum).getStatusIcon() + "] " + tasks.get(taskNum).getTask() +
                            "\n" + underscore;

                    System.out.println(output);

                    // TODO case
                } else if (taskType.equals(possibleTasks.TODO.toString().toLowerCase())) {

                    if (inputLine.length() < 5){
                        throw new DukeException ("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    description = inputLine.substring(5);

                    Todo newTodo = new Todo(description);
                    tasks.add(newTodo);

                    output = underscore + "     Got it. I've added this task:\n       "
                            + newTodo.toString() + "\n     Now you have " +
                            tasks.size() + " tasks in the list.\n" + underscore;

                    System.out.println(output);

                    // DEADLINE case
                } else if (taskType.equals(possibleTasks.DEADLINE.toString().toLowerCase())) {

                    if ( ( inputLine.length() < 9 )){ // Input is only "deadline"
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if ( (inputLine.lastIndexOf('/') < 1) || (  4+inputLine.lastIndexOf('/') > inputLine.length()   ) )  {
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");
                    }

                    description = inputLine.substring(9, inputLine.indexOf('/'));
                    extraDescription = inputLine.substring(4 + inputLine.indexOf('/'));

                    Deadline newDeadline = new Deadline(description, extraDescription);
                    tasks.add(newDeadline);

                    output = underscore + "     Got it. I've added this task:\n       "
                            + newDeadline.toString() + "\n     Now you have " +
                            tasks.size() + " tasks in the list.\n" + underscore;

                    System.out.println(output);

                    // EVENT case
                } else if (taskType.equals(possibleTasks.EVENT.toString().toLowerCase())) {

                    if ( ( inputLine.length() < 6 )){ // Input is only "event"
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if ( (inputLine.lastIndexOf('/') < 1) || (  4+inputLine.lastIndexOf('/') > inputLine.length()   ) )  {
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");
                    }

                    description = inputLine.substring(6, inputLine.lastIndexOf('/'));
                    extraDescription = inputLine.substring(4 + inputLine.lastIndexOf('/'));

                    Event newEvent = new Event(description, extraDescription);
                    tasks.add(newEvent);

                    output = underscore + "     Got it. I've added this task:\n       "
                            + newEvent.toString() + "\n     Now you have " +
                            tasks.size() + " tasks in the list.\n" + underscore;

                    System.out.println(output);

                    // DELETE case
                } else if (taskType.equals(possibleTasks.DELETE.toString().toLowerCase())){

                    taskNum = Integer.parseInt(inputLine.substring(7)); // NTS: check for index outofbounds
                    taskNum--; // ArrayList index == taskNum - 1

                    if (taskNum >= tasks.size()){
                        throw new DukeException("duke.task.Task no. " + (taskNum+1) + " does not exist");
                    }

                    Task taskToDelete = tasks.get(taskNum);
                    tasks.remove(taskNum);

                    output = underscore + "     Noted. I've removed this task.\n       " +
                            taskToDelete.toString() + "\n     Now you have " +
                            tasks.size() + " tasks in the list.\n" + underscore;

                    System.out.println(output);
                    taskToDelete = null;

                    //BYE case
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
                ui.showLoadingError(e);
            } catch (StringIndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            } catch (IOException e){
                System.out.println(e.getCause());
            }

            inputLine = in.nextLine().trim();
            output = "";
        }

    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt").run();
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



