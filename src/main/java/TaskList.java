import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

class TaskList {

    private List<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
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
    void markAsDoneTaskAt(int index) throws DukeIllegalIndexException {
        try {
            this.taskList.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalIndexException(this.generateIndexExceptionMessage(index));
        }
    }

    // Add task to the back of list and return added Task object
    void addTask(Task task) {
        this.taskList.add(task);
    }

    // Index starts from 1
    Task getTaskAt(int index) throws DukeIllegalIndexException {
        try {
            return this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalIndexException(this.generateIndexExceptionMessage(index));
        }
    }

    int getSize() {
        return this.taskList.size();
    }

    Task deleteTaskAt(int index) throws DukeIllegalIndexException {
        try {
            return this.taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalIndexException(this.generateIndexExceptionMessage(index));
        }
    }

    void addTasks(List<Task> tasksToAdd) {
        this.taskList.addAll(tasksToAdd);
    }

    Iterator<Task> iterator() {
        return this.taskList.iterator();
    }

    List<Task> asList() {
        return this.taskList;
    }

}