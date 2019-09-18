package duke.tasklist;

import java.util.ArrayList;
import java.time.LocalDateTime;

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
        tasks = new ArrayList<>();
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
     * Carry out operations according to commands from user and returns the output.
     *
     * @param command command object with input string and command
     * @return resultant command with updated task list
     * @throws DukeException
     */
    public Command doCommand(Command command) throws DukeException {
        String input = command.getInput();
        String output = null;
        switch (command.getComd()) {
            case BYE:
                output = byeCommand();
                break;
            case LIST:
                output = listCommand();
                break;
            case DONE:
                output = doneCommand(input);
                break;
            case DELETE:
                output = deleteCommand(input);
                break;
            case TODO:
                output = toDoCommand(input);
                break;
            case DEADLINE:
                output = deadlineCommand(input);
                break;
            case EVENT:
                output = eventCommand(input);
                break;
            case FIND:
                output = findCommand(input);
                break;
            case NULL:
                throw new DukeException("Wrong input.");
        }
        output += "\n";
        command.setOutput(output);
        command.setTaskList(tasks);
        return command;
    }

    /**
     * Returns a list of upcoming events and deadlines.
     *
     * @return reminders of a list of events/deadlines
     */
    public String getReminder() {
        LocalDateTime now = LocalDateTime.now();
        ArrayList<Task> remindTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDateTime deadlineDate = deadline.getDateTime();
                if (now.isBefore(deadlineDate)) {
                    if (!deadline.isDone()) {
                        remindTasks.add(task);
                    }
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                LocalDateTime eventDate = event.getDateTime();
                if (now.isBefore(eventDate)) {
                    remindTasks.add(task);
                }
            }
        }
        String s;
        if (remindTasks.isEmpty()) {
            s = "nil";
        } else {
            s = "Here's a list of upcoming tasks:\n";
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < remindTasks.size(); i++) {
                sb.append(i + 1).append(".").append(remindTasks.get(i));
                if (i < remindTasks.size() - 1) {
                    sb.append("\n");
                }
            }
            s = sb.toString();
        }
        return s;
    }

    private String byeCommand() {
        return "See you again.";
    }

    private String listCommand() {
        String s = "Here are the tasks in your list:\n";
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private String doneCommand(String input) throws DukeException {
        String[] s = input.split("\\s");
        if (s.length <= 1) {
            throw new DukeException("Task number cannot be empty.\n");
        }
        int n = Integer.parseInt(s[1]) - 1;
        if (n < 0 || n > tasks.size() - 1) {
            throw new DukeException("Task number is not on the list.\n");
        }
        tasks.get(n).markAsDone();
        return "Nice! I've marked this task as done: \n" + tasks.get(n);
    }

    private String deleteCommand(String input) throws DukeException {
        String[] s = input.split("\\s");
        if (s.length <= 1) {
            throw new DukeException("Task number cannot be empty.\n");
        }
        int n = Integer.parseInt(s[1]) - 1;
        if (n < 0 || n > tasks.size() - 1) {
            throw new DukeException("Task number is not in the list.\n");
        }
        String output = "Noted. I've removed this task: \n" + tasks.get(n) +
                "\nNow you have " + (tasks.size() - 1) + " tasks in the list.";
        tasks.remove(n);
        return output;
    }

    private String toDoCommand(String input) throws DukeException {
        String[] s = input.split("\\s");
        if (s.length <= 1) {
            throw new DukeException("The description cannot be empty.\n");
        }
        Task task = new ToDo(input.substring(5));
        tasks.add(task);
        return "Got it. I've added this task: \n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    private String deadlineCommand(String input) throws DukeException {
        String[] s = input.split("\\s");
        if (s.length <= 1) {
            throw new DukeException("The description cannot be empty.\n");
        }
        if (!input.contains("/by")) {
            throw new DukeException("Missing /by.\n");
        }
        String desc = input.substring(9, input.indexOf('/') - 1);
        String date = input.substring(input.indexOf("/by") + 4);
        Task task = new Deadline(desc, date);
        tasks.add(task);
        return "Got it. I've added this task: \n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    private String eventCommand(String input) throws DukeException {
        String[] s = input.split("\\s");
        if (s.length <= 1) {
            throw new DukeException("The description cannot be empty.\n");
        }
        if (!input.contains("/at")) {
            throw new DukeException("Missing /at.\n");
        }
        String desc = input.substring(6, input.indexOf('/') - 1);
        String date = input.substring(input.indexOf("/at") + 4);
        Task task = new Event(desc, date);
        tasks.add(task);
        return "Got it. I've added this task: \n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    private String findCommand(String input) throws DukeException {
        String[] s = input.split("\\s");
        if (s.length <= 1) {
            throw new DukeException("The description cannot be empty.\n");
        }
        ArrayList<Task> foundTasks = new ArrayList<>();
        String search = input.substring(5).toLowerCase();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(search)) {
                foundTasks.add(t);
            }
        }
        StringBuilder sb;
        if (foundTasks.isEmpty()) {
            sb = new StringBuilder("There are no matching tasks.");
        } else {
            sb = new StringBuilder("These are the found tasks:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                sb.append(i + 1).append(".").append(foundTasks.get(i));
                if (i < foundTasks.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
}
