import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a user interface that deals with the interaction between the user and computer.
 */
public class Ui {

    private String printStatement;

    public String print() {
        return this.printStatement;
    }

    public void setPrintStatement(String str) {
        this.printStatement = str;
    }

    public void setErrorMessage(String errorMsg) {
        this.printStatement = errorMsg;
    }

    /**
     * Prints welcome statements for the user.
     */
    public static String printHello() {
        String str = "Hello! I'm Duke\nWhat can I do for you?";
        return str;
    }

    /**
     * Prints good bye statements for the user.
     */
    public void printBye() {
        setPrintStatement("Bye. Hope to see you again soon!");
    }

    /**
     * Prints statements after adding the task to taskList.
     *
     * @param taskList List contains all the tasks.
     * @param newTask Task that is being added.
     */
    public void printAddTask(TaskList taskList, Task newTask) {
        String str = "Got it. I've added this task:\n" + newTask + "\nNow you have "
                + taskList.getListOfTasks().size() + " tasks in the list.";

        setPrintStatement(str);
    }

    /**
     * Prints all the tasks in the taskList.
     *
     * @param taskList List contains all the tasks.
     */
    public void printTaskList(TaskList taskList) {
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getListOfTasks().size(); i++) {
            int number = i + 1;
            str += number + "." + taskList.getListOfTasks().get(i) + "\n";
        }
        setPrintStatement(str);
    }

    /**
     * Prints the task that is marked done.
     *
     * @param taskList List contains all the tasks.
     * @param taskNumber ID of the task that will be marked done.
     */
    public void printDoneTask(TaskList taskList, int taskNumber) {
        String str = "Nice! I've marked this task as done:\n"
                + taskList.getListOfTasks().get(taskNumber);
        setPrintStatement(str);
    }

    /**
     * Prints the task that will be deleted.
     *
     * @param taskList List contains all the tasks.
     * @param deletedTask Task that is being deleted.
     */
    public void printDeleteTask(TaskList taskList, Task deletedTask) {
        String str = "Noted. I've removed this task:\n" + deletedTask + "\nNow you have "
                + taskList.getListOfTasks().size() + " tasks in the list.";
        setPrintStatement(str);
    }

    /**
     * Prints the task that contains the keyword of the search word.
     *
     * @param listOfTasks Tasks that contains the search keyword.
     */
    public void printFindTasks(ArrayList<Task> listOfTasks) {
        String str = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            int number = i + 1;
            str += number + "." + listOfTasks.get(i) + "\n";
        }
        setPrintStatement(str);
    }

    /**
     * Prints the list of tasks after performing sorting.
     *
     * @param listOfTasks Tasks that have been sorted.
     * @param sortType Type of the sort that user specified.
     */
    public void printSortTasks(ArrayList<Task> listOfTasks, String sortType) {
        String str = "The list have been sorted according to: " + sortType + "\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            int number = i + 1;
            str += number + "." + listOfTasks.get(i) + "\n";
        }
        setPrintStatement(str);
    }
}