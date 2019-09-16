package duke.task;

import duke.exception.FailedToLoadIOException;
import duke.parser.FileToTaskParser;
import duke.parser.TaskToFileParser;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This is the list of task items. This supports add, edit, delete operations. When a add, edit or delete operation is
 * to be performed, the <code>TaskManager</code> class provides an abstraction to support these operations.
 */
public class TaskList {

    /**
     * This is the list of task items.
     */
    private List<Task> taskList;

    /**
     * Constructs a new list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a new list of tasks from a stream of line. This stream of lines will be parsed by
     * {@link FileToTaskParser} to each individual tasks that will be added individually to form a list of tasks.
     * @param lines the stream of lines to be parsed.
     */
    public TaskList(Stream<String> lines) {
        this.taskList = new ArrayList<>();
        lines.forEach(line -> {
            try {
                Task task = FileToTaskParser.parse(line);
                taskList.add(task);
            } catch (FailedToLoadIOException lifpe) {
                new Ui().showLineError(lifpe.getLineCount(), line);
            }
        });
    }

    /**
     * Appends the task to the end of the list of the tasks.
     * @param task the task to be appended to the list
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task at the specified index from the list of tasks.
     * @param index the index of the task to be removed
     * @return a string representation of the task removed to be printed on a user interface.
     */
    public Task delete(int index) {
        return taskList.remove(index);
    }

    /**
     * Marks the task at the specified index from the list of tasks as done.
     * @param index the index of the task to be done.
     * @return a string representation of the done task to be be printed on a user interface.
     */
    public Task done(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Returns the number of tasks in the list.
     * @return the number of tasks in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns a string representation of the list of tasks.
     * @return a string representation of the list of tasks
     */
    public String view() {
        return IntStream.range(0, taskList.size())
                 .mapToObj(index -> {
                     int numbering = index + 1;
                     Task task = taskList.get(index);
                     return ("    " + numbering + "." + task.toString() + "\n"
                             + "     " + task.displayReminderIfPresent() + "\n");
                 })
                .reduce("", (x, y) -> x + y);
    }

    /**
     * Finds all matching tasks with the given keyword.
     * @param keyword the keyword to look out for in tasks' description
     * @return a string representation of the list of tasks that match the keyword
     */
    public String find(String keyword) {
        return new TaskList(taskList.stream()
                            .filter(taskDescrption -> taskDescrption.contains(keyword))
                            .map(task -> task.encode())).view();


    }

    /** Returns a stream of string to be saved into the {@link duke.storage.Storage}. Each task is parsed using a task
     * parser. See {@link TaskToFileParser} for more information.
     * @return a stream of string to be saved into storage
     */
    public Stream<String> save() {
        return taskList.stream().map(x -> TaskToFileParser.parse(x));
    }


    public Task remind(int index, Date date) {
        Task remindedTask = taskList.get(index);
        remindedTask.setReminder(date);
        return remindedTask;
    }
}
