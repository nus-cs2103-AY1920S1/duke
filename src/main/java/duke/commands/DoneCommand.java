package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.TaskList;

/**
 * Done command is used to create Done tasks.
 */
public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand class.
     * 
     * @param rawCommand takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public DoneCommand(String rawCommand, TaskList taskList ){
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
        String userIndex = super.rawCommand.split(" ", 2)[1];
        
        if (containsNonNumber(userIndex)){
            throw new DukeException("OOPS!!! You have chosen an invalid task number!");
        }else {
            String stringFromUser = super.rawCommand.replaceAll("\\D+","");
            Integer indexFromUser = Integer.parseInt(stringFromUser);

            if(indexFromUser > taskList.size() || indexFromUser < 1 ){
                String taskListMaxIndex = Integer.toString(taskList.size()) ;
                throw new DukeException("OOPS!!! Please choose a number from 1 to " +taskListMaxIndex );
            }else{
                return Integer.toString(indexFromUser);
            }
        }
    }

    /**
     * Method to check whether user input contains any invalid string for donecommand.
     * 
     * @param userIndex
     * @return boolean depending on whether it contains an invalid done string.
     */
    public boolean containsNonNumber(String userIndex){
        return userIndex.matches(".*[a-zA-Z]+.*") || userIndex.contains("-");
    }

    /**
     * Calls the deleteTask method of the tasklist
     * 
     * @return a String detailing the process (i.e. task done)
     */
    @Override
    public String execute(String processedCommand){
        Integer indexFromUser = Integer.parseInt(processedCommand); 
        return taskList.doneTask(indexFromUser - 1);
    }
    
}