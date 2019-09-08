/**
 * Delete command is used to create Delete tasks.
 */
public class DeleteCommand extends Command {
    private Integer offset;
    
    /**
     * Constructor for DeleteCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public DeleteCommand(String command, Integer offset, TaskList taskList ){
        super(command, taskList);
        this.offset = offset;
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand(){
        return Integer.toString(offset);

    }

    /**
     * Calls the deleteTask method of the tasklist
     * 
     * @return a String detailing the process (i.e. task deleted)
     */
    @Override
    public String execute(String processedCommand){
        System.out.println(processedCommand);
        return taskList.deleteTask(Integer.parseInt(processedCommand));
    }
} 