package duke.tasklist;

import java.util.ArrayList;

import duke.DukeException;
import duke.command.Command;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Class for operations to task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Default constructor for TaskList. Used in case of unable to locate file.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param tasks ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Carry out operations according to commands from user.
     *
     * @param command command object with input string and command
     * @return resultant command with updated task list
     * @throws DukeException
     */
    public Command doCommand(Command command) throws DukeException {
        String input = command.getInput();
        String[] s = input.split("\\s");
        String output = null;
        String desc = null;
        String date = null;
        int n;
        Task task;
        switch (command.getComd()) {
            case BYE:
                output = "See you again.";
                break;
            case LIST:
                output = "Here are the tasks in your list:\n";
                for (int i = 0; i < tasks.size(); i++) {
                    output += (i + 1) + "." + tasks.get(i);
                    if (i < tasks.size() - 1) {
                        output += "\n";
                    }
                }
                break;
            case DONE:
                if (s.length <= 1) {
                    throw new DukeException("Task number cannot be empty.\n");
                }
                n = Integer.parseInt(s[1]) - 1;
                if (n < 0 || n > tasks.size() - 1) {
                    throw new DukeException("Task number is not in the list.\n");
                }
                tasks.get(n).markAsDone();
                output = "Nice! I've marked this task as done: \n" + tasks.get(n);
                break;
            case DELETE:
                if (s.length <= 1) {
                    throw new DukeException("Task number cannot be empty.\n");
                }
                n = Integer.parseInt(s[1]) - 1;
                if (n < 0 || n > tasks.size() - 1) {
                    throw new DukeException("Task number is not in the list.\n");
                }
                output = "Noted. I've removed this task: \n" + tasks.get(n) +
                        "\nNow you have " + (tasks.size() - 1) + " tasks in the list.";
                tasks.remove(n);
                break;
            case TODO:
                if (s.length <= 1) {
                    throw new DukeException("The description cannot be empty.\n");
                }
                task = new ToDo(input.substring(5));
                tasks.add(task);
                output = "Got it. I've added this task: \n" + task +
                        "\nNow you have " + tasks.size() + " tasks in the list.";
                break;
            case DEADLINE:
                if (s.length <= 1) {
                    throw new DukeException("The description cannot be empty.\n");
                }
                if (!input.contains("/by")) {
                    throw new DukeException("Missing /by.\n");
                }
                desc = input.substring(9, input.indexOf('/') - 1);
                date = input.substring(input.indexOf("/by") + 4);
                task = new Deadline(desc, date);
                tasks.add(task);
                output = "Got it. I've added this task: \n" + task +
                        "\nNow you have " + tasks.size() + " tasks in the list.";
                break;
            case EVENT:
                if (s.length <= 1) {
                    throw new DukeException("The description cannot be empty.\n");
                }
                if (!input.contains("/at")) {
                    throw new DukeException("Missing /at.\n");
                }
                desc = input.substring(6, input.indexOf('/') - 1);
                date = input.substring(input.indexOf("/at") + 4);
                task = new Event(desc, date);
                tasks.add(task);
                output = "Got it. I've added this task: \n" + task +
                        "\nNow you have " + tasks.size() + " tasks in the list.";
                break;
            case FIND:
                if (s.length <= 1) {
                    throw new DukeException("The description cannot be empty.\n");
                }
                ArrayList<Task> foundTasks = new ArrayList<Task>();
                String search = input.substring(5).toLowerCase();
                for (Task t : tasks) {
                    if (t.getDescription().toLowerCase().contains(search)) {
                        foundTasks.add(t);
                    }
                }
                if (foundTasks.isEmpty()) {
                    output = "There are no matching tasks.";
                } else {
                    output = "These are the found tasks:\n";
                    for (int i = 0; i < foundTasks.size(); i++) {
                        output += (i + 1) + "." + foundTasks.get(i);
                        if (i < foundTasks.size() - 1) {
                            output += "\n";
                        }
                    }
                }
                break;
            case NULL:
                throw new DukeException("Wrong input.");
        }
        output += "\n";
        command.setOutput(output);
        command.setTaskList(tasks);
        return command;
    }
}
