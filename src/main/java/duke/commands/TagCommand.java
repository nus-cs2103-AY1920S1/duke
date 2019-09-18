package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.TaskList;

/**
 * Tag command is used tag the task.
 */
public class TagCommand extends Command {
    /**
     * Constructor for TagCommand class.
     * 
     * @param rawCommand takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public TagCommand(String rawCommand, TaskList taskList ){
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
        String [] tagCommandArray = taskName.split(" ");
        String userInputIndex = tagCommandArray[0] ;

        Integer tagIndex = Integer.parseInt(userInputIndex) - 1;
        String tagName = tagCommandArray[1];
        if(! tagName.contains("#")){
            throw new DukeException("Tag should contain the # at the start!");
        }else{
            String message = taskList.addTag(tagIndex, tagName);
            return message;
        }
    }

    /**
     * Creates a new Todo task and adds that task to the tasklist
     * 
     * @return a String detailing the process (i.e. task added)
     */
    @Override
    public String execute(String processedCommand){
        return processedCommand;
    }
} 