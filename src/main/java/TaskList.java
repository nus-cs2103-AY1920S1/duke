import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private static final String HEADER = "Here are the tasks in your list: \n";

    /**
     * Gets details for all available task, in sorted order, when the list command is invoked.
     */
    public String getTaskList() {
        // sort according to alphabetical order
        Collections.sort(taskList, new TaskComparator());
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            int count = i + 1;
            tasks = tasks + count + "." + task.toString() + "\n";

        }
        return HEADER + tasks;
    }

    /**
     * Returns details for all available task when the list command is invoked.
     */
    public String getTasksData() {
        String message = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            int count = i + 1;
            message = message + task.toString() + "\r\n";

        }
        return message;
    }

    /**
     * Marks a task as done and prints message that a task is marked as done.
     * Returns message associated with a task marked as completed.
     *
     * @param taskIndex position of a task in the taskList Arraylist
     *
     */

    public String markDone(int taskIndex) {
        assert taskIndex >= 0 && taskIndex <= taskList.size(): "taskIndex must be within arraylist size";
        String msg;
        try {
            Task t = taskList.get(taskIndex);
            t.setCompleted();
            msg = "Nice! I've marked this task as done: \n";
            msg  = msg + "   [✓] " + t.getTask();

        } catch (IndexOutOfBoundsException ex) {
            if (taskList.size() == 0) {
                msg = "No tasks to be done.";
            } else {
                taskIndex += 1;
                msg = "Task " + taskIndex + " does not exist.";
            }
        }
        return msg;
    }

    /**
     * Deletes a task from taskList Arraylist and prints message that a task is deleted.
     * Returns a string which indicates a task has been deleted.
     *
     * @param taskIndex position of a task in the taskList Arraylist
     *
     */
    public String deleteTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex <= taskList.size(): "taskIndex must be within arraylist size";
        String msg = "";
        try {
            Task deletedTask = taskList.remove(taskIndex);
            msg = "Noted. I've removed this task: \n";
            msg = msg + " " + deletedTask.toString() + "\n";

            if (taskList.size() <= 1) {
                msg = msg + "Now you have " + taskList.size() + " task in the list.";
            } else {
                msg = msg + "Now you have " + taskList.size() + " tasks in the list.";
            }
        } catch (IndexOutOfBoundsException ex) {
            if (taskList.size() == 0) {
                msg = "Nothing to delete.";
            } else {
                taskIndex += 1;
                msg = "Task " + taskIndex + " does not exist.";
            }
        }
        return msg;
    }

    /**
     * Creates and adds a deadline object into taskList Arraylist and prints a message related to the object's creation.
     *
     * @param input information in the form of: return book /by 2/12/2019 1800 (an example)
     *
     */
    public String addDeadLine(String input) {
        String[] words = input.split(" /by ");
        Deadline dl = new Deadline(words[0], words[1]);
        taskList.add(dl);
        return dl.addDeadlineMsg(taskList.size());
    }

    /**
     * Creates and adds an event object and prints a message related to its creation.
     *
     * @param input information in the form of: proj-meeting /at 13/10/2019 6-8pm (an example)
     *
     */
    public String addEvent(String input) {
        String[] words = input.split(" /at ");
        Events event = new Events(words[0], words[1]);
        taskList.add(event);
        return event.addedEventMsg(taskList.size());
    }

    /**
     * Creates and adds a todo object to taskList Arraylist and prints a message related to the object's creation.
     *
     * @param input information in the form of: proj-meeting (an example)
     *
     */
    public String addToDo(String input) {
        Todo td = new Todo(input);
        taskList.add(td);
        return td.addedTodoMsg(taskList.size());
    }


}
