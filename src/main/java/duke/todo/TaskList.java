package duke.todo;
import duke.DukeException;

import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> taskList;
    private int counter;

    /**
     * Constructs a TaskList object after taking in a list of file input.
     *
     * Throws DukeException when the input is empty.
     *
     * @param fileInput Input from the data file.
     * @throws DukeException
     */
    public TaskList(ArrayList<String> fileInput) throws DukeException {
        if (fileInput.size() == 0) {
            throw new DukeException("");
        } else {
            this.taskList = new ArrayList<>();
            this.counter = 0;

            for (String fileEntry : fileInput) {
                String[] processedEntry = fileEntry.split("\\|", 3);
                String taskType = processedEntry[0].trim();
                if (taskType.equals("T")) {
                    String taskDescription = processedEntry[1];
                    this.addTask(taskDescription);
                } else {
                    String taskDescription = processedEntry[1].trim();
                    String taskDate = processedEntry[2].trim();
                    this.addTask(taskType, taskDescription, taskDate);
                }
            }
        }
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.counter = 0;
    }

    /**
     * Returns a list of all tasks in the task list.
     *
     * @return String of all tasks.
     */
    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();

        for (Task task : taskList) {
            outputString.append(task + "\n");
        }

        return outputString.toString();
    }

    /**
     * Returns a string of formatted tasks from the task list
     * for end-of-programme write-over usage.
     *
     * @return String of all formatted tasks.
     */
    public String outputTasks() {
        StringBuilder output = new StringBuilder();

        for (Task task : this.taskList) {
            output.append(task.getFormattedTask() + "\n");
        }
        return output.toString();
    }

    /**
     * Returns a string of formatted report on the current task list.
     *
     * @return String of formmated report on the current task list.
     */
    public String generateList() {
        StringBuilder output = new StringBuilder();
        int index;
        Task currentTask;

        output.append("    Here are the tasks in your list:\n");
        for (int i = 0 ; i < counter; i++) {
            currentTask = taskList.get(i);
            index = i + 1;
            output.append("    ");
            output.append(index);
            output.append(". ");
            output.append(currentTask);
            if (i < counter - 1) output.append('\n');
        }
        return output.toString();
    }

    /**
     * Returns a task based on its index.
     *
     * @param index Index of the task.
     * @return Task with stated index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getNumOfTasks() {
        return counter;
    }

    public String findTask(String keyword) {
        ArrayList<Task> tasksFound = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                tasksFound.add(task);
            }
        }

        StringBuilder output = new StringBuilder();
        int index;
        Task currentTask;

        output.append("    Here are the matching tasks in your list:\n");
        for (int i = 0 ; i < counter; i++) {
            currentTask = tasksFound.get(i);
            index = i + 1;
            output.append("    ");
            output.append(index);
            output.append(". ");
            output.append(currentTask);
            if (i < counter - 1) output.append('\n');
        }
        return output.toString();
    }

    /**
     * Adds a todo task based on the input detail and
     * returns the task.
     *
     * @param taskDetail Detail of the task input.
     * @return Task added.
     */
    public Task addTask(String taskDetail) {
        int numOfTasks = counter++ + 1;
        Task newTask = new Todo(taskDetail);
        taskList.add(newTask);
        return newTask;
    }

    /**
     * Adds a task based on the input task type, task detail, and the date where
     * the task is due, and returns the task.
     *
     * @param taskType Type of the task added.
     * @param taskDetail Detail of the task added.
     * @param taskDate Due date of the task added.
     * @return Task added.
     */
    public Task addTask(String taskType, String taskDetail, String taskDate) {
        int numOfTasks = counter++ + 1;
        String[] inputTask = taskDetail.split("/by|/at");
        Task newTask = new Task("");

        switch (taskType) {
        case "D":
            newTask = new Deadline(taskDetail, taskDate);
            break;
        case "E":
            newTask = new Event(taskDetail, taskDate);
            break;
        default:
            break;
        }

        taskList.add(newTask);
        return newTask;
    }

    /**
     * Marks a task based on the input index as done.
     *
     * @param index Index of the task done.
     */
    public void markTaskDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Removes a task based on the input index from the task list.
     *
     * @param index Index of the task to be removed.
     * @return Task removed.
     */
    public Task removeTask(int index) {
        Task removedTask = taskList.remove(index - 1);
        counter--;
        return removedTask;
    }
}
