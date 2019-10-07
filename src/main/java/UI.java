public class UI {

    public UI() {

    }

    /**
     * Prints out the opening.
     *
     */

    public static void start() {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLineS();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printLineS() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints out bye when user inputs bye.
     *
     */

    public static void bye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLineS();
    }

    public static void listOut() {
        System.out.println("Here are the tasks in your list:");
    }

    public static void listcount(int count) {
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public static void done() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    public static void taskadded() {
        System.out.println("Got it. I've added this task:");
    }

    public static void find() {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
    }

}