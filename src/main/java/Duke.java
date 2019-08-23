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
        String command = in.nextLine();
        String output = ""; String taskType = ""; String description = ""; String extraDescription = ""; int taskNum = -1;

        while ( !command.equals("bye") ){

            taskType = command.split(" ")[0];

            if ( command.equals("list")){

                output = underscore + "     Here are the tasks in your list:\n";
                for (int i = 0; i < tasks.size(); i++){
                    output +=  "     " + (i+1) + "." + tasks.get(i).toString() + "\n";
                }
                output += underscore;

                System.out.println(output);

            } else if ( taskType.equals("done")){

                taskNum = Integer.parseInt(command.substring(5));
                taskNum--; // ArrayList index == taskNum - 1
                tasks.get(taskNum).setDone();

                output = underscore + "     Nice! I've marked this task as done:\n" +
                        "       [" + tasks.get(taskNum).getStatusIcon() + "] " + tasks.get(taskNum).description +
                        "\n" + underscore;

                System.out.println(output);

            } else if (taskType.equals("todo")){

                description = command.substring(5);

                Todo newTodo = new Todo(description);
                tasks.add(newTodo);

                output = underscore + "     Got it. I've added this task:\n       "
                        + newTodo.toString() + "\n     Now you have " +
                        tasks.size() + " tasks in the list.\n" + underscore;

                System.out.println(output);

            } else if (taskType.equals("deadline")){

                description = command.substring(9, command.lastIndexOf('/'));
                extraDescription = command.substring(4 + command.lastIndexOf('/'));

                Deadline newDeadline = new Deadline(description, extraDescription);
                tasks.add(newDeadline);

                output = underscore + "     Got it. I've added this task:\n       "
                        + newDeadline.toString() + "\n     Now you have " +
                        tasks.size() + " tasks in the list.\n" + underscore;

                System.out.println(output);

            } else if (taskType.equals("event")){

                description = command.substring(6, command.lastIndexOf('/'));
                extraDescription = command.substring(4 + command.lastIndexOf('/'));

                Event newEvent = new Event(description, extraDescription);
                tasks.add(newEvent);

                output = underscore + "     Got it. I've added this task:\n       "
                        + newEvent.toString() + "\n     Now you have " +
                        tasks.size() + " tasks in the list.\n" + underscore;

                System.out.println(output);

            } else { // Invalid command

                System.out.println("Invalid command given");

            }

            command = in.nextLine();
            output = "";
        }

        // Prints goodbye sequence
        output = underscore + "\n" + "     " + "Bye. Hope to see you again soon!" + "\n" + underscore + "\n";
        System.out.print(output);
    }

}



