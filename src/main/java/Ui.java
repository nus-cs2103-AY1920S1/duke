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
        String oops = "(ヾﾉ꒪ཫ꒪ ) OOPS!!! I'm sorry, but I don't know what that means\n"
                + "Type 'help' to see what I can do for you. (｡◕‿◕｡)";
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
        return "(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ Here are the tasks in your list:\n"
                + taskList;
    }

    /**
     * Prints message for tasks that are done.
     * @param task task object that has just been completed.
     */
    public String printDone(Task task) {
        return "´ ▽ ` )ﾉ Nice! I've marked this task as done:\n"
                + "[" + task.getStatusIcon() + "]" + task.getDescription() + "\n";
    }

    /**
     * Prints message after task has been added to TaskList.
     * @param task new task object that has been added.
     * @param sizeOfTaskList size of TaskList.
     */
    public String printAddTask(Task task, int sizeOfTaskList) {
        return "ಥ◡ಥ Got it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + sizeOfTaskList + " tasks in the list.\n";
    }

    /**
     * Prints message after task has been deleted from TaskList.
     * @param deletedTask deleted Task.
     * @param sizeOfTaskList size of taskList.
     */
    public String printDelete(Task deletedTask, int sizeOfTaskList) {
        return "(¬‿¬) Noted. I've removed this task: \n"
                + deletedTask.toString() + "\n"
                + "Now you have " + (sizeOfTaskList - 1) + " tasks in the list.\n";
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
        return "இ~இ Here are the matching tasks in your list:\n"
                + taskList;
    }

    /**
     * Prints correct format for event input.
     */
    public String printEventFormat() {
        return " (;´･д･`) Doesn't match the event format.\n Please use /at dd/mm/yyyy 0000 (in 24hr).\n";
    }

    /**
     * Prints correct format for deadline input.
     */
    public String printDeadlineFormat() {
        return " (. ﾟーﾟ) Doesn't match the deadline format.\n Please use /by dd/mm/yyyy 0000 (in 24hr).\n";
    }

    /**
     * Prints error messages if task description is empty or cannot be understood.
     * @param taskType
     * @throws DukeException
     */
    public String throwErrorMessage(String taskType) throws DukeException {
        if (Stream.of("delete", "done", "todo", "deadline", "event").anyMatch(s -> taskType.equals(s))) {
            throw new DukeException("∑(゜Д゜;) OOPS!!! The description of a " + taskType + " cannot be empty.\n");
        } else {
            throw new DukeException("_(꒪ཀ꒪」∠)_ OOPS!!! I'm sorry, but I don't know what that means.\n");
        }
    }

    /**
     * String representation of the error message.
     * @param error
     */
    public String printErrorMessage(String error) {
        return "OOPS!!! " + error + "\n";
    }

    public String printHelp() {
        String help = "Come, lemme teachu baby talk (●´ω｀●):\n"
                + "list - returns current tasks in task list.\n"
                + "done [task number] - mark task as done.\n"
                + "delete [task number] - delete task from task list.\n"
                + "todo [description] - insert a todo task into task list.\n"
                + "deadline [description] /by [dd/MM/yyyy] [0000] - insert a deadline task into task list.\n"
                + "event [description] /at [dd/MM/yyyy] [0000] - insert an event into task list.\n"
                + "find [keyword] - find task(s) containing keyword in task list.\n";
        return help;
    }
}
