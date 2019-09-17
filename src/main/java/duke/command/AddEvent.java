package duke.command;

import duke.exception.DukeIllegalDescriptionException;
import duke.exception.DukeIllegalTimeFormatException;
import duke.io.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class AddEvent extends Command {
    public static String addEvent(String act, Storage sto) throws FileNotFoundException, DukeIllegalDescriptionException {
        String eventDetail = act.substring(6);
        int eventDivision = eventDetail.indexOf("/");
        try {
            String eventDescription = eventDetail.substring(0, eventDivision - 1);
            String at = eventDetail.substring(eventDivision + 3);

            Task event = new Event(eventDescription, at);
            TaskList.taskList.add(event);
            String response = ("Got it. I've added this task: \n" + event.toString()
                    + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
            sto.saveData();
            return response;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(act);
        } catch (ParseException e) {
            throw new DukeIllegalTimeFormatException();
        }
    }
}
