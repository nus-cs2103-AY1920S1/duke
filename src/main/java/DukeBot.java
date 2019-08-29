import java.util.Scanner;

public class DukeBot {
    private TaskList taskList = new TaskList();
    private Boolean isOngoing;
    private String welcomeMessage = "What can I do for you?";

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
        System.out.println(welcomeMessage);
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
     *
     * @param input input string given by user.
     */
    private void process(String input) {
        // todo: extract list and done into TaskList class
        if (input.equals("bye")) {
            // if "bye", terminate
            this.isOngoing = false;
        } else if (input.equals("list")) {
            // if "list", print list
            this.taskList.printList();
        } else if (input.startsWith("done")) {
            String s = input.substring(5); // extract the task ID entered by user
            int taskId = Integer.parseInt(s);
            this.taskList.getTask(taskId).markAsDone();
        } else {
            // otherwise, add to list
            this.taskList.addToList(input);
        }
    }
}
