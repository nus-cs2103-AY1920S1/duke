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
    public String processCommand() throws DukeException{
        String userIndex = super.command.split(" ", 2)[1];
        
        if (containsNonNumber(userIndex)){
            throw new DukeException("☹ OOPS!!! You have chosen an invalid task number!");
        }else {
            String stringFromUser = super.command.replaceAll("\\D+","");
            Integer indexFromUser = Integer.parseInt(stringFromUser);

            if(indexFromUser > taskList.size() -1 || indexFromUser < 1 ){
                String taskListMaxIndex = Integer.toString(taskList.size()) ;
                throw new DukeException("☹ OOPS!!! Please choose a number from 1 to " +taskListMaxIndex );
            }else{
                return Integer.toString(indexFromUser);
            }
        }
    }

    public boolean containsNonNumber(String userIndex){
        if(userIndex.matches(".*[a-zA-Z]+.*") || userIndex.contains("-")){
            return true; 
        }else{
            return false;
        }
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