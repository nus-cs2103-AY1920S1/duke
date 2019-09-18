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
     * @return true if there are inputs, false otherwise
     */
    public boolean hasInputs() {
        return sc.hasNextLine();
    }


    /**
     * Reads from standard input and returns it.
     *
     * @return String which consists of user input.
     */
    public String readCommand(){
        return sc.nextLine();
    }

    /**
     * Prints the welcome message of the application.
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

        appendWithNewline(builder,"Got it. I've added this task: ");
        appendWithNewline(builder,task.toString());
        appendWithNewline(builder,
                String.format("Now you have %d tasks in the list.",taskList.getNumTasks()));

        return builder.toString();



    }

    /**
     * Prints out the message when a task is deleted.
     *
     * @param task Task that was deleted.
     * @param taskList Task list that the Task was removed from.
     */
    public String printDeletion(Task task, TaskList taskList) {

        StringBuilder builder = new StringBuilder();
        appendWithNewline(builder,"Noted. I've removed this task: ");
        appendWithNewline(builder,
                String.format("Now you have %d tasks in the list.",taskList.getNumTasks()));

        return builder.toString();

    }


    /**
     * Prints a list of string in the correct format
     *
     * @param start List of Strings which needs to be printed sequentially
     */
    public String printInput(List<String> start) {
        StringBuilder builder = new StringBuilder();
        for (String input : start) {
            appendWithNewline(builder,input);
        }

        return builder.toString();

    }

    /**
     * Prints a single line of input in the correct format
     *
     * @param input String to be printed
     */
    public String printOneLine(String input) {
        StringBuilder builder = new StringBuilder();
        appendWithNewline(builder,input);

        return builder.toString();

    }


    /**
     * Prints the list of tasks.
     *
     * @param taskList List of tasks stored in the application.
     */


    public String printNumberList(TaskList taskList) {

        StringBuilder builder = new StringBuilder();
        appendWithNewline(builder, "Here are the tasks in your list:");
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            appendWithNewline(builder,
                    String.format("%d.%s",i+1, taskList.getTaskAt(i+1)));
        }

        return builder.toString();
    }


    /**
     * Prints the list of tasks.
     *
     * @param lst List of tasks with matching keyword
     */
    public String printFindResults(List<Task> lst) {

        StringBuilder builder = new StringBuilder();


        builder.append("Here are the matching tasks in your list:");
        builder.append("\n");
        for (int i = 0; i < lst.size(); i++) {
            appendWithNewline(builder,
                    String.format("%d.%s",i+1, lst.get(i)));
        }

        return builder.toString();
    }


    /**
     * Prints the exit message.
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


    private void appendWithNewline(StringBuilder builder, String msg) {
        builder.append(msg);
        builder.append("\n");
    }

    
}

