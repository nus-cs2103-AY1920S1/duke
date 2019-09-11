package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Deadline;
import models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadlineCommand extends Command {
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

            result = int_day + ordinalIndicator + " of " + month + " " + year + ", " + time;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        super.execute(taskList, ui, storage);

        String wholeTask = this.descriptionOfTask.trim();
        int index = wholeTask.indexOf('/');
        //what the task is
        String description = wholeTask.substring(0, index).trim();
        //when it is due by
        String date = wholeTask.substring(index + 4).trim();
        String formattedDate = getFormattedDate(date);
        Task newDeadline = new Deadline(description, formattedDate);
        taskList.addTask(newDeadline);

        ui.displayAddingOfTask(newDeadline, taskList.getSize());
        storage.saveToDataFile(taskList);

    }

    @Override
    protected void checkValidity() throws DukeException {
        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!this.descriptionOfTask.contains("/by")) {
            throw new DukeException(" ☹ OOPS!!! Deadline input should include '/by'.");
        }
    }
}
