public class TaskWithDate extends Task {
    protected String date;

    public TaskWithDate(String description, String date) {
        super(description);
        this.date = date;
    }

    public static String[] extractDataFromLine(String line, String delimiter) {
        return line.split(delimiter);
    }

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
}
