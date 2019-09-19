package duke.parser;

import duke.task.Task;

/**
 * This is a parser used to parse individual tasks into a string representation that will be saved into the file path
 * specified in {@link duke.main.Duke} and {@link duke.storage.Storage}.
 */
public class TaskToFileParser {

    /**
     * Parses the task into a string to be saved into the storage by encoding each tasks.
     * @param task the task to be parsed
     * @return a string representation of task
     */
    public static String parse(Task task) {
        assert (task != null);
        return task.encode() + "\n";
    }

}
