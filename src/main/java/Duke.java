import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Prints intro sequence
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String underscore = "    ____________________________________________________________";
        String intro = underscore +
                       "      Hello! I'm Duke " + "\n" +
                       "      What can I do for you?" + "\n" +
                       underscore ;
        System.out.println(intro);

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        String output = "";

        while ( !command.equals("bye") ){
            output = underscore + "\n" + "      " + command + "\n" + underscore + "\n";
            System.out.print(output);
            command = in.nextLine();
        }

        output = underscore + "\n" + "      " + "Bye. Hope to see you again soon!" + "\n" + underscore + "\n";
        System.out.print(output);
    }
}
