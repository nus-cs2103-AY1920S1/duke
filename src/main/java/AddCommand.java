/**
 * AddCommand extends Command.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    private String[] oneLine;
    private Task task;
    public String[] timeDate;

    public AddCommand(String[] oneLine) {
        this.oneLine = oneLine;
    }

    /**
     * boolean isValidTime.
     */
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

        String description;
        String timeDate;
        String firstWord = oneLine[0];

        if (firstWord.equals("todo")) {
            tasks.getTaskList().add(new Todo(oneLine[1].trim()));
        } else if (firstWord.equals("deadline")) {
            this.timeDate = oneLine[1].trim().split(" /by ");
            if (this.timeDate.length == 2 && isValidTime(this.timeDate[1].trim())) {
                description = this.timeDate[0].trim();
                timeDate = this.timeDate[1].trim();
                tasks.getTaskList().add(new Deadline(description, timeDate));
            } else {
                throw new NoTimeAndDateException("specific date/time for deadline is wrong");
            }
        } else {
            this.timeDate = oneLine[1].trim().split(" /at ");
            if (this.timeDate.length == 2 && isValidTime(this.timeDate[1].trim())) {
                description = this.timeDate[0].trim();
                timeDate = this.timeDate[1].trim();
                tasks.getTaskList().add(new Event(description, timeDate));
            } else {
                throw new NoTimeAndDateException("specific date/time for event is wrong");
            }
        }
        System.out.println(Ui.frontSpace + " Got it. I've added this task: ");
        System.out.println(Ui.frontSpace + "   " + tasks.getTaskList().get(tasks.size() - 1));
        System.out.println(Ui.frontSpace + " Now you have " + tasks.getTaskList().size() + " tasks in the list.");
        try {
            storage.save(tasks);
        } catch (Exception e) {
            System.out.println();
            System.out.println(Ui.frontSpace + " duke.txt not exist");
        }
    }
}