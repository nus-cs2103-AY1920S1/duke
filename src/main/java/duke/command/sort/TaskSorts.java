package duke.command.sort;

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
