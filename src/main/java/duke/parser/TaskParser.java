package duke.parser;

import duke.task.Task;

public class TaskParser {

    public static String parse(Task task) {
        return task.encode() + "\n";
    }
}
