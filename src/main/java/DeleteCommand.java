/**
 * Delete command is used to create Delete tasks.
 */
public class DeleteCommand extends Command {
    private String taskName;

    /**
     * Constructor for DeleteCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public DeleteCommand(String command, String taskName, TaskList taskList ){
        super(command, taskList);
        this.taskName = taskName;
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand() throws DukeException{
        if(containsNonNumber(taskName)){
            throw new DukeException("☹ OOPS!!! You have chosen an invalid task number!");
        }else{
            int offset = Integer.parseInt(taskName) - 1;
            if(offset > taskList.size() - 1){
                throw new DukeException("☹ OOPS!!! There aren't so many tasks!"); 
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
        if(taskName.matches(".*[a-zA-Z]+.*") || taskName.contains("-")){
            return true; 
        }else{
            return false;
        }
    }
} 