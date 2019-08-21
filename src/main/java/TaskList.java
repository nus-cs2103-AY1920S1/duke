import java.util.List;
import java.util.ArrayList;

public class TaskList {

    protected List<Task> taskList;

    protected TaskList() {
        this.taskList = new ArrayList<>();
    }

    private String generateIndexExceptionMessage(int index) {
        String message;
        if (index < 1) {
            message = "☹ OOPS!!! Index has to be greater than zero.";
        } else {
            message = String.format("☹ OOPS!!! You have less than %d tasks.", index);
        }
        return message;
    }

    // Index starts from 1
    protected void markAsDoneTaskAt(int index) throws DukeIllegalIndexException {
        try {
            this.taskList.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalIndexException(this.generateIndexExceptionMessage(index));
        }
    }

    // Add task to the back of list and return added Task object
    protected Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    // Index starts from 1
    protected Task getTaskAt(int index) throws DukeIllegalIndexException {
        try {
            return this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalIndexException(this.generateIndexExceptionMessage(index));
        }
    }

    protected int getSize() {
        return this.taskList.size();
    }

    protected Task deleteTaskAt(int index) throws DukeIllegalIndexException {
        try {
            return this.taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalIndexException(this.generateIndexExceptionMessage(index));
        }
    }

}