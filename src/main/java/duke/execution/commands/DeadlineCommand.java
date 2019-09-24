package duke.execution.commands;

import duke.exception.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.UI;
import duke.models.Deadline;
import duke.models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents the characteristics of a Deadline Command.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param description of the deadline Command.
     */
    public DeadlineCommand(String description) {

        super(description);

    }

    /**
     * Returns the formatted date and time. If it is in the wrong format, it will return the string entered.
     *
     * @param dateAndTime String value of the date and time.
     * @return the formatted date and time, if it is in the correct format of dd/MM/yyyy hhmm
     */
    public static String getFormattedDate(String dateAndTime) {
        String result = dateAndTime;
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(dateAndTime);
            String day = new SimpleDateFormat("dd").format(date);
            String month = new SimpleDateFormat("MMMMMMMMMMMMMMM").format(date);
            String year = new SimpleDateFormat("yyyy").format(date);
            String time = new SimpleDateFormat("h:mm a").format(date).toLowerCase();
            String ordinalIndicator;

            int intDay = Integer.parseInt(day);
            if (intDay >= 11 && intDay <= 13) {
                ordinalIndicator = "th";
            } else if (intDay % 10 == 1) {
                ordinalIndicator = "st";
            } else if (intDay % 10 == 2) {
                ordinalIndicator = "nd";
            } else if (intDay % 10 == 3) {
                ordinalIndicator = "rd";
            } else {
                ordinalIndicator = "th";
            }

            result = intDay + ordinalIndicator + " of " + month + " " + year + ", " + time;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Executes a deadline Command object. Here, a deadline object will be created.
     *
     * @param taskList deadline object will be added to the tasklist.
     * @param ui to set a response from duke.
     * @param storage to store any changes in the storage.
     * @throws DukeException thrown if deadline command is entered without /by
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

        super.execute(taskList, ui, storage);

        String wholeTask = this.descriptionOfTask.trim();
        int index = wholeTask.indexOf('/');


        //what the task is
        String description = wholeTask.substring(0, index).trim();
        //when it is due by
        String date = wholeTask.substring(index + 4).trim();
        int intPriority = date.indexOf('*');
        //if the format is correct
        String formattedDate = getFormattedDate(date);


        //the duke.execution
        Task newDeadline;
        if (intPriority >= 0) {
            //removing the * if is priority
            String cleanDate = formattedDate.substring(0, intPriority)
                    + formattedDate.substring(intPriority + 1);
            newDeadline = new Deadline(description, cleanDate);
            newDeadline.markAsPriority();
            taskList.addPriorityTask(newDeadline);
        } else {
            newDeadline = new Deadline(description, formattedDate);
            taskList.addTask(newDeadline);
        }

        ui.displayAddingOfTask(newDeadline, taskList.getSize());
        storage.saveToDataFile(taskList);

    }

    /**
     * Handles the error and checks if it is valid for duke.execution.
     *
     * @throws DukeException if description of deadline is empty or is not in the correct format.
     */
    @Override
    protected void checkValidity() throws DukeException {

        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!this.descriptionOfTask.contains("/by")) {
            throw new DukeException(" ☹ OOPS!!! Deadline input should include '/by'.");
        }

    }
}
