package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.TaskList;
import duke.tasks.DeadlinesTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
/**
 * Deadline command is used to create DeadLine tasks.
 */
public class DeadLineCommand extends Command {
    /**
     * Constructor for DeadLineCommand class.
     * 
     * @param rawCommand takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public DeadLineCommand(String rawCommand, TaskList taskList ){
        super(rawCommand, taskList);
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * Also creates the deadline task and executes it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand() throws DukeException {
        if (rawCommand.split(" ").length < 2){
            throw new DukeException("OOPS!!! I'm sorry, but the description of a task cannot be empty.");

        }
        String taskName = rawCommand.split(" ", 2) [1];
        String [] deadlineArray = super.rawCommand.split("/by ");
        
        if(deadlineArray.length < 2){
            throw new DukeException("OOPS!!! The date of a deadline cannot be empty.");
        }
        String deadLineTime = deadlineArray[deadlineArray.length - 1];
        try{
            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadLineTime);
            String newTaskName = taskName.split("/")[0];        
            DeadlinesTask newTask1 = new DeadlinesTask(false, newTaskName, deadlineDateTime);
            String toPrint1 = taskList.add(newTask1);    
            return toPrint1;
        }catch(DateTimeParseException e){
            throw new DukeException("OOPS!!! Date time format is wrong!");
        }
    }

    /**
     * Returns the process.
     * 
     * @return a String detailing the process (i.e. task added)
     */
    @Override
    public String execute(String processedCommand){
        return processedCommand;
    }
    
}