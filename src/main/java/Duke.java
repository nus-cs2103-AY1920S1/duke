import java.util.Scanner;

public class Duke {
    //Constructor
    public Duke() {
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        //First greetings
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("=====");
        Duke duke = new Duke();
        duke.run();
    }

    boolean inProgram;
    private void run() {
        //Initialise Scanner object
        Scanner input = new Scanner(System.in);
        //Continually receive commands until exit
        inProgram = true;
        while (inProgram) {
            String userCommand = input.next();
            if (userCommand.equals("bye")) {
                System.out.println("-----");
                System.out.println("    " + "Bye. Hope to see you again soon!");
                System.out.println("-----");
                inProgram = false;
            } else {
                System.out.println("-----");
                System.out.println("    " + userCommand);
                System.out.println("-----");
            }
        }
    }


}
