package duke.command;

import duke.Ui;
import duke.exceptions.DukeException;
import duke.formats.DateTime;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;

import java.io.IOException;

public class AddEventCommand extends Command {

    private Event event;

    public AddEventCommand(String [] commandArray) throws DukeException {
        String eventName = "";
        int i = 1;
        while(!commandArray[i].equals("/at")){
            if(i >= commandArray.length - 1){
                throw new DukeException("☹ OOPS!!! The '/at' sequence couldn't be found.");
            }
            eventName += " " + commandArray[i];
            i++;
        }
        eventName += " ";
        i++;
        String eventDuration = "";
        if(i >= commandArray.length){
            throw new DukeException("☹ OOPS!!! The event timing must be specified.");
        }
        boolean isFirstWord = true;
        while(i < commandArray.length){
            if(!isFirstWord){
                eventDuration += " ";
            }
            eventDuration += commandArray[i];
            i++;
            isFirstWord = false;
        }
        DateTime dateTime = new DateTime(eventDuration);
        this.event = new Event(eventName, false, dateTime.toString());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(event);
        ui.showAddTask(event, tasks.getSize());
        try{
            storage.writeToFile(event.toFile());
        } catch (IOException e){
            ui.showIOException(e);
        }
    }
}
