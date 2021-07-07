package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.TaskList;
import duke.tasks.ToDoTask;

/**
 * ToDo command is used to create ToDo tasks.
 */
public class ToDoCommand extends Command {
    /**
     * Constructor for ToDoCommand class.
     * 
     * @param rawCommand takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public ToDoCommand(String rawCommand, TaskList taskList ){
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
        return taskName;
    }

    /**
     * Creates a new Todo task and adds that task to the tasklist
     * 
     * @return a String detailing the process (i.e. task added)
     */
    @Override
    public String execute(String processedCommand){
        ToDoTask newTask = new ToDoTask (false, processedCommand);
        String toPrint = taskList.add(newTask);
        return toPrint;
    }
} 