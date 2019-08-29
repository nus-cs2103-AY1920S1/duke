import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?"); // todo: replace with configurable message
        read();
    }

    /**
     * Reads input from the user and passes it to process().
     */
    public static void read() {
        String inputString; // stores input from user
        Scanner in = new Scanner(System.in);

        // continuously take in user input until terminated
        while(true) {
            inputString = in.nextLine();
            if (inputString.equals("bye")) {
                break;
            }
            process(inputString);
        }

        // say goodbye upon exit
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Creates the given input string.
     * For Level-1, simply echoes the string.
     *
     * @param input input string given by user.
     */
    public static void process(String input) {
        // print given input to console
        System.out.println(input);
    }
}
