package duke.task;

import duke.command.DeadlineComparator;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    /**
     * Compares tasks according to chronological order.
     * @param taskOne First task to be compared.
     * @param taskTwo Second task to be compared.
     * @return Returns a negative integer, zero, or a positive integer as the first task is less than,
     *     equal to, or greater than the second.
     */
    public int compare(Task taskOne, Task taskTwo) {
        if (taskOne.equals(taskTwo)) {
            return 0;
        } else {
            if (taskOne.getType().equals(taskTwo.getType())) {
                return compareSameTaskType(taskOne, taskTwo);
            } else {
                return compareDifferentTaskType(taskOne, taskTwo);
            }
        }
    }

    private int compareSameTaskType(Task taskOne, Task taskTwo) {
        String taskType = taskOne.getType();
        if (taskType.equals("T")) {
            TodoComparator todoComparator = new TodoComparator();
            return todoComparator.compare((Todo)taskOne, (Todo)taskTwo);
        } else if (taskType.equals("D")) {
            DeadlineComparator deadlineComparator = new DeadlineComparator();
            return deadlineComparator.compare((Deadline)taskOne, (Deadline)taskTwo);
        } else {
            EventComparator eventComparator = new EventComparator();
            return eventComparator.compare((Event)taskOne, (Event)taskTwo);
        }
    }

    private int compareDifferentTaskType(Task taskOne, Task taskTwo) {
        String taskOneType = taskOne.getType();
        if (taskOneType.equals("T")) {
            return -1;
        } else if (taskOneType.equals("D")) {
            String taskTwoType = taskTwo.getType();
            if (taskTwoType.equals("T")) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 1;
        }
    }

}
