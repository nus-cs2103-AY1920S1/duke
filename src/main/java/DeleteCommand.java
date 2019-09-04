/**
 * Delete command is used to create Delete tasks.
 */
public class DeleteCommand extends Command {
    
    /**
     * Constructor for DeleteCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public DeleteCommand(String command, TaskList taskList ){
        super(command, taskList);
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand(){
        String taskName = super.command.split(" ", 2) [1];
        int offset = Integer.parseInt(taskName) - 1;
        return Integer.toString(offset);

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
} 