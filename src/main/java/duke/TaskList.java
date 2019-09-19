package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class to store and handle task list commands.
 */
public class TaskList {
    private LinkedList<Task> lst;

    /**
     * Creates tasks from input LinkedList and adds them to a task list.
     * If input list is invalid, an empty task list will be created instead.
     *
     * @param list Input list of tasks
     * @throws DukeException Invalid instantiation of tasks
     */
    public TaskList(LinkedList<String> list) throws DukeException {
        lst = new LinkedList<>();
        for (String s : list) {
            String[] arr = s.split("\\|");
            String task = arr[0].trim();
            int done = Integer.parseInt(arr[1].trim());
            String desc = arr[2];
            switch (task) {
            case "D":
                Deadline deadline = Deadline.of(desc, arr[3]);
                if (done == 1) {
                    deadline.markAsDone();
                }
                lst.addLast(deadline);
                break;
            case "T":
                Todo todo = Todo.of(desc);
                if (done == 1) {
                    todo.markAsDone();
                }
                lst.addLast(todo);
                break;
            case "E":
                Event event = Event.of(desc, arr[3]);
                if (done == 1) {
                    event.markAsDone();
                }
                lst.addLast(event);
                break;
            default:
                break;
            }
        }
    }

    public TaskList() {
        lst = new LinkedList<>();
    }

    /**
     * Returns the size of the task list.
     *
     * @return int Size of task list
     */
    public int getNumTasks() {
        return lst.size();
    }

    /**
     * Finds all matching tasks to the input keyword as an ArrayList.
     *
     * @param keyword Input to match
     * @return ArrayList All matching tasks
     */
    public ArrayList<String> findTask(String keyword) {
        ArrayList<String> matches = new ArrayList<>();
        for (Task task : lst) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                matches.add(task.toString());
            }
        }
        return matches;
    }

    /**
     * Adds a task to the back of the task list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        lst.addLast(task);
    }

    /**
     * Deletes a task from the task list based on index and returns the deleted task as a String representation.
     *
     * @param index Task index to remove
     * @return String String representation of the deleted task
     */
    public String deleteTask(int index) {
        assert lst.size() != 0;

        return lst.remove(index).toString();
    }

    /**
     * Marks a task from the task list as done and returns its String representation.
     *
     * @param index Task index to be marked as done
     * @return String String representation of the marked task
     */
    public String doneTask(int index) {
        assert lst.size() != 0;

        Task task = lst.get(index);
        task.markAsDone();
        return task.toString();
    }

    /**
     * Returns all tasks in the task list as a list of their String representations,
     * either in their toString() format or toSaveFormat() depending on the input boolean.
     *
     * @param isSaveFormat Boolean to determine the formatting of the task String
     * @return LinkedList List of all tasks in their String representations
     */
    public LinkedList<String> tasksToStringList(boolean isSaveFormat) {
        LinkedList<String> stringLst = new LinkedList<>();
        int i = 1;
        for (Task task : lst) {
            if (isSaveFormat) {
                stringLst.addLast(task.toSaveFormat());
            } else {
                stringLst.addLast(String.format("%d.%s", i, task.toString().trim()));
                ++i;
            }
        }
        return stringLst;
    }
}
