package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.TaskList;

/**
 * The parent class of other command classes.
 */
public class Command{
    protected String rawCommand;
    protected TaskList taskList;

    /**
     * Constructor for command class.
     *  
     * @param rawCommand takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public Command (String rawCommand, TaskList taskList){
        this.rawCommand = rawCommand;
        this.taskList = taskList;
    }
    
    /**
     * Processes the raw command. 
     * 
     * @return a cleaned command
     */
    public String processCommand() throws DukeException {
        return null;
    }

    /**
     * Executes the instructions as per the processedCommand.
     * 
     * @param processedCommand
     * @return a String detailing the process (e.g. task deleted)
     */
    public String execute(String processedCommand){
        return null;
    }

}