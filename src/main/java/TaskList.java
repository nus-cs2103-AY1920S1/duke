import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is a class which keeps track of the list of tasks.
 * @author Choong Yong Xin
 */

public class TaskList {

    ArrayList<Task> taskList;

    /**
     * Empty Constructor which creates a new file if file does not exist.
     */
    public TaskList() throws IOException {
        this.taskList = new ArrayList<Task>();;
        String currentDirectory = System.getProperty("user.dir");
        File parent = new File(currentDirectory + "/data/");
        File newFile = new File(parent, "/tasks.txt");
        if (parent.mkdirs()) {
            boolean isCreated = newFile.createNewFile();
            assert isCreated : "tasks.txt file should be created";
        }
    }

    /**
     * Constructor which loads list of tasks from hard disk.
     *
     * @param  taskList list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns string which contains the list of tasks.
     *
     * @return String containing list of tasks.
     */
    String displayTaskList() {
        String output = "Here are the tasks in your list:\n";
        int numCommands = 0;
        for (Task i : taskList) {
            numCommands += 1;
            output += (numCommands + "." + i + "\n");
        }
        return output;
    }

    /**
     * Adds a Todo task to tasklist.
     *
     * @param newTodo Todo task to be added.
     */
    void addToDo(Todo newTodo) {
        taskList.add(newTodo);
    }

    /**
     * Adds a Deadline task to tasklist.
     *
     * @param  newDeadline Dateline task to be added.
     */
    void addDeadline(Deadline newDeadline) {
        taskList.add(newDeadline);
    }

    /**
     * Adds a Event task to tasklist.
     *
     * @param  newEvent Event task to be added.
     */
    void addEvent(Event newEvent) {
        taskList.add(newEvent);
    }

    /**
     * Deletes a task from tasklist.
     *
     * @param  taskNumber Number of task to be deleted.
     */
    void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    /**
     * Finds a task from tasklist.
     *
     * @param  taskName task to be found.
     * @return  String to be output.
     */
    String findTasks(String taskName) {
        String output = "Here are the matching tasks in your list\n";
        int numCommands = 0;
        for (Task i : taskList) {
            numCommands += 1;
            if (i.description.toLowerCase().contains(taskName.toLowerCase())) {
                output += (numCommands + "." + i + "\n");
            }
        }
        return output;
    }
}
