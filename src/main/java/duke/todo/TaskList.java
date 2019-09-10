package duke.todo;

import duke.DukeException;
import duke.parser.Parser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class for task list operations and storage.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructs a TaskList object after taking in a list of file input.
     * Throws DukeException when the input is empty.
     *
     * @param fileInput Input from the data file.
     * @throws DukeException When input is empty.
     */
    public TaskList(List<String> fileInput) throws DukeException {
        if (fileInput.size() == 0) {
            throw new DukeException("");
        } else {
            this.taskList = parseFileInput(fileInput);
        }
    }

    /**
     * Parses the list of file input from data file and returns a list of tasks.
     *
     * @param fileInput Data file entries.
     * @return List of tasks stored in data file.
     * @throws DukeException When some entries are corrupted.
     */
    private List<Task> parseFileInput(List<String> fileInput) throws DukeException {
        List<Task> tasks = new ArrayList<>();

        for (String fileEntry : fileInput) {
            tasks.add(Parser.parseTaskFromFile(fileEntry));
        }
        return tasks;
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
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
    public String outputTasksInString() {
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
    public String generateListInString() {
        StringBuilder output = new StringBuilder();
        int index;
        Task currentTask;

        output.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            currentTask = taskList.get(i);
            index = i + 1;
            output.append(index);
            output.append(". ");
            output.append(currentTask);
            if (i < taskList.size() - 1) {
                output.append('\n');
            }
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
        return taskList.size();
    }

    /**
     * Finds a task based on the keyword input.
     *
     * @param keyword Keyword of the task.
     * @return Task with the keyword.
     */
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
        for (int i = 0; i < taskList.size(); i++) {
            currentTask = tasksFound.get(i);
            index = i + 1;
            output.append("    ");
            output.append(index);
            output.append(". ");
            output.append(currentTask);
            if (i < taskList.size() - 1) {
                output.append('\n');
            }
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
    public Task addTask(String taskType, String taskDetail, LocalDateTime taskDate) {
        Task newTask = new Task("");

        switch (taskType) {
        case "deadline":
            newTask = new Deadline(taskDetail, taskDate);
            break;
        case "event":
            newTask = new Event(taskDetail, taskDate);
            break;
        default:
            break;
        }

        taskList.add(newTask);
        return newTask;
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Marks a task based on the input index as done.
     *
     * @param index Index of the task done.
     * @return Task done.
     */
    public Task markTaskDone(int index) {
        Task taskDone = taskList.get(index);
        taskDone.markAsDone();
        return taskDone;
    }

    /**
     * Removes a task based on the input index from the task list.
     *
     * @param index Index of the task to be removed.
     * @return Task removed.
     */
    public Task removeTask(int index) {
        Task removedTask = taskList.remove(index - 1);
        return removedTask;
    }
}
