import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    String line = "____________________________________________________________";
    protected Scanner sc;

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
        return sc.nextLine();
    }

    /**
     * Prints greeting message.
     */
    public void printGreeting() {
        String logo = "  .-')   _   .-')      ('-.     _  .-')   .-') _         .-. .-')     ('-.    .-. .-')              \n" +
                " ( OO ).( '.( OO )_   ( OO ).-.( \\( -O ) (  OO) )        \\  ( OO )   ( OO ).-.\\  ( OO )             \n" +
                "(_)---\\_),--.   ,--.) / . --. / ,------. /     '._        ;-----.\\   / . --. / ;-----.\\  ,--.   ,--.\n" +
                "/    _ | |   `.'   |  | \\-.  \\  |   /`. '|'--...__)       | .-.  |   | \\-.  \\  | .-.  |   \\  `.'  / \n" +
                "\\  :` `. |         |.-'-'  |  | |  /  | |'--.  .--'       | '-' /_).-'-'  |  | | '-' /_).-')     /  \n" +
                " '..`''.)|  |'.'|  | \\| |_.'  | |  |_.' |   |  |          | .-. `.  \\| |_.'  | | .-. `.(OO  \\   /   \n" +
                ".-._)   \\|  |   |  |  |  .-.  | |  .  '.'   |  |          | |  \\  |  |  .-.  | | |  \\  ||   /  /\\_  \n" +
                "\\       /|  |   |  |  |  | |  | |  |\\  \\    |  |          | '--'  /  |  | |  | | '--'  /`-./  /.__) \n" +
                " `-----' `--'   `--'  `--' `--' `--' '--'   `--'          `------'   `--' `--' `------'   `--'      ";
        System.out.println(logo);
        String greet = line + "\nHello! My name is Smart Baby~\n What can I do for you?\n" + line;
        System.out.println(greet);
    }

    /**
     * Prints farewell message.
     */
    public void printBye() {
        System.out.println(line + "\nZzz...sleeping time! ~u~\n" + line);
    }

    /**
     * Prints do not understand message.
     */
    public void printOops() {
        System.out.println(line + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
    }

    /**
     * Prints list of tasks in TaskList.
     * @param tasks Task objects in TaskList.
     */
    public void printList(TaskList tasks) {
        System.out.println(line + "\nHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            assert task != null;
            System.out.println((i + 1) + "." + task.toString());
        }
        System.out.println(line);
    }

    /**
     * Prints message for tasks that are done.
     * @param task task object that has just been completed.
     */
    public void printDone(Task task) {
        System.out.println(line + "\nNice! I've marked this task as done:\n"
                + "[" + task.getStatusIcon() + "]" + task.getDescription() + "\n" + line);
    }

    /**
     * Prints message after task has been added to TaskList.
     * @param task new task object that has been added.
     * @param sizeOfTaskList size of TaskList.
     */
    public void printAddTask(Task task, int sizeOfTaskList) {
        System.out.println(line + "\nGot it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + sizeOfTaskList + " tasks in the list.\n" + line);
    }

    /**
     * Prints message after task has been deleted from TaskList.
     * @param deletedTask deleted Task.
     * @param sizeOfTaskList size of taskList.
     */
    public void printDelete(Task deletedTask, int sizeOfTaskList) {
        System.out.println(line + "\nNoted. I've removed this task: \n" + deletedTask.toString() + "\n"
                + "Now you have " + (sizeOfTaskList - 1) + " tasks in the list.\n" + line);
    }

    /**
     * Prints matching tasks in list.
     * @param findList list of matches.
     */
    public void printFind(TaskList findList) {
        System.out.println(line + "\nHere are the matching tasks in your list:");
        for (int i = 0; i < findList.size(); i++) {
            Task task = findList.get(i);
            System.out.println((i+1) + "." + task.toString());
        }
        System.out.println(line);
    }

    /**
     * Prints correct format for event input.
     */
    public void printEventFormat() {
        System.out.println(line + "\nDoesn't match the event format. Please use /at dd/mm/yyyy 0000 (in 24hr).\n" + line);
    }

    /**
     * Prints correct format for deadline input.
     */
    public void printDeadlineFormat() {
        System.out.println(line + "\nDoesn't match the deadline format. Please use /by dd/mm/yyyy 0000 (in 24hr).\n" + line);
    }

    /**
     * Prints error messages if task description is empty or cannot be understood.
     * @param taskType
     * @throws DukeException
     */
    public void throwErrorMessage(String taskType) throws DukeException {
        if (Stream.of("delete", "done", "todo", "deadline", "event").anyMatch(s -> taskType.equals(s))) {
            throw new DukeException(line + "\nOOPS!!! The description of a " + taskType + " cannot be empty.\n" + line);
        } else {
            throw new DukeException(line + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
        }
    }
}
