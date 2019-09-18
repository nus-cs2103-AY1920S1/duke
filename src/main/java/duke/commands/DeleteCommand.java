package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.TaskList;

/**
 * Delete command is used to create Delete tasks.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand class.
     * 
     * @param rawCommand takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public DeleteCommand(String rawCommand, TaskList taskList ){
        super(rawCommand, taskList);
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand() throws DukeException {
        if (rawCommand.split(" ").length < 2){
            throw new DukeException("OOPS!!! I'm sorry, but the description of a task cannot be empty.");

        }
        String taskName = rawCommand.split(" ", 2) [1];

        if(containsNonNumber(taskName)){
            throw new DukeException("OOPS!!! You have chosen an invalid task number!");
        }else{
            int offset = Integer.parseInt(taskName) - 1;
            if(offset > taskList.size() - 1){
                throw new DukeException("OOPS!!! There aren't so many tasks!");
            }else{
                return Integer.toString(offset);
            }
        }
    }

    /**
     * Calls the deleteTask method of the tasklist
     * 
     * @return a String detailing the process (i.e. task deleted)
     */
    @Override
    public String execute(String processedCommand){
        return taskList.deleteTask(Integer.parseInt(processedCommand));
    }

    public boolean containsNonNumber(String taskName){
        return taskName.matches(".*[a-zA-Z]+.*") || taskName.contains("-");
    }
} 