import java.time.LocalDateTime;
/**
 * Deadline command is used to create DeadLine tasks.
 */
public class DeadLineCommand extends Command {

    private String taskName; 
    private String[] deadlineArray;

    /**
     * Constructor for DeadLineCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public DeadLineCommand(String command, String taskName, String[] deadlineArray, TaskList taskList ){
        super(command, taskList);
        this.taskName = taskName;
        this.deadlineArray = deadlineArray;
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * Also creates the deadline task and executes it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand(){
        String deadLineTime = deadlineArray[deadlineArray.length - 1];
        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadLineTime);
        
        String newTaskName = taskName.split("/")[0];        
        DeadlinesTask newTask1 = new DeadlinesTask (false, newTaskName, deadlineDateTime);
        String toPrint1 = taskList.add(newTask1);
        
        return toPrint1;
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