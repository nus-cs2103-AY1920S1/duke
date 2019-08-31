package duke.task;

import duke.exception.LineInFileParseException;
import duke.parser.FileLineParser;
import duke.parser.TaskParser;
import duke.ui.Ui;
import java.lang.StringBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This is the list of task items. This supports add, edit, delete operations. When a add, edit or delete operation is
 * to be performed, the <code>TaskList</code> class provides an abstraction to support these operations.
 */
public class TaskList {

    /**
     * This is the list of task items.
     */
    private final List<Task> taskList;

    /**
     * Constructs a new list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a new list of tasks from a stream of line. This stream of lines will be parsed by
     * {@link FileLineParser} to each individual tasks that will be added individually to form a list of tasks.
     * @param lines the stream of lines to be parsed.
     */
    public TaskList(Stream<String> lines) {
        this.taskList = new ArrayList<>();
        lines.forEach(line -> {
            try {
                taskList.add(FileLineParser.parse(line));
            } catch (LineInFileParseException lifpe) {
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
    public String delete(int index) {
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        return task.toString();
    }

    /**
     * Marks the task at the specified index from the list of tasks as done.
     * @param index the index of the task to be done.
     * @return a string representation of the done task to be be printed on a user interface.
     */
    public String done(int index) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        return task.toString();
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
    public String list() {
        StringBuffer buffer = new StringBuffer();
        int i = 1;
        for (Task task :  taskList) {
            buffer.append("    " + i + "." + task.toString() + "\n");
            i++;
        }
        return buffer.toString();
    }

    /** Returns a stream of string to be saved into the {@link duke.storage.Storage}. Each task is parsed using a task
     * parser. See {@link TaskParser} for more information.
     * @return a stream of string to be saved into storage
     */
    public Stream<String> save() {
        return taskList.stream().map(x -> TaskParser.parse(x));
    }

}
