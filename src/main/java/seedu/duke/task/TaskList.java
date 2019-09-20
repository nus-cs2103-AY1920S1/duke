package seedu.duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.duke.DukeException;
import seedu.duke.Parser;

public class TaskList {

    // the list of tasks is padded.
    // user tasks start from index 1.
    List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.tasks.add(0, new Task());
    }

    public TaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Create a tasklist object with from lines of formatted save data, each line
     * saved as an element in String array.
     * 
     * @param fileContent lines of formatted task strings.
     */
    public TaskList(String[] fileContent) {
        this(Arrays.stream(fileContent).map(Parser::parseLineToTask).filter(optionalTask -> !optionalTask.isEmpty())
                .map(optionalTask -> optionalTask.get()).collect(Collectors.toList()));
    }

    public String getSaveString() {
        return tasks.stream().map(task -> task.saveFormat()).collect(Collectors.joining());
    }

    /**
     * Adds a task to the task list.
     * 
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Searches for tasks in the list that contains the given keyword.
     * 
     * @param keyword to sear the list with
     * @return a new {@link TaskList} containing all the relevant tasks.
     */
    public TaskList search(String keyword) {
        List<Task> relevantTasks = tasks.stream().filter(task -> task.containKeyword(keyword))
                .collect(Collectors.toList());
        return new TaskList(relevantTasks);
    }

    /**
     * Marks a single task, denoted by its index in the internal list, as done.
     * 
     * 
     * @param taskNumber the index of the Task to be marked as done.
     * @return the task that is marked as done successfully.
     * 
     * @throws IndexOutOfBoundsException if the index given is greater that the
     *                                   number of tasks
     * @throws DukeException             if the task is already done.
     */
    public Task markAsDone(int taskNumber) throws IndexOutOfBoundsException, DukeException {
        Task task = tasks.get(taskNumber);
        task.markAsDone();
        return task;
    }

    public Task remove(int taskNumber) throws IndexOutOfBoundsException, DukeException {
        return tasks.remove(taskNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tasks.size(); i++) {
            if (i != 1) {
                sb.append("\n");
            }
            sb.append(String.format("%d. %s", i, tasks.get(i)));
        }
        return sb.toString();
    }
}
