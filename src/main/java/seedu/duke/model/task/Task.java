package seedu.duke.model.task;

import java.util.Comparator;
import java.util.Random;

import seedu.duke.model.dateandtime.Date;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.model.dateandtime.Time;

/**
 * Defines a Task object that represents a task.
 */
public class Task {

    public enum Type {
        TODO, DEADLINE, EVENT;
    }

    private String taskString;
    private Type type;
    private DateAndTime dateAndTime;

    private boolean isDone = false;
    private boolean markedAsDelete = false;

    private static int count;
    private int id;

    private int listIndex;

    // Comparators used to compare Task objects based on different criteria.
    private static Comparator<Task> stringComparator = Comparator.comparing((Task x) -> x.taskString);
    private static Comparator<Task> revStringComparator = stringComparator.reversed();
    private static Comparator<Task> dateAndTimeComparator = (Task x, Task y)
        -> x.dateAndTime == null && y.dateAndTime == null ? stringComparator.compare(x, y)
        : x.dateAndTime == null ? 1
        : y.dateAndTime == null ? -1
        : x.dateAndTime.compareTo(y.dateAndTime) > 0 ? 1
        : x.dateAndTime.compareTo(y.dateAndTime) < 0 ? -1
        : stringComparator.compare(x, y);
    private static Comparator<Task> revDateAndTimeComparator = (Task x, Task y)
        -> x.dateAndTime == null && y.dateAndTime == null ? stringComparator.compare(x, y)
        : x.dateAndTime == null ? -1
        : y.dateAndTime == null ? 1
        : x.dateAndTime.compareTo(y.dateAndTime) > 0 ? -1
        : x.dateAndTime.compareTo(y.dateAndTime) < 0 ? 1
        : stringComparator.compare(x, y);
    private static Comparator<Task> doneComparator = (Task x, Task y)
        -> x.isDone && !y.isDone ? -1 : y.isDone && !x.isDone ? 1 : stringComparator.compare(x, y);
    private static Comparator<Task> undoneComparator = (Task x, Task y)
        -> y.isDone && !x.isDone ? -1 : x.isDone && !y.isDone ? 1 : stringComparator.compare(x, y);
    private static Comparator<Task> idComparator = Comparator.comparingInt((Task x) -> x.id);
    private static Comparator<Task> revIdComparator = idComparator.reversed();
    private static Comparator<Task> typeComparator = (Task x, Task y)
        -> x.type.toString().compareTo(y.type.toString()) == 0
        ? stringComparator.compare(x, y)
        : x.type.toString().compareTo(y.type.toString());
    private static Comparator<Task> revTypeComparator = (Task x, Task y)
        -> x.type.toString().compareTo(y.type.toString()) == 0
        ? stringComparator.compare(x, y)
        : y.type.toString().compareTo(x.type.toString());

    /**
     * Creates a Task object with a task description, type and date / time if any.
     * @param type Type of Task to be created.
     * @param taskString Task description of the task.
     * @param dateAndTime DateAndTime object representing the date and time associated with this task.
     *                    If there is no DateAndTime associated with this task, a client of this class may pass in
     *                    null for this parameter.
     */
    public Task(Type type, String taskString, DateAndTime dateAndTime) {
        this.type = type;
        this.taskString = taskString;
        this.dateAndTime = dateAndTime;
        Task.count++;
        this.id = Task.count;
    }

    /**
     * Returns an entirely new Task object copy with the same attributes as that of this Task.
     * @return Copy of this Task.
     */
    public Task copy() {
        DateAndTime dateAndTime = this.getDateAndTime();
        if (dateAndTime != null) {
            this.dateAndTime = new DateAndTime(dateAndTime.getDate(), dateAndTime.getTimeStart(),
                    dateAndTime.getTimeEnd());
        }
        Task copy;
        if (this instanceof Todo) {
            copy = new Todo(this.taskString);
        } else if (this instanceof Event) {
            copy = new Event(this.taskString, dateAndTime);
        } else {
            copy = new Deadline(this.taskString, dateAndTime);
        }
        copy.id = this.id;
        copy.isDone = this.isDone;
        copy.markedAsDelete = this.markedAsDelete;
        return copy;
    }

    /**
     * Returns a String that indicates that this Task instance has been added to the given TaskList.
     * Indicates also the updated number of tasks in the given TaskList
     * Pre-condition: This Task has indeed been added to the given TaskList.
     * @param tasks TaskList to which this Task has been added.
     * @return String indicating that this Task instance has been added to the given TaskList.
     */
    public String addedMessage(TaskList tasks) {
        String s = "Got it. I've added this task:\n  " + this + "\n"
                + "Now you have " + tasks.size();
        if (tasks.size() == 1) {
            s += " task in the list.";
        }  else {
            s += " tasks in the list.";
        }
        assert tasks.size() > 0 : "Task is claimed to have been added to TaskList but TaskList contains less than "
                + "1 item.";
        return s;
    }

    /**
     * Returns a String that indicates that this Task instance has been removed from the given TaskList.
     * Indicates also the updated number of tasks remaining in the given TaskList
     * Pre-condition: This Task has indeed been removed to the given TaskList.
     * @param tasks TaskList to which this Task has been removed.
     * @return String indicating that this Task instance has been removed from the given TaskList.
     */
    public String deletedMessage(TaskList tasks) {
        String s = "Noted. I've removed this task:\n"
                + this + "\n";
        if (tasks.size() == 1) {
            s += "Now you have 1 task in the list.";
        } else {
            s += "Now you have " + tasks.size() + " tasks in the list.";
        }
        return s;
    }

    /**
     * Returns a String that indicates that this Task instance has been marked as done.
     * Pre-condition: This Task has indeed been marked as done.
     * @return String indicating that this Task instance has been marked as done.
     */
    public String doneMessage() {
        return "Nice! I've marked this task as done:\n  " + this;
    }

    /**
     * Returns the task description of this Task.
     * @return Task description of this Task stored in the taskString attribute.
     */
    public String getTaskString() {
        assert taskString != null : "Task does not have a task description";
        return taskString;
    }

    /**
     * Returns the state of whether the Task has been marked as done (completed).
     * @return A boolean stored in the isDone attribute denoting whether the Task has been marked as done (completed).
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the type of Task stored in this Task instance's type attribute.
     * Represented by Type constant.
     * @return Type of task stored in this Task instance's type attribute.
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns a DateAndTime object stored in this Task instance's dateAndTime attribute.
     * Returns null if this Task has no date and time associated with it.
     * @return DateAndTime object stored in this Task instance's dateAndTime attribute.
     */
    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Returns the boolean of whether this Task instance is to be marked as delete.
     * @return True or false boolean on whether this Task instance is to be marked as delete.
     */
    public boolean getMarkedAsDelete() {
        return markedAsDelete;
    }

    /**
     * Sets this instance of task to be marked as deleted.
     * Post-requisite: The Duke program will delete all tasks that have been marked to be deleted.
     */
    public void markAsDelete() {
        markedAsDelete = true;
    }

    /**
     * Sets the state of whether this Task has been completed.
     * @param isDone A boolean passed in by a client of this method to determine whether this Task has been marked as
     *               done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
        assert this.isDone == isDone : "State of whether Task has been completed was intended to be set "
                + "but was not set by system.";
    }

    /**
     * Returns a comparator that compares tasks based on the tasks' DateAndTime attribute.
     * @return A DateAndTime Comparator of this class.
     */
    public static Comparator<Task> getDateAndTimeComparator() {
        return dateAndTimeComparator;
    }

    /**
     * Returns a reversed comparator that compares tasks based on the tasks' DateAndTime attribute.
     * @return A reversed DateAndTime Comparator of this class (latest date first).
     */
    public static Comparator<Task> getRevDateAndTimeComparator() {
        return revDateAndTimeComparator;
    }


    /**
     * Returns a comparator that compares tasks based on their done status. (done tasks sorted first.)
     * @return A done status comparator of this class that arranges done tasks first.
     */
    public static Comparator<Task> getDoneComparator() {
        return doneComparator;
    }

    /**
     * Returns a comparator that compares tasks based on their done status. (undone tasks sorted first.)
     * @return A done status comparator of this class that arranges undone tasks first.
     */
    public static Comparator<Task> getUndoneComparator() {
        return undoneComparator;
    }

    /**
     * Returns a comparator that compares tasks based on the order which they are created.
     * @return An id comparator of this class that arranges tasks in order of their id.
     */
    public static Comparator<Task> getIdComparator() {
        return idComparator;
    }

    /**
     * Returns a reversed comparator that compares tasks based in reverse order which they are created.
     * @return A reversed id comparator of this class that arranges tasks in reverse order of their id.
     */
    public static Comparator<Task> getRevIdComparator() {
        return revIdComparator;
    }


    /**
     * Returns a comparator that compares tasks based on their type, in alphabetical order.
     * @return A Type comparator of this class that arranges tasks in order of their type.
     */
    public static Comparator<Task> getTypeComparator() {
        return typeComparator;
    }

    /**
     * Returns a reversed comparator that compares tasks based on their type, in reverse alphabetical order.
     * @return A reversed Type comparator of this class that arranges tasks in reverse order of their type.
     */
    public static Comparator<Task> getRevTypeComparator() {
        return revTypeComparator;
    }

    /**
     * Returns a comparator that compares tasks based on the tasks' descriptions.
     * @return A task description comparator of this class.
     */
    public static Comparator<Task> getStringComparator() {
        return stringComparator;
    }

    /**
     * Returns a reversed comparator that compares tasks based on the tasks' descriptions.
     * @return A task description comparator of this class (reverse alphabetical order).
     */
    public static Comparator<Task> getRevStringComparator() {
        return revStringComparator;
    }

    /**
     * Generates a random Task with random attributes.
     * Each random Task has a randomly generated integer ID in its description.
     * @return Randomly generated task of random type, task description and date/ time.
     */
    public static Task generateRandomTask() {

        // Generates Task description id from random alphabets, numerical digits and spaces.
        String id = "";
        char[] randCharBox = new char[62];

        for (int i = 0; i < 26; i++) {
            randCharBox[i] = (char) (i + 65);
        }

        for (int j = 26; j < 36; j++) {
            randCharBox[j] = (char) (j + 22);
        }

        for (int k = 36; k < 62; k++) {
            randCharBox[k] = (char) (k + 61);
        }

        Random rand = new Random();

        int lastIndexOfSpace = 0;
        int randIdSize = 5 + rand.nextInt(10);

        for (int i = 0; i < randIdSize; i++) {
            if (i > lastIndexOfSpace + 3) {
                boolean generateSpace = rand.nextBoolean();
                if (generateSpace == true) {
                    id += " ";
                    lastIndexOfSpace = i;
                    continue;
                }
            }
            id += randCharBox[rand.nextInt(61)];
        }

        boolean randIsDone = rand.nextBoolean();

        String taskString = "rand " + " " + id;

        // Randomly generates whether date of Deadline is DD/MM/YYYY format or DD/MM/YYYY HHMM format.
        int randDateFormat = rand.nextInt(2);

        int randYear = rand.nextInt(10000);
        int randMonth = 1 + rand.nextInt(12);
        int numOfDays = randMonth == 2
                ? (Date.isLeapYear(randYear) ? 29 : 28)
                : (randMonth <= 7 && randMonth % 2 != 0) || (randMonth > 7 && randMonth % 2 == 0)
                ? 31
                : 30;
        int randDay = 1 + rand.nextInt(numOfDays);
        int randStartHour = rand.nextInt(24);
        int randStartMin = rand.nextInt(60);
        int randEndHour = rand.nextInt(24);
        int randEndMin = rand.nextInt(60);

        Date randDate = new Date(randDay, randMonth, randYear);
        Time randStartTime = new Time(randStartHour, randStartMin);
        Time randEndTime = new Time(randEndHour, randEndMin);

        int randType = rand.nextInt(3);
        Type type = randType == 0 ? Type.TODO : randType == 1
                ? Type.EVENT : Type.DEADLINE;

        Task task;
        if (type == Type.TODO) {
            task = new Todo(taskString, randIsDone);
        } else if (type == Type.DEADLINE) {
            DateAndTime randDateAndTime = randDateFormat == 0
                    ? new DateAndTime(randDate)
                    : new DateAndTime(randDate, randEndTime);
            task = new Deadline(taskString, randDateAndTime, randIsDone);
        } else {
            DateAndTime randDateAndTime = new DateAndTime(randDate, randStartTime, randEndTime);
            task = new Event(taskString, randDateAndTime, randIsDone);
        }

        return task;
    }

    /**
     * Returns the String representation of a Task instance.
     * Formats the String to be returned such that it contains the Task's as type, state of whether it has been
     * completed, and task description in this order.
     * @return String representation of a Task instance.
     */
    @Override
    public String toString() {
        assert taskString != null : "Task does not have a task description";
        String doneString = isDone == true ? "[+]" : "[ ]";
        return "[" + type.toString().charAt(0) + "]" + doneString + " " + this.taskString;
    }
}
