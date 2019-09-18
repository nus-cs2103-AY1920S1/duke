package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.TaskList;
import duke.tasks.EventsTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
/**
 * Event command is used to create Event tasks.
 */
public class EventCommand extends Command {
    /**
     * Constructor for EventCommand class.
     * 
     * @param rawCommand takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public EventCommand(String rawCommand, TaskList taskList ){
        super(rawCommand, taskList);
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * Also creates a new event task.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand() throws DukeException {
        if (rawCommand.split(" ").length < 2){
            throw new DukeException("OOPS!!! I'm sorry, but the description of a task cannot be empty.");

        }

        String taskName = rawCommand.split(" ", 2) [1];
        String [] deadlineArray1 = super.rawCommand.split("/");
        if(deadlineArray1.length < 2){
            throw new DukeException("OOPS!!! The date of an event cannot be empty.");
        }
        String newTaskName1 = taskName.split("/")[0];
        String [] timeArray = taskName.split(" ");
        String startingDateTimeString = timeArray[timeArray.length - 2];
        String endingDateTimeString = timeArray[timeArray.length - 1];
        try{
            LocalDateTime startingDateTime = LocalDateTime.parse(startingDateTimeString);
            LocalDateTime endingDateTime = LocalDateTime.parse(endingDateTimeString);
            EventsTask newTask2 = new EventsTask (false, newTaskName1, startingDateTime, endingDateTime);
            String toPrint2 = taskList.add(newTask2);
            return toPrint2;
        }catch(DateTimeParseException e){
            throw new DukeException("OOPS!!! Date time format is wrong!");
        }
    }

    /**
     * 
     * @return a String detailing the process.
     */
    @Override
    public String execute(String processedCommand){
        return processedCommand;
    }
    
}