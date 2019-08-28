import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventCommand extends Command {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException{
        ui.showLine();
        int end = ui.getRemainingWords().indexOf('/');
        if (end > 0) {
            String description = ui.getRemainingWords().substring(1, ui.getRemainingWords().indexOf('/'));
            String time = ui.getRemainingWords().substring(end + 4).trim();
            if (time.isEmpty()) {
                throw new DukeException("☹OOPS!!! Wrong format'");
            } else {
                Event m = new Event(description, makeDate(time));
                tasks.taskArrayList.add(m);
                storage.writeData();

                System.out.println("Got it. I've added this task:");
                System.out.println(m);
                System.out.println("Now you have " + tasks.taskArrayList.size() + " tasks in the list.");
                ui.showLine();
            }
        } else {
            throw new DukeException("☹OOPS!!! Wrong format'");
        }
    }

    public boolean isExit() {
        return false;
    }

    public String makeDate(String input) throws ParseException {
        String ordinalIndicator;
        Date date = DATE_FORMAT.parse(input);
        String day = new SimpleDateFormat("dd").format(date);
        String month = new SimpleDateFormat("MMMMMMMMMMM").format(date);
        String year = new SimpleDateFormat("yyyy").format(date);
        String time = new SimpleDateFormat("h:mma").format(date).toLowerCase();

        int int_day = Integer.parseInt(day);
        if (int_day >= 11 && int_day <= 13) {
            ordinalIndicator = "th";
        } else if (int_day % 10 == 1) {
            ordinalIndicator = "st";
        } else if (int_day % 10 == 2) {
            ordinalIndicator = "nd";
        } else if (int_day % 10 == 3) {
            ordinalIndicator = "rd";
        } else {
            ordinalIndicator = "th";
        }

        String outputDate = int_day + ordinalIndicator + " of " + month + " " + year + ", " + time;
        return outputDate;
    }
}
