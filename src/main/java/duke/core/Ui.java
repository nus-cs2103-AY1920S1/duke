package duke.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.tasks.Task;


/**
 * Represents the user interface of the application. Provides methods for reading input
 * and printing output to the console.
 */
public class Ui {

    private Scanner sc = new Scanner(System.in);


    /**
     * Checks if there are inputs to be read
     *
     * @return True if there are inputs, false otherwise
     */
    public boolean hasInputs() {
        return sc.hasNextLine();
    }


    /**
     * Reads from standard input and returns it.
     *
     * @return String which consists of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the welcome message of the application.
     *
     * @return Welcome message.
     */
    public String printWelcomeMessage() {
        List<String> start = new ArrayList<>();
        start.add("Hello! I'm Duke");
        start.add("What can I do for you?");
        return this.printInput(start);
    }

    /**
     * Prints a message when a task is added.
     *
     * @param task Task that was added.
     * @param taskList Task list where the Task is stored.

     */
    public String printAddMessage(Task task, TaskList taskList) {
        StringBuilder builder = new StringBuilder();

        appendWithNewline(builder, "Got it. I've added this task: ");
        appendWithNewline(builder, task.toString());
        appendWithNewline(builder,
                String.format("Now you have %d tasks in the list.", taskList.getNumTasks()));

        return builder.toString();


    }

    /**
     * Prints out the message when a task is deleted.
     *
     * @param task Task that was deleted.
     * @param taskList Task list that the Task was removed from.
     * @return String message to be printed.
     */
    public String printDeletion(Task task, TaskList taskList) {

        StringBuilder builder = new StringBuilder();
        appendWithNewline(builder, "Noted. I've removed this task: ");
        appendWithNewline(builder, task.toString());
        appendWithNewline(builder,
                String.format("Now you have %d tasks in the list.", taskList.getNumTasks()));

        return builder.toString();

    }


    /**
     * Prints a list of string in the correct format
     *
     * @param start List of Strings which needs to be printed sequentially
     * @return String message to be printed.
     */
    public String printInput(List<String> start) {
        StringBuilder builder = new StringBuilder();
        for (String input : start) {
            appendWithNewline(builder, input);
        }

        return builder.toString();

    }

    /**
     * Prints a single line of input in the correct format
     *
     * @param input String to be printed
     * @return String message to be printed.
     */
    public String printOneLine(String input) {
        StringBuilder builder = new StringBuilder();
        appendWithNewline(builder, input);

        return builder.toString();

    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList List of tasks stored in the application
     * @return String message to be printed.
     */
    public String printNumberList(TaskList taskList) {

        StringBuilder builder = new StringBuilder();
        appendWithNewline(builder, "Here are the tasks in your list:");
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            appendWithNewline(builder,
                    String.format("%d.%s", i + 1, taskList.getTaskAt(i + 1)));
        }

        return builder.toString();
    }


    /**
     * Prints the list of tasks.
     *
     * @param lst List of tasks with matching keyword
     * @return String message to be printed.
     */
    public String printFindResults(List<Task> lst) {

        StringBuilder builder = new StringBuilder();


        builder.append("Here are the matching tasks in your list:");
        builder.append("\n");
        for (int i = 0; i < lst.size(); i++) {
            appendWithNewline(builder,
                    String.format("%d.%s", i + 1, lst.get(i)));
        }

        return builder.toString();
    }


    /**
     * Prints the exit message.
     *
     * @return String message to be printed.
     */
    public String printByeMessage() {

        return this.printOneLine("Bye. Hope to see you again soon!");
    }


    /**
     * Prints out the exception.
     *
     * @param e Exception object whose message is to be printed out
     */
    public String printErrorMessage(Exception e) {
        return this.printOneLine(e.getMessage());
    }

    //helper method to attach a newline after adding a message to the same StringBuilder
    private void appendWithNewline(StringBuilder builder, String msg) {
        builder.append(msg);
        builder.append("\n");
    }

    /**
     * Prints out a mini user manual to help the user
     *
     * @return String message to be printed.
     */
    public String printHelpMessage() {
        return printInput(List.of(
                "List of commands with format:",
                "",
                "     todo TASK_DESCRIPTION : Adds a todo task to list",
                "     deadline TASK_DESCRIPTION /by dd/mm/yyyy hhmm : Adds a deadline to list " +
                        " with a recognisable datetime format",
                "     event TASK_DESCRIPTION /at dd/mm/yyyy hhmm : Adds a deadline to list" +
                        " with a recognisable datetime format",
                "     deadline TASK_DESCRIPTION /by DUE_DATE : To add deadline to list with any due date format",
                "     event TASK_DESCRIPTION /at DUE_DATE : To add event to list with any due date format",
                "     e.g. todo report",
                "     e.g. deadline report /by 12/12/1212 2312",
                "     e.g. event meetup /at 12/12/1212 2312",
                "     e.g. deadline report /by Monday",
                "     e.g. event meetup /at Monday",
                "",
                "     find KEYWORD : Find tasks with the word in the description/name",
                "",
                "     done [TASK_NUMBER] : Checks task at a particular index as done",
                "     delete [TASK_NUMBER] : Deletes task at a particular index",
                "",
                "     list : Shows all undeleted tasks",
                "     bye : Exit the program",
                "",
                "     Note: bracketed inputs like [TASK_NUMBER] denote that only integer inputs are accepted"));
    }


}




