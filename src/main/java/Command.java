/**
 * The parent class of other command classes.
 */
public class Command{
    protected String command;
    protected TaskList taskList;

    /**
     * Constructor for command class.
     *  
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public Command (String command, TaskList taskList){
        this.command = command;
        this.taskList = taskList;
    }
    
    /**
     * Processes the raw command. 
     * 
     * @return a cleaned command
     */
    public String processCommand(){
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
    };

}