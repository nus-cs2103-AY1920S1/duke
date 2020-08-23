package duke;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import duke.error.DukeException;
import duke.error.InvalidTimeException;
import duke.task.Task;

public class TaskList {
    private List<Task> list;
    private HashSet<Long> timesUnavailable;

    /**
     * Empty constructor.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
        this.timesUnavailable = new HashSet<Long>();
    }

    /**
     * Constuctor.
     *
     * @param list List of Task
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds an item top the list.
     *
     * @param task Task
     * @throws InvalidTimeException if the time given is already used
     */
    public void add(Task task) throws DukeException {
        Optional<Date> taskTime = task.getTime();
        if (taskTime.isPresent()) {
            boolean isDateAvailable = this.verifyDateAvailability(taskTime.get());
            if (!isDateAvailable) {
                throw new InvalidTimeException("Sorry, but the time given is already used!");
            } else {
                this.timesUnavailable.add(convertMillisToMin(taskTime.get().getTime()));
            }
        }
        this.list.add(task);
    }

    /**
     * Removes an item from the list based on 1-indexed index.
     *
     * @param index int
     * @return Task
     */
    public Task remove(int index) {
        Task taskToRemove = this.get(index);
        Optional<Date> taskToRemoveDate = taskToRemove.getTime();
        if (taskToRemoveDate.isPresent()) {
            this.timesUnavailable.remove(convertMillisToMin(taskToRemoveDate.get().getTime())); 
        }
        return this.list.remove(index - 1);
    }

    /**
     * Gets the element based on 1-indexed index.
     *
     * @param index int
     * @return Task
     */
    public Task get(int index) {
        return this.list.get(index - 1);
    }

    /**
     * Gets the size of the list.
     *
     * @return int the size of the list
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Checks if date is available.
     *
     * @param date Date which is the Date of the object to be added
     * @return boolean
     */    
    private boolean verifyDateAvailability(Date date) {
        long dateInMinutes = this.convertMillisToMin(date.getTime());
        
        // Now check if the item already exists in the HashSet
        if (this.timesUnavailable.contains(dateInMinutes)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Converts time in millseconds since 1970 to time in minutes.
     *
     * @param millis long
     * @return long
     */
    private long convertMillisToMin(long millis) {
        return millis / 1000 / 60;
    }
}
