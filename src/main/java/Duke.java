import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        // Prints intro sequence
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String underscore = "    ____________________________________________________________" + "\n" ;
        String intro = underscore +
                       "      Hello! I'm Duke " + "\n" +
                       "      What can I do for you?" + "\n" +
                       underscore ;
        System.out.println(intro);

        // Creates an ArrayList of Task objects
        ArrayList<Task> tasks = new ArrayList<Task>();

        // Creates scanner object to handle input
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        String output = ""; String taskType = ""; String description = ""; String extraDescription = ""; int taskNum = -1;

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

                } else if (taskType.equals(possibleTasks.DEADLINE.toString().toLowerCase())) {

                    if ( ( command.length() < 9 )){ // Input is only "deadline"
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if ( (command.lastIndexOf('/') < 1) || (  4+command.lastIndexOf('/') > command.length()   ) )  {
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");
                    }

                    description = command.substring(9, command.lastIndexOf('/'));
                    extraDescription = command.substring(4 + command.lastIndexOf('/'));

                    Deadline newDeadline = new Deadline(description, extraDescription);
                    tasks.add(newDeadline);

                    output = underscore + "     Got it. I've added this task:\n       "
                            + newDeadline.toString() + "\n     Now you have " +
                            tasks.size() + " tasks in the list.\n" + underscore;

                    System.out.println(output);

                } else if (taskType.equals(possibleTasks.EVENT.toString().toLowerCase())) {

                    if ( ( command.length() < 6 )){ // Input is only "event"
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if ( (command.lastIndexOf('/') < 1) || (  4+command.lastIndexOf('/') > command.length()   ) )  {
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");
                    }

                    description = command.substring(6, command.lastIndexOf('/'));
                    System.out.println(command.lastIndexOf('/'));
                    extraDescription = command.substring(4 + command.lastIndexOf('/'));

                    Event newEvent = new Event(description, extraDescription);
                    tasks.add(newEvent);

                    output = underscore + "     Got it. I've added this task:\n       "
                            + newEvent.toString() + "\n     Now you have " +
                            tasks.size() + " tasks in the list.\n" + underscore;

                    System.out.println(output);

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



