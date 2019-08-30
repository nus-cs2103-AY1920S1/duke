import java.text.ParseException;
import java.util.ArrayList;

/**
 * Represents an interface for user interaction.
 */
public class Ui {

    private String line = "    ____________________________________________________________";

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private void formatPrint(String[] lists) {
        System.out.println(line);
        for (String s : lists) {
            System.out.println("     " + s);
        }
        System.out.println(line);
    }

    private void formatPrint(String s) {
        System.out.println(line);
        System.out.println("     " + s);
        System.out.println(line);
    }

    private String format(String s) {
        return "     " + s;
    }

    private void echo(String s) {
        formatPrint(s);
    }

    /**
     * Sends greetings to user.
     */
    public void greet() {
        formatPrint(new String[]{"Hello! I'm Duke", "What can I do for you?"});
    }

    /**
     * Says goodbye to user.
     */
    public void bye() {
        formatPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all tasks.
     * @param list a TaskList object that contains a list of tasks
     * @throws DukeException if description of tasks cannot be parsed
     */
    public void printList(TaskList list) throws DukeException {
        ArrayList<Task> tasks = list.tasks;
        if (tasks.size() == 0) {
            throw new DukeException("There is no task in your list");
        }
        try {
            System.out.println(line);
            System.out.println(format("Here are the tasks in your list:"));
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                System.out.println("  " + format(i + 1 + "." + t.repr()));
            }
            System.out.println(line);
        } catch (ParseException e) {
            throw new DukeException("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
        }
    }

    /**
     * Tells user a task has been marked as done.
     * @param list a TaskList object that contains a list of tasks
     * @param num number of task to be marked as done
     * @throws ParseException if description of task connot be parsed
     */
    public void done(TaskList list, int num) throws ParseException {
        ArrayList<Task> tasks = list.tasks;
        String[] listToPrint = {"Nice! I've marked this task as done: ", "  " + tasks.get(num - 1).repr()};
        formatPrint(listToPrint);
    }

    /**
     * Tells user a task has been deleted.
     * @param list a TaskList object that contains a list of tasks
     * @param num number of task to be deleted
     * @throws ParseException if description of task connot be parsed
     */
    public void delete(TaskList list, int num) throws ParseException {
        ArrayList<Task> tasks = list.tasks;
        Task t = tasks.get(num - 1);
        System.out.println(line);
        System.out.println(format("Noted. I've removed this task: "));
        System.out.println("  " + format(t.repr()));
        switch (tasks.size()) {
            case 0:
                System.out.println(format("Now you have no task in the list."));
                break;
            case 1:
                System.out.println(format("Now you have 1 task in the list."));
                break;
            default:
                System.out.println(format("Now you have " + tasks.size() + " tasks in the list."));
                break;
        }
        System.out.println(line);
    }

    /**
     * Tells user a task has been added.
     * @param list a TaskList object that contains a list of tasks
     * @throws DukeException if description of task connot be parsed
     */
    public void add(TaskList list) throws DukeException {
        ArrayList<Task> tasks = list.tasks;
        try {
            Task newTask = tasks.get(tasks.size() - 1);
            String str = format(newTask.repr());
            System.out.println(line);
            System.out.println(format("Got it. I've added this task: "));
            System.out.println("  " + str);
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | ParseException ex) {
            throw new DukeException("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
        }
        if (tasks.size() == 1) {
            System.out.println(format("Now you have 1 task in the list."));
        } else {
            System.out.println(format("Now you have " + tasks.size() + " tasks in the list."));
        }
        System.out.println(line);
    }


    /** Prints matching list.
     * @param targets a TaskList of matching tasks
     * @throws DukeException if there is no matching task or task description cannot be parsed
     */
    public void printFind(TaskList targets) throws DukeException {
        ArrayList<Task> tasks = targets.tasks;
        if (tasks.size() == 0) {
            throw new DukeException("There is no matching task in your list");
        }
        try {
            System.out.println(line);
            System.out.println(format("Here are the matching tasks in your list:"));
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                System.out.println("  " + format(i + 1 + "." + t.repr()));
            }
            System.out.println(line);
        } catch (ParseException e) {
            throw new DukeException("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
        }
    }

    /**
     * Tells user tasks connot be loaded from hard disk.
     */
    public void showLoadingError() {
        formatPrint("Cannot load files");
    }

    /**
     * Tells user a DukeException has occurred.
     * @param ex
     */
    public void showDukeException(DukeException ex) {
        formatPrint(ex.getMessage());
    }

    /**
     * Tells user a ParseError has occurred.
     */
    public void showParseError() {
        formatPrint("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
    }

}