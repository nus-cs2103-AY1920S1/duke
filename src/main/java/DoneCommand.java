/**
 * Done command is used to create Done tasks.
 */
public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public DoneCommand(String command, TaskList taskList ){
        super(command, taskList);
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand(){
        String stringFromUser = super.command.replaceAll("\\D+","");
        Integer indexFromUser = Integer.parseInt(stringFromUser);
        return Integer.toString(indexFromUser);
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