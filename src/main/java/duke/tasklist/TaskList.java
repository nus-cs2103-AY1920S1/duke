package duke.tasklist;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeTaskDoneException;
import duke.exception.DukeUpdateTodoTimeException;
import duke.exception.DukeWrongTimeFormatException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import duke.time.TimeConverter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a class that wraps around an ArrayList that stores the Tasks of the Duke App.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Creates a TaskList when there is no saved file and initialises the ArrayList to store 100 Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
        assert this.tasks != null : "ArrayList not created for TaskList";
    }

    /**
     * Creates a TaskList when there is a saved file present.
     *
     * @param current is the ArrayList that is translated from the File stored in the StorageData of the Duke App.
     */
    public TaskList(ArrayList<Task> current) {
        this.tasks = current;
    }

    /**
     * Returns the number of tasks stored in the ArrayList in the TaskList.
     *
     * @return the number of tasks in the TaskList object.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets the Task object at that particular index.
     *
     * @param taskNumber is the number of the task that we want to obtain from the ArrayList.
     * @return the Task object in the TaskList that is stored at the index represented by taskNumber.
     */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Adds a particular Task object to the ArrayList of the TaskList object.
     *
     * @param current the Task object that we want to add.
     */
    public void add(Task current) {
        tasks.add(current);
    }

    public void delete(int taskNumber) throws IndexOutOfBoundsException{
        tasks.remove(taskNumber - 1);
    }

    /**
     * Sets a Task from the ArrayList stored in the TaskList object to done status.
     *
     * @param taskNumber is the number of the Task that we want to set as done from the TaskList.
     * @return the String that represents the task that is done along with its modified status.
     * @throws IndexOutOfBoundsException when a taskNumber greater than the TaskList size or a negative number is given.
     * @throws DukeTaskDoneException when a taskNumber that corresponds to a task that is already done is given.
     */
    public void done(int taskNumber) throws DukeTaskDoneException, IndexOutOfBoundsException{
        Task current = tasks.get(taskNumber - 1);
        if(current.getStatus()) {
            throw new DukeTaskDoneException();
        } else {
            current.markAsDone();
            assert current.getStatus() == true : "Task not marked as done when method indicates so.";
        }
    }

    /**
     * First method called to check if the update command is followed by a tasknumber to be updated.
     * If command is correct, an overloaded update method will be called instead that checks what is to be modified.
     * @param details the details of the Task to be changed and what to update it with.
     * @throws DukeEmptyDescriptionException if the update command is followed by no user input.
     * @throws DukeMissingDescriptionException if the update command is followed by incomplete user input.
     */
    public String update(String details) throws DukeEmptyDescriptionException, DukeMissingDescriptionException,
            DukeWrongTimeFormatException, DukeUpdateTodoTimeException {
        Scanner infoReader = new Scanner(details);
        if(details.isEmpty()) {
            throw new DukeEmptyDescriptionException("update");
        } else if (infoReader.hasNextInt()) {
            int taskNumber = infoReader.nextInt() - 1;
            if(infoReader.hasNextLine()) {
                return this.update(taskNumber, infoReader.nextLine().trim());
            } else {
                throw new DukeMissingDescriptionException("update");
            }
        } else {
            throw new DukeMissingDescriptionException("update");
        }
    }

    /**
     * Checks what part of the Task object is to be modified. If an invalid field is given, an exception is thrown.
     * If the description is to be modified, the update is done immediately.
     * If the time is to be modified, another method is called to verify if the Task is an Event or Deadline first.
     * @param taskNumber is the taskNumber of the TaskList to be updated.
     * @param details is the details of the update itself.
     * @throws DukeMissingDescriptionException  if the update command has missing information afterward.
     */
    private String update(int taskNumber, String details) throws DukeMissingDescriptionException,
            DukeWrongTimeFormatException, DukeUpdateTodoTimeException, IndexOutOfBoundsException {
        Scanner infoReader = new Scanner(details);
        String whatToUpdate = infoReader.next();
        if(whatToUpdate.equals("description")) {
            Task current = this.get(taskNumber);
            if(infoReader.hasNextLine()) {
                return current.updateDescription(infoReader.nextLine().trim());
            } else {
                throw new DukeMissingDescriptionException("update");
            }
        } else if (whatToUpdate.equals("time")) {
            if(infoReader.hasNextLine()) {
                return this.updateTime(taskNumber, infoReader.nextLine().trim());
            } else {
                throw new DukeMissingDescriptionException("update");
            }
        } else {
            throw new DukeMissingDescriptionException("update");
        }
    }

    private String updateTime(int taskNumber, String details) throws DukeWrongTimeFormatException,
            DukeUpdateTodoTimeException, DukeMissingDescriptionException {
        if(details.isEmpty()) {
            throw new DukeMissingDescriptionException("update");
        }
        Task current = this.get(taskNumber);
        if(current instanceof Event) {
            String updatedTime = TimeConverter.convert(details);
            return ((Event)current).updatePeriod(updatedTime);
        } else if (current instanceof Deadline) {
            String updatedTime = TimeConverter.convert(details);
            return ((Deadline)current).updateTime(updatedTime);
        } else {
            throw new DukeUpdateTodoTimeException();
        }
    }
}
