import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Store command-line input as String
        String input = "";

        //Read command-line input with Scanner
        Scanner scanner = new Scanner(System.in);

        //Initial opening introduction and prompt for user input
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println("");

        //Check for last statement
        while (!input.equals("bye")) {

            // Get entire line of input from command-line
            input = scanner.nextLine();

            //Echo all commands entered by user, except "bye", exit loop
            if (input.equals("bye")) break;
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + input);
            System.out.println("    ____________________________________________________________");
            System.out.println("");
        }
        //Closing statement
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
