import java.time.LocalDateTime;

/**
 * Task is an object simulating a task, eg events.
 * This class contains attributes that every task should have
 * such as whether the task is done or what the description of
 * the task is. This class also has methods that can change
 * the attributes of the task, which are common among all
 * tasks such as doing the task and storing the deadlines
 * of the task as a Java date.
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;
    protected Priority taskPriority;

    /**
     * Constructs a Task object.
     * A task generally constructed with a description what to do.
     * @param description Description of what the task is about.
     */
    public Task(String description, Priority taskPriority) {
        this.description = description;
        this.isDone = false;
        this.taskPriority = taskPriority;
    }

    /**
     * Generates the status icon of the task.
     * Method returns either a tick or a cross icon based
     * on whether the task is done. Uses unicode.
     * @return String representing the unicode of the icon.
     */
    protected String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Changes the value of boolean isDone to True.
     */
    public void doTask() {
        this.isDone = true;
    }

    /**
     * Converts a String to a LocalDateTime object.
     * This method converts a string of the format "dd/mm/yyyy HHMM"
     * into a LocalDateTime object. e.g. "2/12/2019 1800"
     * @param date String of the format given above.
     * @return LocalDateTime LocalDateTime object representing the date.
     */
    protected LocalDateTime storeAsDateTime(String date) {
        StringBuilder sb = new StringBuilder();
        String[] temp = date.split(" ");


        int timeInt = Integer.parseInt(temp[2]);
        String hour = (timeInt / 100) + "";
        String minutes = (timeInt % 100) + "";

        String dateString = temp[1];
        String[] dateStringArr = dateString.split("/");

        //If day of the week is single digit pad with 0
        if (dateStringArr[0].length() == 1) {
            dateStringArr[0] = "0" + dateStringArr[0];
        }

        //If month is single digit pad with 0
        if (dateStringArr[1].length() == 1) {
            dateStringArr[1] = "0" + dateStringArr[1];
        }

        //Formatting for LocalDateTime to parse
        sb.append(dateStringArr[2]);
        sb.append("-");
        sb.append(dateStringArr[1]);
        sb.append("-");
        sb.append(dateStringArr[0]);
        sb.append("T");

        if (hour.length() == 1) {
            hour = "0" + hour;
        }

        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }

        sb.append(hour + ":" + minutes + ":00");

        return LocalDateTime.parse(sb.toString());
    }

    public String getDesc() {
        return this.description;
    }

    public Priority getPriority() {
        return taskPriority;
    }

    /**
     * Filler method to imitate an interface.
     * @return String empty string
     */
    protected String toFileFormat() {
        return "";
    }

    @Override
    public String toString() {
        String task = "[" + this.getStatusIcon() + "] " + description;
        return task;
    }

    @Override
    public int compareTo(Task task) {
        if (this.taskPriority == task.taskPriority) {
            return 0;
        } else if (this.taskPriority == Priority.HIGH) {
            return -1;
        } else if (this.taskPriority == Priority.LOW) {
            return 1;
        } else if (task.taskPriority == Priority.HIGH) {
            return 1;
        } else if (task.taskPriority == Priority.LOW) {
            return -1;
        } else {
            assert false;
            return 0;
        }
    }

}