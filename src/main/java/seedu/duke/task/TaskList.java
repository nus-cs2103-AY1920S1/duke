package seedu.duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.duke.Parser;

public class TaskList {

    List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(Stream<String> fileContent) {
        fileContent.map(line -> Parser.parseLineToTask(line)).filter(optionalTask -> !optionalTask.isEmpty())
                .forEach(task -> tasks.add(task.get()));
    }

    public String getSaveString() {
        return tasks.stream().map(task -> task.saveFormat()).collect(Collectors.joining("\n"));
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
