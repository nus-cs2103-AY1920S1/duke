/**
 * This class contains the task list and methods to manipulate the list of tasks: generating the number of tasks in
 * memory, adding tasks or deleting tasks.
 */

package duke.managers;

import duke.exceptions.DukeException;

import duke.tasks.Event;
import duke.tasks.Task;


import java.util.ArrayList;
import java.util.OptionalInt;

public class TaskList {
    private static ArrayList<Task> allTasks;

    /**
     * Loads previously stored information in the hard disk.
     *
     * @param loadedTasks a data structure containing the tasks stored
     * @throws DukeException is thrown when there are no tasks to be loaded
     */
    public TaskList(ArrayList<Task> loadedTasks) throws DukeException {
        if (loadedTasks.size() == 0) {
            throw new DukeException("No tasks to load. A new TaskList will be created.");
        } else {
            this.allTasks = loadedTasks;
        }
    }

    /**
     * Generates an empty list of tasks in the case when there are no previously saved tasks.
     */
    public TaskList() {
        allTasks = new ArrayList<>();
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public int totalNumTasks() {
        return allTasks.size();
    }

    public Task getTask(int taskNum) {
        return allTasks.get(taskNum - 1);
    }

    public Task delTask(int taskNum) {
        return allTasks.remove(taskNum - 1);
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    /**
     * Checks first if the event the user entered has the same date as a previously saved event. If yes, another
     * method will be called to check if there is an overlap in the timings.
     *
     * @param date String containing the day of the new event
     * @param timeToTime String containing the time of the new event (in this format: 6.30pm-7pm)
     * @return boolean that determines whether there are existing clashes
     */
    public OptionalInt noDateClashes(String date, String timeToTime) {
        int index = 0;
        OptionalInt clashingTaskIndex = OptionalInt.empty();
        for (Task task : allTasks) {
            if (task instanceof Event) {
                String[] eventTimeArr = ((Event) task).getEventTime().split("[,]");
                if (eventTimeArr[0].trim().equals(date)) {
                    clashingTaskIndex = noTimeClashes(timeToTime, index);
                    if (!clashingTaskIndex.isEmpty()) {
                        break;
                    }
                }
            }
            index++;
        }
        return clashingTaskIndex;
    }

    /**
     * Checks if there is clash in the timings of the newly added event and existing events in memory.
     *
     * @param timeToTime String containing the time of the newly added event
     * @param index of first Event in the list that shares the same day as the newly added event
     * @return boolean that determines whether there are existing clashes
     */
    private OptionalInt noTimeClashes(String timeToTime, int index) {
        String[] newEventTime = timeToTime.split("-");
        double newEventTime1 = processTime(newEventTime[0]);
        double newEventTime2 = processTime(newEventTime[1]);
        Event targetEvent = (Event) allTasks.get(index);
        String[] targetEventTime = targetEvent.getEventTime().split(",")[1].split("-");
        double targetTime1 = processTime(targetEventTime[0]);
        double targetTime2 = processTime(targetEventTime[1]);
        if (newEventTime1 <= targetTime1 && newEventTime2 > targetTime1) {
            return OptionalInt.of(index);
        } else if (newEventTime1 >= targetTime1 && newEventTime1 <= targetTime2) {
            return OptionalInt.of(index);
        } else {
            return OptionalInt.empty();
        }
    }

    /**
     * Converts time descriptions saved in Duke (e.g. "23rd of March 2019, 6pm-7pm") into numbers for comparison to
     * check for clash in timings.
     *
     * @param time String containing the time saved in Duke
     * @return double containing the same time in military format
     */
    private double processTime(String time) {
        int length = time.length();
        String suffix = time.substring(length - 2, length);
        double timeNum = Double.parseDouble(time.substring(0, length - 2));
        if (suffix.equals("am") && timeNum == 12) {
            return 0;
        } else if (suffix.equals("pm")) {
            return timeNum + 12;
        } else {
            return timeNum;
        }
    }
}
