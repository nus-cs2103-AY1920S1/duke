import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a TaskList which manipulates the list of tasks.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class TaskList {
    protected ArrayList<Task> tasklist;
    protected Storage storage;

    /**
     * Creates a TaskList object with the Storage object to be read from or written to.
     * @param storage To be read from or written to.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasklist = storage.readInputFile();
    }

    /**
     * Based on what processed task returns from the Parser, the TaskList would return and act accordingly.
     * If its adding or modifying of tasks, the UI and Storage would be activated. Bye statement would terminate
     * the TaskList object.
     *
     * @param s String from the Storage input file.
     * @return A task string for the UI and Storage to either echo or save.
     */
    public String addTask(String s) {
        String[] task = s.split(" "); // task array with many individual strings
        String statement = "";
        String[] modifiedTask = Parser.processTask(task); // modified task array {[command], [description], [date]}
        assert modifiedTask.length == 3 : "Parser failed to process command correctly";
        final String COMMAND = modifiedTask[0];
        final String DESCRIPTION = modifiedTask[1];
        final String DATE = modifiedTask[2];
        try {
            switch (COMMAND) {
                case "todo":
                    ToDo todo = new ToDo(DESCRIPTION);
                    tasklist.add(todo);
                    statement = "Got it. I've added this task:\n  " + todo + "\nNow you have " + Task.getCurrTotal()
                            + " tasks in the list.";
                    storage.save(tasklist);
                    return statement;
                case "deadline":
                    Deadline deadline = new Deadline(DESCRIPTION, DATE);
                    tasklist.add(deadline);
                    statement = "Got it. I've added this task:\n  " + deadline + "\nNow you have " + Task.getCurrTotal()
                            + " tasks in the list.";
                    storage.save(tasklist);
                    return statement;
                case "event":
                    Event event = new Event(DESCRIPTION, DATE);
                    tasklist.add(event);
                    statement = "Got it. I've added this task:\n  " + event + "\nNow you have " + Task.getCurrTotal()
                            + " tasks in the list.";
                    storage.save(tasklist);
                    return statement;
                case "list":
                    return checkList();
                case "done":
                    statement =  complete(Integer.parseInt(DESCRIPTION));
                    storage.save(tasklist);
                    return statement;
                case "delete":
                    statement = delete(Integer.parseInt(DESCRIPTION));
                    storage.save(tasklist);
                    return statement;
                case "find":
                    return findList(DESCRIPTION);
                case "bye":
                    return "bye";
                case "help":
                    return help();
                default:
                    return COMMAND;
            }
        } catch (IOException ioe) {
            return ioe.getMessage().toString();
        }
    }

    protected String checkList() {
        if (this.tasklist.isEmpty()) {
            return "\u2639 OOPS!!! I'm sorry, but there are no tasks in your list :-(";
        }
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasklist.size(); i++) {
            Task t = this.tasklist.get(i);
            s += ((i+1) + "." + t + "\n");
        }
        return s.substring(0, s.length() - 1);
    }

    protected String findList(String keyword) {
        if (this.tasklist.isEmpty()) {
            return "\u2639 OOPS!!! I'm sorry, but there are no tasks in your list :-(";
        }
        String s = "Here are the matching tasks in your list:\n";
        int idx = 1;
        for (Task t : tasklist) {
            if (t.getDescription().contains(keyword)) {
                s += ("" + idx + t + "\n");
                idx++;
            }
        }
        return s.substring(0, s.length() - 1); // removing the extra "\n"
    }

    protected String complete(int i) {
        if (i > tasklist.size()) {
            return "\u2639 OOPS!!! I'm sorry, but I have no such task in your list :-(";
        }
        Task t = this.tasklist.get(i - 1);
        t.setDone();
        String statement = "Nice! I've marked this task as done:\n  " + t;
        return statement;
    }

    protected String delete(int i) {
        if (i > tasklist.size()) {
            return "\u2639 OOPS!!! I'm sorry, but I have no such task in your list :-(";
        }
        Task t = this.tasklist.remove(i - 1);
        Task.decCurrTotal();
        String statement = "Noted. I've removed this task:\n  " + t + "\nNow you have " + Task.getCurrTotal()
                            + " tasks in the list.";
        return statement;
    }

    protected String help() {
        String logo = " ___     __   ____\n"
                    + "|  __|  / _ \\ |  __ \\\n"
                    + "| |  _  | | | | |  |  | |\n"
                    + "| |_| | | |_| | |  |_| |\n"
                    + "|___/  \\__/ |____/\n";
        String header = "Hello from\n" + logo + "Hello! I'm God\n" + "Here are some tips:\n";
        String task = "To add a task, you begin with either \"todo\", \"deadline\" or \"event\".\nFollowing that, "
                + "you can add the description of the task. (ie, \"todo read a book\".)\nIf your task is a deadline, "
                + "you are required to add after the description, the date of the form \"/by DD/MM/YYYY\" or \"/by DD/M"
                + "M/YYYY HHMM\" (24H format).\nIf your task is an event, you are required to add a date of the form \""
                + "/at DD/MM/YYYY HHMM-HHMM\"(24H format).\nFailure to follow the above format will result in an error "
                + "response from me :-(";
        String check = "To check what tasks I have stored for you enter the word \"list\".";
        String doneDelete = "To mark a task as done or to delete it, enter the word \"done\" or \"delete\" followed by "
                + "a whitespace and the task number to complete the aforementioned action.\n(ie, \"done 2\" to tell me "
                + "to mark task 2 as done.)";
        String search = "To search for a task based on the description, enter the word \"find\" followed by a "
                + "whitespace and the keyword you are looking for.\n(ie, \"find book\" will search for all tasks with "
                + "the word \"book\" in the description. Take note that the keyword is case-sensitive.)";
        String end = "Hope this guide was helpful for you \u263A";
        return header + "\n\n" + task + "\n\n" + check + "\n\n" + doneDelete + "\n\n" + search + "\n\n" + end;
    }
}
