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

        // Creates an ArrayList
        ArrayList<String> tasks = new ArrayList<String>();

        // Creates scanner object to handle input
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        String output = "";

        while ( !command.equals("bye") ){

            if ( command.equals("list")){
                output = underscore;
                for (int i = 0; i < tasks.size(); i++){
                    output +=  "     " + (i+1) + ". " + tasks.get(i) + "\n";
                }
                output += underscore;

                System.out.println(output);
            } else {
                tasks.add(command);
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
