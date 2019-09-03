package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * UI of the application.
 */
public class Ui {
    Scanner myScanner;
    StringJoiner displayMsg = new StringJoiner(System.lineSeparator());
    boolean isGui;

    public Ui() {
        myScanner = new Scanner(System.in);
        this.isGui = false;
    }

    public Ui(boolean isGui) {
        this.isGui = true;
    }

    public void printNonGuiDisplayMsg() {
        if (!this.isGui) {
            System.out.println(displayMsg);
        }
    }

    public void showLoadingError(String message) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add(message);
        printNonGuiDisplayMsg();
    }

    public void showWelcome() {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Hello! I'm Duke\nWhat can I do for you?");
        printNonGuiDisplayMsg();
    }

    /**
     * Reads user input.
     *
     * @return full user input
     */
    public String readCommand() {
        return myScanner.nextLine();
    }

    public void showDeadlineCommand(TaskList tasks) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Got it. I've added this task:");
        displayMsg.add(tasks.getTasks().get(tasks.getTasks().size() - 1).toString());
        displayMsg.add("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        printNonGuiDisplayMsg();
    }

    public void showDeleteCommand(TaskList tasks, int index) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Noted. I've removed this task:");
        displayMsg.add(tasks.getTasks().get(index).toString());
        displayMsg.add("Now you have " + (tasks.getTasks().size() - 1) + " tasks in the list.");
        printNonGuiDisplayMsg();
    }

    public void showListCommand(TaskList tasks) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Here are the tasks in your list");
        for (int i = 0; i < tasks.getTasks().size(); i = i + 1) {
            int number = i + 1;
            displayMsg.add(number + "." + tasks.getTasks().get(i));
        }
        printNonGuiDisplayMsg();
    }

    public void showDoneCommand(TaskList tasks, int index) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Nice! I've marked this task as done:");
        displayMsg.add("[" + tasks.getTasks().get(index).getDoneIcon()
                + "]" + tasks.getTasks().get(index).getDescription());
        printNonGuiDisplayMsg();
    }

    public void showEventCommand(TaskList tasks) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Got it. I've added this task:");
        displayMsg.add(tasks.getTasks().get(tasks.getTasks().size() - 1).toString());
        displayMsg.add("Now you have " + tasks.getTasks().size()  + " tasks in the list.");
        printNonGuiDisplayMsg();
    }

    public void showExitCommand() {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Bye. Hope to see you again soon!");
        printNonGuiDisplayMsg();
    }

    public void showFindCommand(TaskList tasks, String keyword) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Here are the matching tasks in your list:");
        for (Task task : tasks.getTasks()) {
            int counter = 1;
            if (task.getDescription().indexOf(keyword) != -1) {
                displayMsg.add(counter + "." + task);
            }
        }
        printNonGuiDisplayMsg();
    }

    public void showIncorrectCommand() {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("You have entered an invalid command");
        printNonGuiDisplayMsg();
    }

    public void showToDoCommand(TaskList tasks) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Got it. I've added this task:");
        displayMsg.add(tasks.getTasks().get(tasks.getTasks().size() - 1).toString());
        displayMsg.add("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        printNonGuiDisplayMsg();
    }
}
