import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.start();
    }

    /**
     * Main driver function for the program -
     * Greets user, then repeatedly takes in
     * user input and processes them.
     */
    private void start() {
        boolean notShutdown = true;
        Scanner sc = new Scanner(System.in);

        this.greetHello();
        do {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                notShutdown = false;
            } else {
                this.formattedPrintln(input);
            }
        } while (notShutdown);
        this.greetGoodbye();
    }


    /**
     * Prints the target string between two horizontal
     * bars.
     *
     * @param output  The string to be printed
     */
    private void formattedPrintln(String output) {
        System.out.println("____________________________________________________________\n"
                + output
                + "\n"
                + "____________________________________________________________\n");
    }

    /**
     * Prints out a formatted hello greeting on the
     * screen. It is a implemented as a thin wrapper
     * around duke.formattedPrintln()
     */
    private void greetHello() {
        String hello = "Hello! I'm Duke\n"
                + "What can I do for you?";
        this.formattedPrintln(hello);
    }

    /**
     * Prints out a formatted goodbye greeting on the
     * screen. It is a implemented as a thin wrapper
     * around duke.formattedPrintln()
     */
    private void greetGoodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        this.formattedPrintln(goodbye);
    }
}