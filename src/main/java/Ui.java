import java.util.Scanner;
import java.util.stream.Stream;

import javafx.scene.control.TextField;

/**
 * Deals with interactions with the user.
 */
public class Ui {
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
     *
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
        String greet = " Hello! My name is Smart Baby~\n"
                + " ≧◡≦ What can I do for you?\n"
                + " To see my baby powers, type 'help'\n";
        return greet;
    }

    /**
     * Prints farewell message.
     */
    public String printBye() {
        String bye = "Zzz...sleeping time! ≖‿≖\n";
        return bye;
    }

    /**
     * Prints do not understand message.
     */
    public String printOops() {
        String oops = "ಠ_ಠ OOPS!!! I'm sorry, but I don't know what that means.\n"
                + "Type 'help' to see what I can do for you. (｡◕‿◕｡)";
        return oops;
    }

    /**
     * Prints list of tasks in TaskList.
     *
     * @param tasks Task objects in TaskList.
     */
    public String printList(TaskList tasks) {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            assert t != null;
            taskList = taskList + (i + 1) + "." + t.toString() + "\n";
        }
        return "(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ Here are the task(s) in your list:\n"
                + taskList;
    }

    /**
     * Prints message for tasks that are done.
     *
     * @param task task object that has just been completed.
     */
    public String printDone(Task task) {
        return "´ ▽ ` )ﾉ Nice! I've marked this task as done:\n"
                + "[" + task.getStatusIcon() + "]" + task.getDescription() + "\n";
    }

    /**
     * Prints message after task has been added to TaskList.
     *
     * @param task           new task object that has been added.
     * @param sizeOfTaskList size of TaskList.
     */
    public String printAddTask(Task task, int sizeOfTaskList) {
        return "ಥ◡ಥ Got it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + sizeOfTaskList + " tasks in the list.\n";
    }

    /**
     * Prints message after task has been deleted from TaskList.
     *
     * @param deletedTask    deleted Task.
     * @param sizeOfTaskList size of taskList.
     */
    public String printDelete(Task deletedTask, int sizeOfTaskList) {
        return "(¬‿¬) Noted. I've removed this task: \n"
                + deletedTask.toString() + "\n"
                + "Now you have " + (sizeOfTaskList - 1) + " tasks in the list.\n";
    }

    /**
     * Prints matching tasks in list.
     *
     * @param findList list of matches.
     */
    public String printFind(TaskList findList) {
        String taskList = "";
        for (int i = 0; i < findList.size(); i++) {
            Task task = findList.get(i);
            taskList = taskList + (i + 1) + "." + task.toString() + "\n";
        }
        return "இ~இ Here are the matching task(s) in your list:\n"
                + taskList;
    }

    /**
     * Prints correct format for event input.
     */
    public String printEventFormat() {
        return " (;´･д･`) Doesn't match the event format.\n"
                + "Please use \"event ... /at dd/mm/yyyy 0000\" (in 24hr).\n";
    }

    /**
     * Prints correct format for deadline input.
     */
    public String printDeadlineFormat() {
        return " (. ﾟーﾟ) Doesn't match the deadline format.\n"
                + "Please use \"deadline ... /by dd/mm/yyyy 0000\" (in 24hr).\n";
    }

    /**
     * Prints error messages if task description is empty or cannot be understood.
     *
     * @param taskType
     */
    public String throwErrorMessage(String taskType) {
        switch (taskType) {
        case "todo":
            return "∑(゜Д゜;) Description of a todo can't be empty.\n";
        case "event":
            return "∑(゜Д゜;) Description of an event can't be empty.\n";
        case "deadline":
            return "∑(゜Д゜;) Description of a deadline can't be empty.\n";
        case "done":
            return "I've done nothing productive all day...you too? (⊃◜⌓◝⊂)\n";
        case "delete":
            return "One does not simply delete nothing.(￣ ︶ ￣;)\n";
        default:
            return "ಠ_ಠ OOPS!!! I'm sorry, but I don't know what that means.\n";
        }
    }

    public String printHelp() {
        String help = "Come, lemme teachu some baby talk (●´ω｀●):\n"
                + "todo - to add a todo task to the list.\n"
                + "event - to add an event with date and time to the list.\n"
                + "deadline - to add a task with deadline to the list.\n"
                + "list - to see items on the list.\n"
                + "done - to mark a completed task as done.\n"
                + "delete - to remove a task from the list.\n"
                + "find - to see item(s) containing the keyword from the list.\n"
                + "bye - to end the session.";
        return help;
    }
}
