package duke.ui;

import duke.date.DateTime;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints greeting message to user on application launch.
     */
    public String welcomeMsg() {
        String result = "";
        String line = "___________________________________________________\n";
        String greetMsg = "Hello! I'm Spongebob\nWhat can I do for you?\n";
        result = line + greetMsg + line;
        return result;
    }

    /**
     * Prints exit message and exists the application.
     */
    public static String handleBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out current lists of tasks.
     * @param input User input
     * @param tasks List of tasks
     * @throws DukeException When list command is not input correctly
     */
    public static String handleList(String input, TaskList tasks) throws DukeException {
        String result = "";
        String[] inputStringArr = input.split(" ");
        if (inputStringArr.length > 1) {
            throw new DukeException(":( OOPS!!! The list command should not be followed by other "
                    + "text!");
        } else {
            result += "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                int listNum = i + 1;
                Task t = tasks.get(i);
                if (t.getType().equals("todo")) {
                    result += "       " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                            + t.toString() + '\n';
                } else if (t.getType().equals("event")) {
                    result += "       " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                            + t.toString() + " (at: " + t.getDate() + ")" + '\n';
                } else {
                    result += "       " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                            + t.toString() + " (by: " + t.getDate() + ")" + '\n';
                }
            }
        }
        return result;
    }

    /**
     * Print message that a task has been marked as completed.
     * @param input User input
     * @param tasks List of tasks
     * @param storage Update tasks stored persistently
     * @throws DukeException When task has already been marked done or number provided not in range
     */
    public static String handleDone(String input, TaskList tasks, Storage storage) throws DukeException {
        String result = "";
        String[] inputStringArr = input.split(" ");
        if (inputStringArr.length > 1) {
            int taskNum = Integer.parseInt(inputStringArr[1]);
            int totalTasks = tasks.size();
            if (taskNum < 1 || taskNum > totalTasks) {
                throw new DukeException(":( OOPS!!! The number provided is not within the range of the "
                        + "list.");
            } else {
                Task t = tasks.get(taskNum - 1);
                boolean isDone = t.getStatus();
                if (isDone) {
                    throw new DukeException(":( OOPS!!! The task has already been marked as completed.");
                } else {
                    t.setDone();
                    result += "Nice! I've marked this task as done:\n" + "       [" + '+' + "] " + t;
                    storage.rewriteFile(tasks);
                }
            }
        } else {
            throw new DukeException(":( OOPS!!! Please specify the completed task's number.");
        }
        return result;
    }

    /**
     * When user adds a Todo class task.
     * @param input User input
     * @param tasks List of tasks
     * @param storage Update tasks stored persistently
     * @throws DukeException When task description given by user is empty
     */
    public static String handleTodo(String input, TaskList tasks, Storage storage) throws DukeException {
        String result = "";
        String[] inputStringArr = input.split(" ");
        if (inputStringArr.length > 1) {
            String taskName = ((input.split(" ", 2))[1]).strip();
            Todo t = new Todo(taskName);
            assert t != null : "t should not be null";
            tasks.add(t);
            result += "Got it. I've added this task:\n" + "       [T]" + "[ ]" + ' ' + t + '\n'
                    + "     Now you have " + tasks.size() + " tasks in the list.";
            storage.appendToFile("T | 0 | " + taskName + '\n');
        } else {
            throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
        }
        return result;
    }

    /**
     * When user adds a Deadline class task.
     * @param input User input
     * @param tasks List of tasks
     * @param storage Update tasks stored persistently
     */
    public static String handleDeadline(String input, TaskList tasks, Storage storage) {
        String result = "";
        String[] separateTaskDate = input.split("/", 2);
        String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
        String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
        DateTime dt = new DateTime(date);
        Deadline d = new Deadline(taskName, dt.toString());
        assert d != null : "d should not be null";
        tasks.add(d);
        result += "Got it. I've added this task:\n" + "       [D][ ] " + d + " (by: " + dt + ")\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        storage.appendToFile("D | 0 | " + taskName + " | " + dt.toString() + '\n');
        return result;
    }

    /**
     * When user adds an Event class task.
     * @param input User input
     * @param tasks List of tasks
     * @param storage Update tasks stored persistently
     */
    public static String handleEvent(String input, TaskList tasks, Storage storage) {
        String result = "";
        String[] separateTaskDate = input.split("/", 2);
        String taskName = ((separateTaskDate[0].split(" ", 2))[1]).strip();
        String date = ((separateTaskDate[1].split(" ", 2))[1]).strip();
        DateTime dt = new DateTime(date);
        Event e = new Event(taskName, dt.toString());
        assert e != null : "e should not be null";
        tasks.add(e);
        result += "Got it. I've added this task: \n" + "       [E][ ] " + e + " (at: " + dt + ")\n"
                + "     Now you have " + tasks.size() + " tasks in the list.";
        storage.appendToFile("E | 0 | " + taskName + " | " + date + '\n');
        return result;
    }

    /**
     * When user deletes a task from the task list.
     * @param input User input
     * @param tasks List of tasks
     * @param storage Update tasks stored persistently
     */
    public static String handleDelete(String input, TaskList tasks, Storage storage) {
        String result = "";
        String[] inputStringArr = input.split(" ");
        int taskNum = Integer.parseInt(inputStringArr[1]);
        Task t = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        result += "Noted. I've removed this task:\n";
        if (t.getType().equals("todo")) {
            result += "       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t +'\n';
        } else if (t.getType().equals("event")) {
            result += "       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + " (at: "
                    + t.getDate() + ")\n";
        } else {
            result += "       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + " (by: "
                    + t.getDate() + ")\n";
        }
        result += "Now you have " + tasks.size() + " tasks in the list.";
        storage.rewriteFile(tasks);
        return result;
    }

    /**
     * When user finds a task using a keyword.
     * @param input Input from user
     * @param tasks List of tasks
     */
    public static String handleFind(String input, TaskList tasks) {
        String result = "";
        String[] inputStringArr = input.split(" ", 2);
        String searchTerm = inputStringArr[1];
        result += "Here are the matching tasks in your list:";
        int listNum = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String taskName = t.toString();
            if (taskName.contains(searchTerm)) {
                if (t.getType().equals("todo")) {
                    result += "     " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t + '\n';
                } else if (t.getType().equals("event")) {
                    result += "     " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                            + t + " " + "(at: " + t.getDate() + ")" + '\n';
                } else {
                    result += "     " + listNum + "." + t.getTypeIcon() + '[' + t.getStatusIcon() + "] "
                            + t + " (by: " + t.getDate() + ")" + '\n';
                }
                listNum++;
            }
        }
        return result;
    }
}
