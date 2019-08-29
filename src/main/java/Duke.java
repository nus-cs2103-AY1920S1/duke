import java.util.Scanner;

public class Duke {
    private boolean isOngoing;
    private TaskList taskList;

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
    private void read() {
        String inputString; // stores input from user
        Scanner in = new Scanner(System.in);

        this.isOngoing = true;
        this.taskList = new TaskList();

        // continuously take in user input until terminated
        while(isOngoing) {
            inputString = in.nextLine();
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
    private void process(String input) {
        // terminate if "bye"
        if (input.equals("bye")) {
            this.isOngoing = false;
        }

        // print list if "list"
        if (input.equals("list")) {
            this.taskList.printList();
        }

        // add to list otherwise
        this.taskList.addToList(input);
    }
}
