package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.time.Date;
import duke.time.Time;
import duke.ui.UI;

/**
 * Class that represents command to add new deadline.
 */
public class AddDeadlineCommand extends Command {

    private Deadline deadline;
    private String[] inputMessage;
    private String errorMessage = "noError";
    private TaskList listOfTasks;

    /**
     * Constructor that takes in main message of the deadline.
     * @param message The main message of the deadline.
     */
    public AddDeadlineCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        this.listOfTasks = listOfTasks;
        inputMessage = input.split(" ");
        String mainInput = "";
        int marker = 0;
        for (int i = 0; i < inputMessage.length; i++) {
            if (i + 1 >= inputMessage.length) {
                errorMessage = "Please provide more information!";
                return;
            }
            if (inputMessage[i + 1].equals("/by")) {
                mainInput += inputMessage[i];
                marker = i + 1;
                break;
            } else {
                mainInput += inputMessage[i];
                mainInput += " ";
            }
        }
        String tempInfo = "";
        for (int i = marker + 1; i < inputMessage.length; i++) {
            if (i == inputMessage.length - 1) {
                tempInfo += inputMessage[i];
            } else {
                tempInfo += inputMessage[i];
                tempInfo += " ";
            }
        }
        String[] dateAndTime = tempInfo.split(" ");
        String[] date = dateAndTime[0].split("/");
        if (date.length != 3 || dateAndTime.length < 2) {
            errorMessage = "Invalid date and time format!";
            return;
        }
        if (date[0].equals("") || date[1].equals("") || date[2].equals("")) {
            errorMessage = "Invalid date and time format!";
            return;
        }
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        Date inputDate = new Date(day, month, year);
        Time inputTime = new Time(Integer.parseInt(dateAndTime[1]));
        if (!inputDate.isValid()) {
            errorMessage = "Sorry! Invalid time format";
            return;
        }
        if (!inputTime.isValid()) {
            errorMessage = "Sorry! Invalid time format";
            return;
        }
        String extraInfo = inputDate + ", " + inputTime;
        if (extraInfo.equals("")) {
            errorMessage = "Please provide date and time for the deadline";
            return;
        }
        if (!inputMessage[marker].equals("/by")) {
            errorMessage = "Wrong syntax, should be using /by for deadline";
            return;
        }
        deadline = new Deadline(mainInput, extraInfo);
        listOfTasks.addDeadline(deadline);
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
    }

    @Override
    public String toString() {
        if (!errorMessage.equals("noError")) {
            return errorMessage;
        } else {
            String output = "Done! I have added this deadline to the list\n\n" + deadline.toString() + "\n\n";
            output += "You now have " + listOfTasks.size() + " tasks in the list!";
            return output;
        }
    }
}
