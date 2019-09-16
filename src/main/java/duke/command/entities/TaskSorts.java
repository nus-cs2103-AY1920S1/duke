package duke.command.entities;

import duke.command.entities.sorts.TaskDateComparator;
import duke.command.entities.sorts.TaskNameComparator;
import duke.task.Task;

import java.util.Comparator;

public enum TaskSorts {
    NAME("name", new TaskNameComparator()),
    DATE("date", new TaskDateComparator());

    public final String keyword;
    public final Comparator<Task> comparator;

    TaskSorts(String keyword, Comparator<Task> comparator) {
        this.keyword = keyword;
        this.comparator = comparator;
    }
}
