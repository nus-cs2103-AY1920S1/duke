import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.io.IOException;


public class DeadlineCommand extends Command {
    boolean isDone;
    String description;
    Date deadline;

    public DeadlineCommand(boolean isDone, String description, String deadline) {
        this.isDone = isDone;
        this.description = description;
        try {
            Date twentyFourHourFormat = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(deadline);
            String twelveHourFormat = new SimpleDateFormat("dd/MM/yyy hh:mm").format(twentyFourHourFormat);
            this.deadline = new SimpleDateFormat("dd/MM/yyy hh:mm").parse(twelveHourFormat);
        } catch (ParseException e) {
            System.out.println(e.getMessage());

        }
    }

    public void runCommand(TaskList taskList, Storage storage, Ui ui) {
        try {
            storage.appendToFile("D", this.deadline, this.description);

            Deadline newDeadline = new Deadline(this.description, this.deadline);

            taskList.addTask(newDeadline);

            int numTask = taskList.size();


            ui.printText("Got it. I've added this task: \n" + "  "
                    + newDeadline + "Now you have " +
                    numTask + " tasks in the list.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}