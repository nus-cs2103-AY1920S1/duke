import java.util.Scanner;

public class DukeBot {
    private TaskList taskList = new TaskList();
    private Boolean isOngoing;

    /**
     * Starts DukeBot and prints welcome message.
     */

    public void initialise() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?"); // todo: replace with configurable message
        run();
    }

    /**
     * Reads input from the user and passes it to process().
     */
    private void run() {
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
        if (input.equals("bye")) {
            // terminate if "bye"
            this.isOngoing = false;
        } else if (input.equals("list")) {
            // print list if "list"
            this.taskList.printList();
        } else if (input.startsWith("done")) {
            int taskId = Integer.parseInt(input.substring(5));
            this.taskList.getTask(taskId).markAsDone();
        } else {
            // add to list otherwise
            this.taskList.addToList(input);
        }
    }
}
