import tasks.Task;
import tasks.Event;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Gets the ArrayList from a TaskList object.
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes the task in the specified index.
     * @param position index of task in task list.
     * @return The deleted task.
     */
    public Task deleteTask(String position) {
        int index = Integer.parseInt(position) - 1;
        assert index >= 0 : "index should not be negative";
        Task currTask = list.get(index);
        list.remove(index);
        return currTask;
    }

    /**
     * Change the status of a task of position p in the TaskList
     * to "done" and returns the updated Task.
     *
     * @param position index + 1 of the task in the list.
     * @return Updated Task.
     */
    public Task doTask(String position) {
        int index = Integer.parseInt(position) - 1;
        assert index >= 0 : "index should not be negative";
        Task currTask = list.get(index);
        currTask.doTask();
        return currTask;
    }

    /**
     * Gets a list of dates that are already filled up.
     * @return ArrayList of dates that are unavailable.
     */
    public ArrayList<Date> getFilledTimeSlots() {
        ArrayList<Date> timeSlots = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task instanceof Event) {
                Date date = ((Event) task).getAt();
                timeSlots.add(date);
            }
        }
        return timeSlots;
    }

    /**
     * Finds the next available date and time that has a
     * time slot of the specified duration.
     * @param duration Duration of time slot needed.
     * @return Date of next available time slot.
     */
    public Date findFreeTime(int duration) {
        ArrayList<Date> timeSlots = this.getFilledTimeSlots();
        Date currentTime = new Date();
        while (true) {
            boolean passed = true;
            for (Date slot: timeSlots) {
                long diff = slot.getTime() - currentTime.getTime();
                int diffHours = (int) (diff / (60 * 60 * 1000));
                if (diffHours >= -duration && diffHours <= duration) {
                    passed = false;
                    break;
                }
            }
            if (passed) {
                break;
            } else {
                currentTime = addHours(currentTime, 1);
            }
        }
        return currentTime;
    }

    /**
     * Adds the number of specified hours to a give date.
     * @param currentDate Given date.
     * @param hours Number of hours to be added.
     * @return Date with hours added.
     */
    public static Date addHours(Date currentDate, int hours) {
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(currentDate); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, hours); // adds one hour
        return cal.getTime();
    }
}
