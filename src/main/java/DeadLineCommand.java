import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
/**
 * Deadline command is used to create DeadLine tasks.
 */
public class DeadLineCommand extends Command {

    private String taskName; 

    /**
     * Constructor for DeadLineCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public DeadLineCommand(String command, String taskName, TaskList taskList ){
        super(command, taskList);
        this.taskName = taskName;
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * Also creates the deadline task and executes it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand() throws DukeException{
        String [] deadlineArray = super.command.split("/by ");
        if(deadlineArray.length < 2){
            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty."); 
        }
        String deadLineTime = deadlineArray[deadlineArray.length - 1];
        try{
            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadLineTime);
            String newTaskName = taskName.split("/")[0];        
            DeadlinesTask newTask1 = new DeadlinesTask (false, newTaskName, deadlineDateTime);
            String toPrint1 = taskList.add(newTask1);    
            return toPrint1;
        }catch(DateTimeParseException e){
            throw new DukeException("☹ OOPS!!! Date time format is wrong!");
        }
    }

    /**
     * Returns the process.
     * 
     * @return a String detailing the process (i.e. task added)
     */
    @Override
    public String execute(String processedCommand){
        return processedCommand;
    }
    
}