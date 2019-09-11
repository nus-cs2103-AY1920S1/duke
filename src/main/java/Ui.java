import java.util.Scanner;
import java.util.stream.Stream;
import javafx.scene.control.TextField;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static String line = "______________________________________________________\n";
    protected Scanner sc;
    private TextField userInput;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.sc = sc;
    }

    /**
     * Reads command from user input.
     * @return string of user input.
     */
    public String readCommand() {
        sc = new Scanner(System.in);
        userInput = new TextField();
        return userInput.getText();
    }

    /**
     * Prints greeting message.
     */
    public static String printGreeting() {
        String greet = line
                + " Hello! My name is Smart Baby~\n"
                + " ≧◡≦ What can I do for you?\n"
                + line;
        return greet;
    }

    /**
     * Prints farewell message.
     */
    public String printBye() {
        String bye = line
                + "Zzz...sleeping time! ~u~\n"
                + line;
        return bye;
    }

    /**
     * Prints do not understand message.
     */
    public String printOops() {
        String oops = line
                + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + line;
        return oops;
    }

    /**
     * Prints list of tasks in TaskList.
     * @param tasks Task objects in TaskList.
     */
    public String printList(TaskList tasks) {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            assert t != null;
            taskList = taskList + (i + 1) + "." + t.toString() + "\n";
        }
        return line
                + "Here are the tasks in your list:\n"
                + taskList
                + line;
    }

    /**
     * Prints message for tasks that are done.
     * @param task task object that has just been completed.
     */
    public String printDone(Task task) {
        return line
                + "Nice! I've marked this task as done:\n"
                + "[" + task.getStatusIcon() + "]" + task.getDescription() + "\n"
                + line;
    }

    /**
     * Prints message after task has been added to TaskList.
     * @param task new task object that has been added.
     * @param sizeOfTaskList size of TaskList.
     */
    public String printAddTask(Task task, int sizeOfTaskList) {
        return line
                + "Got it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + sizeOfTaskList + " tasks in the list.\n"
                + line;
    }

    /**
     * Prints message after task has been deleted from TaskList.
     * @param deletedTask deleted Task.
     * @param sizeOfTaskList size of taskList.
     */
    public String printDelete(Task deletedTask, int sizeOfTaskList) {
        return line + "Noted. I've removed this task: \n"
                + deletedTask.toString() + "\n"
                + "Now you have " + (sizeOfTaskList - 1) + " tasks in the list.\n"
                + line;
    }

    /**
     * Prints matching tasks in list.
     * @param findList list of matches.
     */
    public String printFind(TaskList findList) {
        String taskList = "";
        for (int i = 0; i < findList.size(); i++) {
            Task task = findList.get(i);
            taskList = taskList + (i+1) + "." + task.toString() + "\n";
        }
        return line
                + "Here are the matching tasks in your list:\n"
                + taskList
                + line;
    }

    /**
     * Prints correct format for event input.
     */
    public String printEventFormat() {
        return line + "Doesn't match the event format. Please use /at dd/mm/yyyy 0000 (in 24hr).\n" + line;
    }

    /**
     * Prints correct format for deadline input.
     */
    public String printDeadlineFormat() {
        return line + "Doesn't match the deadline format. Please use /by dd/mm/yyyy 0000 (in 24hr).\n" + line;
    }

    /**
     * Prints error messages if task description is empty or cannot be understood.
     * @param taskType
     * @throws DukeException
     */
    public String throwErrorMessage(String taskType) throws DukeException {
        if (Stream.of("delete", "done", "todo", "deadline", "event").anyMatch(s -> taskType.equals(s))) {
            throw new DukeException(line + "\nOOPS!!! The description of a " + taskType + " cannot be empty.\n" + line);
        } else {
            throw new DukeException(line + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
        }
    }

    /**
     * String representation of the error message.
     * @param error
     */
    public String printErrorMessage(String error) {
        return "OOPS!!! " + error + "\n";
    }
}
