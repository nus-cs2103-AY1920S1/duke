package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private MainWindow window;

    /**
     * Constructs a command line interface that handles display of prompts and receiving user input.
     *
     * @deprecated use {@link #Ui(MainWindow)} instead.
     */
    @Deprecated
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Constructs a graphic interface that handles display of prompts and receiving user input.
     */
    public Ui(MainWindow window) {
        this.window = window;
    }

    /**
     * Prints a message to the console.
     *
     * @deprecated use {@link #printGuiMessage(String)} instead.
     * @param message the message to be printed.
     */
    @Deprecated
    public void printMessage(String message) {
        assert message.strip().length() > 0 : message;
        String[] messages = message.split("\n");
        System.out.println("    ____________________________________________________________");
        for (String line : messages) {
            System.out.println("     " + line);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    /**
     * Prints a message to the GUI.
     *
     * @param message the message to be printed.
     */
    public void printGuiMessage(String message) {
        assert message.strip().length() > 0 : message;
        window.handleDukeResponse(message);
    }

    /**
     * Prints a message when a task is added to the task list.
     *
     * @param task the task added to the task list.
     * @param tasks the task list.
     */
    public void printTaskAdded(Task task, TaskList tasks) {
        printGuiMessage("Got it (ﾟ▽ﾟ)/ \nI've added this task: \n  " + task
                + "\nNow you have " + Ui.pluralize("task", tasks.getSize()) + " in the list.");
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param task the task marked as done.
     */
    public void printTaskDone(Task task) {
        printGuiMessage("Got it (ﾟ▽ﾟ)/ \nI've marked this task as done:\n  " + task);
    }

    /**
     * Prints a message when a task is to be deleted.
     *
     * @param task the task to be deleted.
     * @param tasks the task list before deletion.
     */
    public void printTaskDeleted(Task task, TaskList tasks) {
        printGuiMessage("Noted (^._.^)ﾉ \nI've removed this task: \n  " + task
                + "\nNow you have " + Ui.pluralize("task", tasks.getSize() - 1) + " in the list.");
    }

    /**
     * Prints a message when a task is to be archived.
     *
     * @param task the task to be deleted.
     * @param tasks the task list before deletion.
     */
    public void printTaskArchived(Task task, TaskList tasks) {
        printGuiMessage("Noted (^._.^)ﾉ \nI've archived this task: \n  " + task
                + "\nNow you have " + Ui.pluralize("task", tasks.getSize() - 1) + " in the list.");
    }

    /**
     * Prints all tasks in the task list.
     *
     * @param tasks the task list.
     */
    public void printListTasks(TaskList tasks) {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append("Here are the tasks in your list:\n").append(tasks.toString());
        printGuiMessage(myBuilder.toString());
    }

    /**
     * Prints search results from the task list.
     *
     * @param results the list of search results.
     */
    public void printFindTasks(TaskList results) {
        if (results.getSize() > 0) {
            StringBuilder myBuilder = new StringBuilder();
            myBuilder.append("Here are the matching tasks in your list:\n").append(results.toString());
            printGuiMessage(myBuilder.toString());
        } else {
            printGuiMessage("There is no matching task in your list.");
        }
    }

    /**
     * Prints an error to the console.
     *
     * @param e the error to be printed.
     */
    public void printError(Exception e) {
        printGuiMessage("OOPS!!! " + e.getMessage());
    }

    /**
     * Reads a command from user input in the console.
     *
     * @deprecated because of the addition of the graphic interface.
     * @return the raw command string read from user input.
     */
    @Deprecated
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        printGuiMessage("こんにちは！メイドちゃんです！\nWhat can I do for you?");
    }

    /**
     * Converts the item specified in its plural form.
     *
     * @param item the item to be converted.
     * @param quantity the number of the item.
     * @return the plural form of the item with quantifier.
     */
    public static String pluralize(String item, Integer quantity) {
        assert quantity > 0 : quantity;
        assert item.length() > 0 : item;
        if (quantity == 1) {
            return "1 " + item;
        } else {
            return quantity + " " + item + "s";
        }
    }
}
