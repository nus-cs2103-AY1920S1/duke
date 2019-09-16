import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public abstract class Command {
    String desc;

    public Command(String desc) {
        this.desc = desc;
    }

    public abstract String execute(Storage storage, TaskList list, Ui ui) throws IOException, ParseException;

    /**
     * Gets the date.
     *
     * @param descArr The command line with description and date separated.
     * @param storage The storage for the task manager.
     * @return The date.
     * @throws ParseException If a parse exception occurred.
     */
    public Date getDate(String[] descArr, Storage storage)
            throws ParseException {
        return storage.convertToDate(descArr[1].substring(3));
    }

    /**
     * Gets the task number.
     *
     * @param arr The array with command and description separated.
     * @return The task number.
     */
    public int getTaskNum(String[] arr) {
        return Integer.parseInt(arr[1]) - 1;
    }
}
