package duke.d_interface;

/**
 * Involves in the setup and exiting of Duke. It also ensure that the task
 * list in Duke is not violated.
 */
public class Ui {
    /**
     * Checks that a valid index is given when referencing the task list.
     */
    public void numberErrorMessage() {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! Please type in a valid index from 1 to 100\n" +
                "    ____________________________________________________________\n");
    }

    /**
     * Checks that a valid index is given when referencing the task list.
     * @param len Index given of the specific task.
     */
    public void indexErrorMessage(int len) {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! Index out of bounds for task list of length " + len + "\n" +
                "    ____________________________________________________________\n");
    }

    /**
     * Greets the user whenever they start up Duke.
     */
    //Greet the user when starting up Duke
    public void greet() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        printLine();
        System.out.println(logo);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        printLine();
        printLine();
        System.out.println("     Here are the tasks that you have now:");
    }

    /**
     * Echo the command that the user gives to Duke.
     * @param command Command which the user gives when interacting with Duke.
     */
    // Echo commands entered by users
    public void echo(String command) {
        printLine();
        System.out.println("     " + command);
        printLine();
        System.out.println();
    }

    /**
     * Exits and closes Duke when the "bye" command is given.
     */
    //Exits when the user types bye
    public void exit() {
        printLine();
        System.out.println("      Bye. Hope to see you again soon!");
        printLine();
        System.out.println();
    }

    /**
     * Helps to print out a generic line for indentation purposes.
     */
    private void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
