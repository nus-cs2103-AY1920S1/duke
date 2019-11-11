package seedu.duke.tasks.comparators;

import seedu.duke.tasks.Task;

import java.util.Comparator;

public class UpdateTimeComparator implements Comparator<Task> {
    @Override
    public int compare(Task taskA, Task taskB) {
        return taskB.getUpdatedAt().compareTo(taskA.getUpdatedAt());
    }
}
