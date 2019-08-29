import java.util.ArrayList;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import duke.exception.DukeException;

/**
 * Deals with the storage and manipulation of tasks.
 */
public class TaskList {

    private Duke parent;
    private ArrayList<Task> taskList;

    public TaskList(Duke parent) {
        this.parent = parent;
    }

    /**
     * Sets the task list to the input list.
     *
     * @param taskList The task list to be used by Duke.
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index ID of the task.
     * @return The corresponding task.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * The number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a Task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task from the specified index.
     *
     * @param index The index from which a task should be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Deletes the task specified by ID.
     *
     * @param input The Delete command
     * @throws DukeException If the Delete command is invalid.
     */
    public void deleteTask(String input) throws DukeException {
        String[] tokens = input.split("\\s+");
        if (tokens.length != 2) {
            throw new DukeException("OOPS!!! Invalid delete command.");
        } else {
            try {
                int taskNumber = Integer.parseInt(tokens[1]);
                Task removedTask = taskList.get(taskNumber - 1);
                taskList.remove(taskNumber - 1);
                String message = "Noted. I've deleted this task:\n"
                        + "  " + removedTask.toString()
                        + "\n" + parent.getNumberOfTasks();
                parent.print(message);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Please enter a valid number after delete.");
            }
        }
    }

    /**
     * Adds the specified task to the list of tasks.
     *
     * @param description The description of the task.
     */
    public void addTask(String description) {
        String[] tokens = description.split("\\s+");
        String taskType = tokens[0];
        //System.out.println(taskType);
        try {
            if (taskType.equalsIgnoreCase("Event")) {
                addEvent(description);
            } else if (taskType.equalsIgnoreCase("Todo")) {
                addTodo(description);
            } else if (taskType.equalsIgnoreCase("Deadline")) {
                addDeadline(description);
            } else {
                parent.print("Invalid task!");
            }
        } catch (DukeException e) {
            parent.print(e.getMessage());
        }
    }

    /**
     * Adds an Event to the list of tasks.
     *
     * @param description Description of the event.
     * @throws DukeException If the description is invalid.
     */
    public void addEvent(String description) throws DukeException {
        int indexOfAt = description.indexOf("/at");
        if (indexOfAt == -1) {
            throw new DukeException("OOPS!!! The event description must contain a time following \"/at\"");
        }
        String desc = description.substring(5, indexOfAt).strip();
        String at = description.substring(indexOfAt + 3).strip();
        if (!Date.verifyDateTimeEvent(at)) {
            parent.print("Invalid format! Please stick to DD/MM/YYYY HHMM-HHMM");
        } else {
            taskList.add(new Event(desc, at));
            parent.printTaskAdded();
        }
    }

    /**
     * Adds a "To do" to the task list.
     *
     * @param description Description of the To do task.
     * @throws DukeException If the description is invalid.
     */
    public void addTodo(String description) throws DukeException {
        String[] tokens = description.split("\\s+");
        StringBuilder desc = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            desc.append(tokens[i] + " ");
        }
        String newDescription = desc.toString().strip();
        taskList.add(new Todo(newDescription));
        parent.printTaskAdded();
    }

    /**
     * Adds a deadline to the task list.
     *
     * @param description Description of the deadline.
     * @throws DukeException If description is invalid.
     */
    public void addDeadline(String description) throws DukeException {
        int indexOfBy = description.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DukeException("OOPS!!! The deadline description must contain a time following \"/by\"");
        }
        String desc = description.substring(8, indexOfBy).strip();
        String by = description.substring(indexOfBy + 3).strip();
        if (!Date.verifyDateTimeDeadline(by)) {
            parent.print("Invalid time format! Please stick to DD/MM/YYYY HHMM");
        } else {
            taskList.add(new Deadline(desc, by));
            parent.printTaskAdded();
        }
    }
}


