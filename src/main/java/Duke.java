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
        String output = "";
        int taskNum = -1;

        while ( !command.equals("bye") ){

            if ( command.equals("list")){

                output = underscore + "     Here are the tasks in your list:\n";
                for (int i = 0; i < tasks.size(); i++){
                    output +=  "     " + (i+1) + ".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description + "\n";
                }
                output += underscore;

                System.out.println(output);

            } else if ( command.substring(0,4).equals("done")){

                taskNum = Integer.parseInt(command.substring(5));
                taskNum--; // ArrayList index == taskNum - 1
                tasks.get(taskNum).setDone();

                output = underscore + "     Nice! I've marked this task as done:\n" +
                        "       [" + tasks.get(taskNum).getStatusIcon() + "] " + tasks.get(taskNum).description +
                        "\n" + underscore;

                System.out.println(output);

            } else {

                tasks.add(new Task(command));
                output = underscore + "     " + "added: " + command + "\n" + underscore;
                System.out.println(output);

            }

            command = in.nextLine();
            output = "";
        }

        // Prints goodbye sequence
        output = underscore + "\n" + "     " + "Bye. Hope to see you again soon!" + "\n" + underscore + "\n";
        System.out.print(output);
    }

}



