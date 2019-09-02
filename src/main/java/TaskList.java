import java.util.ArrayList;

/**
 * Represents the list of tasks in the program.
 */
public class TaskList {
    protected ArrayList<Task> taskList;
    protected ArrayList<String> fileList;

    /**
     * Creates a TaskList object including tasks from existing file.
     * @param fileList List of tasks read from file
     * @throws DukeException if inputs for tasks are missing or in a wrong format
     */
    public TaskList(ArrayList<String> fileList) throws DukeException {
        this.fileList = fileList;
        this.taskList = new ArrayList<Task>();

        for (int i = 0; i < fileList.size(); i++) {
            String[] listItem = fileList.get(i).split(" \\| ");
            String type = listItem[0];
            Task add;

            switch(type) {
            case "T":
                add = new Todo(listItem[2]);

                if(listItem[1] == "✓") {
                    add.markAsDone();
                }

                this.taskList.add(add);

            case "D":
                add = new Deadline(listItem[2], listItem[3]);

                if(listItem[1] == "✓") {
                    add.markAsDone();
                }

                this.taskList.add(add);

            case "E":
                add = new Events(listItem[2], listItem[3]);

                if(listItem[1] == "✓") {
                    add.markAsDone();
                }

                this.taskList.add(add);
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

