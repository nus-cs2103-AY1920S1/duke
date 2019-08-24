public class TaskWithDate extends Task {
    protected String date;

    public TaskWithDate(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    public TaskWithDate(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * The current format of input for a task with date is to add a /at or /on. This extracts the data from that format.
     * @param line Line of input
     * @param delimiter delimiter provided [" /at " or " /on "]
     * @return
     */
    public static String[] extractDataFromLine(String line, String delimiter) {
        return line.split(delimiter);
    }

    /**
     * Verify that the data is correct before creating a task with data
     * @param data the data as extracted from extractDataFromLine
     * @param taskName Name of the task e.g. event or deadline
     * @return
     */
    public static boolean validateData(String[] data, String taskName) {
        if (data[0].length() <= 0) {
            System.out.println("☹ OOPS!!! The description of a " + taskName + " cannot be empty.");
            return false;
        } else if (data.length <= 1 || data[1].length() <= 0) {
            System.out.println("☹ OOPS!!! The date of a " + taskName + " cannot be empty.");
            return false;
        }
        return true;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
