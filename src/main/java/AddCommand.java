import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    private String[] oneLine;
    private Task task;
    public String[] timeDate;


    public AddCommand(String[] oneLine) {
        this.oneLine = oneLine;
        System.out.println("[debug][AddCommand]: VVVVVVVVVVVVVV");
    }
    public static boolean isValidTime(String str) {
        try {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime localDate1 = LocalDateTime.parse(str, formatter1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        for(String str : oneLine){
            System.out.println("[debug][AddCommand]: oneLine[idx] " + "|" + str + "|");
        }
        String description;
        String time_date;
        String firstWord = oneLine[0];
        System.out.println("[debug][AddCommand] firstWord: " + "|" + firstWord + "|");
        System.out.println("[debug][AddCommand] euqals: " + "|" + firstWord.equals("todo") + "|");

        System.out.println(Ui.frontSpace + " Got it. I've added this task: ");
        if (firstWord.equals("todo")) {
            System.out.println("[debug][AddCommand] add todo : " + "|" + oneLine[1].trim() + "|");

            tasks.getTaskList().add(new Todo(oneLine[1].trim()));

        } else if (firstWord.equals("deadline")) {
            timeDate = oneLine[1].trim().split(" /by ");
            if (timeDate.length == 2 && isValidTime(timeDate[1].trim())) {
                description = timeDate[0].trim();
                time_date = timeDate[1].trim();
                tasks.getTaskList().add(new Deadline(description, time_date));
            } else {
                throw new NoTimeAndDateException("specific date/time for deadline is wrong");
            }
        } else {
            timeDate = oneLine[1].trim().split(" /at ");
            if (timeDate.length == 2 && isValidTime(timeDate[1].trim())) {
                description = timeDate[0].trim();
                time_date = timeDate[1].trim();
                tasks.getTaskList().add(new Event(description, time_date));
            } else {
                throw new NoTimeAndDateException("specific date/time for event is wrong");
            }
        }
        System.out.println("[debug][AddCommand]: tasks.size() " + "|" + tasks.size() + "|");

        System.out.println(Ui.frontSpace + "   " + tasks.getTaskList().get(tasks.size()-1));
        System.out.println(Ui.frontSpace + " Now you have " + tasks.getTaskList().size() + " tasks in the list.");
        try {
            storage.save(tasks);
        } catch (Exception e) {
            System.out.println(Ui.frontSpace + " duke.txt has problem");
        }

    }
}