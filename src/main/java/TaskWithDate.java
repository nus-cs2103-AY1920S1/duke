import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskWithDate extends Task {
    static final SimpleDateFormat TASK_WITH_DATE_FORMATTER = new SimpleDateFormat("dd 'of' MMMM yyyy, Kmma");
    static final SimpleDateFormat TASK_WITH_DATE_PARSER = new SimpleDateFormat("dd/MM/yyyy HHmm");
    protected Date date;

    public TaskWithDate(String description, String date) throws ParseException {
        super(description);
        this.date = TASK_WITH_DATE_PARSER.parse(date);
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
