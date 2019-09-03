import java.util.Scanner;

/**
 * The Ui class provides methods to deal with user interactions
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates an Ui object that contains a scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a string of welcome text to the user.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a sentence bidding goodbye to user.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Gets the next command the user's input.
     *
     * @return A string representing the next command.
     */
    public String readCommand() {
        String command = sc.next();
        return command;
    }

    /**
     * Gets the next integer the user's input.
     *
     * @return An int representing the next integer.
     */
    public int readNumber() {
        return sc.nextInt();
    }

    /**
     * Gets the next line of user's input.
     *
     * @return A string representing the next line of the user's input.
     */
    public String readLine() {
        return sc.nextLine();
    }

    /**
     * Prints a series of statements whenever a task is added.
     *
     * @param t A task that will be added to a list of tasks.
     */
    public void printAdd(Task t, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
    }

    /**
     * Prints a series of statements whenever a task is deleted.
     *
     * @param toDelete A task that will be deleted from the list of tasks.
     */
    public void printDelete(Task toDelete, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + toDelete);
        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
    }

    /**
     * Check whether the task description is a valid string.
     *
     * @param taskname A string representing the description of a task.
     * @return true if the description of the task is not an empty string.
     */
    public boolean checkValidity(String taskname) {
        boolean flag = true;
        try {
            if (taskname.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            flag = false;
        }
        return flag;
    }

    /**
     * Prints a series of statements whenever a task is done.
     *
     * @param number An int representing which task to be marked as done.
     * @param tasks A TaskList object that contains an ArrayList of task.
     */
    public void printDone(int number, TaskList tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getList().get(number - 1));
    }

    /**
     * Prints the list of tasks stored in the ArrayList
     *
     * @param tasks A TaskList object that contains an ArrayList of Task.
     */
    public void listTask(TaskList tasks) {
        System.out.println("Here are the " + (tasks.getList().size() == 1 ? "task" : "tasks") + " in your list:");
        for (int i = 1; i <= tasks.getList().size(); i++) {
            System.out.println(i + "." + tasks.getList().get(i - 1));
        }
    }

    public void printFind(String keyword, TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getList().get(i).getDescription().contains(keyword)) {
                System.out.println(count + "." + tasks.getList().get(i));
                count++;
            }
        }
    }
}

