import java.util.ArrayList;

/**
 * Represents the list of tasks in the program.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Creates a TaskList object including tasks from existing file.
     * @param fileList List of tasks read from file
     * @throws DukeException if inputs for tasks are missing or in a wrong format
     */
    public TaskList(ArrayList<String> fileList) throws DukeException {
        this.taskList = new ArrayList<Task>();

        for (int i = 0; i < fileList.size(); i++) {
            String[] listItem = fileList.get(i).split(" \\| ");
            String type = listItem[0];
            Task add;

            switch(type) {
            case "T":
                add = new Todo(type, listItem[2]);
                addTask(add, listItem[1]);
                break;
            case "D":
                add = new Deadline(type, listItem[1], listItem[3]);
                addTask(add, listItem[1]);
                break;

            case "E":
                add = new Event(type, listItem[2], listItem[3].split(" ")[0], listItem[3].split(" ")[1]);
                addTask(add, listItem[1]);
                break;

            case "F":
                 add = new FixedDurationTask(type, listItem[2], listItem[3]);
                 break;

            default:
                throw new DukeException("The input is not a task!");
            }
        }
    }

    /**
     * Creates an empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list
     * @param task the task to be added to the task list
     * @param status indicates whether the task has been completed
     */
    public void addTask(Task task, String status) {
        if(status == "\u2713") {
            task.markAsDone();
        }

        this.taskList.add(task);
    }

    /**
     * Returns list of existing tasks.
     * @return an ArrayList of tasks ocjects
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Prints out all tasks currently in the list.
     * @return String corresponding to tasks in their String format
     */
    public String printTaskList() {
        String printable = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            printable += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return printable;
    }

    /**
     * Adds a task object to the list of tasks.
     * @param task task object to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Retries a task object from the list of tasks.
     * @param num id number of task object to be retrieved
     */
    public Task get(int num) {
        return this.taskList.get(num);
    }

    /**
     * Removes a tasks object from the list of tasks.
     * @param num id of task object to be deleted
     */
    public void remove(int num) {
        this.taskList.remove(num);
    }

    /**
     * Retrives the number of objects in the task list.
     * @return the size of tasklist
     */
    public int size() {
        return this.taskList.size();
    }
}

