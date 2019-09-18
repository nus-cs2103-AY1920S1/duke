package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.UI;
import duke.exception.DukeException;
import duke.time.Date;
import duke.time.Time;
import duke.task.Event;

/**
 * Class that represent the command to add a new event.
 */
public class AddEventCommand extends Command {

    private String[] inputMessage;
    private Event event;
    private String errorMessage = "noError";
    private TaskList listOfTasks;

    /**
     * Constructor that takes in main message of the event.
     * @param message The main message of the event.
     */
    public AddEventCommand(String message) {
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
            if (inputMessage[i + 1].equals("/at")) {
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
            errorMessage = "Please provide date and time for the event";
            return;
        }
        if (!inputMessage[marker].equals("/at")) {
            errorMessage = "Wrong syntax, should be using /at for event";
            return;
        }
        event = new Event(mainInput, extraInfo);
        listOfTasks.addEvent(event);
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
    }

    @Override
    public String toString() {
        if (!errorMessage.equals("noError")) {
            return errorMessage;
        } else {
            String output = "Done! I have added this event to the list\n\n" + event.toString() + "\n\n";
            output += "You now have " + listOfTasks.size() + " tasks in the list!";
            return output;
        }
    }
}
