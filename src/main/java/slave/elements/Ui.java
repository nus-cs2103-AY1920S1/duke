package slave.elements;

import slave.exception.DukeException;
import slave.task.Deadline;
import slave.task.Event;
import slave.task.Task;
import slave.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * User Interface that prints to user
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prints welcome Message on application start-up
     */
    public void showWelcomeMessage() {
        String message = "Hello! I'm Slave! Your very own productivity application.\n" +
                "     What can I do for you? Type 'help' for commands!";
        System.out.println(Formatter.formatMessage(message));
    }

    /**
     * Displays goodbye message when exiting program
     */
    public void showByeMessage() {
        String message = "Bye! Thanks for using me! Will be right here when you need me.";
        System.out.println(Formatter.formatMessage(message));
    }

    /**
     * Displays help message when prompted
     */
    public void showHelpMessage() {
        String message = "Commands:\n" +
                "     todo <task description> : To add task to list\n" +
                "     event <task description> /at <date> : To add event to list\n" +
                "     deadline <task description> /by <date> : To add deadline to list\n" +
                "     list : Retrieves all the tasks you have so far\n" +
                "     done <task index> : Checks task as done\n" +
                "     delete <task index> : Deletes task at a particular index\n" +
                "     clear : clear all tasks in the list and wipe all data\n" +
                "     bye : Exit program\n" +
                "     Note: Currently, Slave can only read date in the form 'DD/MM/YYYY HHMM'\n" +
                "     (E.g. 2/1/2020 1254 will be read as 2nd of January 2020 12.54pm)";
        System.out.println(Formatter.formatMessage(message));
    }

    /**
     * Prints error message to the user
     * @param e Error to be broadcasted
     */
    public void showErrorMessage(DukeException e){
        System.out.println(Formatter.formatMessage(e.getMessage()));
    }

    /**
     * Read input from user
     * @return returns user input
     */
    public String readCommand(){
        return scanner.nextLine();
    }

    /**
     * prints clear command message
     */
    public void printClearCommand() {
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Clearing List..."));
        Formatter.printLine();
    }

    /**
     * prints add deadline command
     * @param deadlineTask deadline task to be added
     * @param taskList current task list
     */
    public void printAddDeadlineCommand(Deadline deadlineTask, TaskList taskList) {
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Got it. I've added this task:"));
        System.out.println(Formatter.indentLine("  " + deadlineTask));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }

    /**
     * prints add event command
     * @param eventTask event task to be added
     * @param taskList current task list
     */
    public void printAddEventCommand(Event eventTask, TaskList taskList) {
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Got it. I've added this task:"));
        System.out.println(Formatter.indentLine("  " + eventTask));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }


    /**
     * prints add to-do command
     * @param toDoTask to-do task to be added
     * @param taskList current task list
     */

    public void printAddToDoCommand(ToDo toDoTask, TaskList taskList) {
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Got it. I've added this task:"));
        System.out.println(Formatter.indentLine("  " + toDoTask));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }

    /**
     * prints delete command
     * @param toRemove task to be removed
     * @param taskList current task list
     */
    public void printDeleteCommand(Task toRemove, TaskList taskList){
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Noted. I've removed this task:"));
        System.out.println(Formatter.indentLine("  " + toRemove));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }

    /**
     * prints done command
     * @param task task to be set as done
     */
    public void printDoneCommand(Task task) {
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Nice! I've marked this task as done:"));
        System.out.println(Formatter.indentLine("  " + task));
        Formatter.printLine();
    }

    /**
     * prints a list of current tasks
     * @param taskList current task list
     */
    public void printListCommand(TaskList taskList) {
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Here are the tasks in your list:"));
        for (Task task: taskList.getList()){
            System.out.println(Formatter.indentLine(task.getId() + "." + task.toString()));
        }
        Formatter.printLine();
    }

    /**
     * prints a list of matching results
     * @param findList list of searched entries
     */
    public void printFindCommand(ArrayList<Task> findList) {
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Here are the matching tasks in your list:"));
        int index = 1;
        for (Task task: findList) {
            System.out.println(Formatter.indentLine(index + ") " + task.getId() + "." + task.toString()));
            index++;
        }
        Formatter.printLine();
    }
}
