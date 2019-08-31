package duke.module;

import duke.task.Task;
import duke.exception.DukeIllegalIndexException;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private static final String DUKE_LIST_TASKS = "Here are the tasks in your list:";
    private static final String DUKE_NO_TASKS = "You currently have no tasks in your list.";

    private List<Task> taskList;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    private String generateIndexExceptionMessage(int index) {
        String message;
        if (index < 1) {
            message = "☹ OOPS!!! Index has to be greater than zero.";
        } else {
            message = String.format("☹ OOPS!!! Currently there are only %d tasks.", this.taskList.size());
        }
        return message;
    }

    // Index starts from 1
    public void markAsDoneTaskAt(int index) throws DukeIllegalIndexException {
        try {
            this.taskList.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalIndexException(this.generateIndexExceptionMessage(index));
        }
    }

    public void markAsDoneAllTasks() throws DukeIllegalIndexException {
        for (int i = 1; i <= this.taskList.size(); i++) {
            this.markAsDoneTaskAt(i);
        }
    }

    // Add task to the back of list and return added Task object
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    // Index starts from 1
    public Task getTaskAt(int index) throws DukeIllegalIndexException {
        try {
            return this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalIndexException(this.generateIndexExceptionMessage(index));
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task deleteTaskAt(int index) throws DukeIllegalIndexException {
        try {
            return this.taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalIndexException(this.generateIndexExceptionMessage(index));
        }
    }

    public void deleteAllTasks() {
        this.taskList.clear();
    }

    public List<Task> findAllTasksContaining(String charSequence) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(charSequence)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    public Iterator<Task> iterator() {
        return this.taskList.iterator();
    }

    public String[] listAll() {
        if (this.taskList.size() == 0) {
            return new String[] { DUKE_NO_TASKS };
        }

        List<String> lines = new ArrayList<>();
        lines.add(DUKE_LIST_TASKS);
        for (int i = 0; i < this.taskList.size(); i++) {
            lines.add(String.format("  %d.%s",
                    i + 1,
                    this.taskList.get(i).getStatus()));
        }
        return lines.toArray(new String[0]);
    }

}