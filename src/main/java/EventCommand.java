import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Event command is used to create Event tasks.
 */
public class EventCommand extends Command {

    private String taskName; 
    /**
     * Constructor for EventCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public EventCommand(String command, String taskName,TaskList taskList ){
        super(command, taskList);
        this.taskName = taskName;
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * Also creates a new event task.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand() throws DukeException{
        String [] deadlineArray1 = super.command.split("/");
        if(deadlineArray1.length < 2){
            throw new DukeException("☹ OOPS!!! The date of an event cannot be empty."); 
        }
        String newTaskName1 = taskName.split("/")[0];
        String [] timeArray = taskName.split(" ");
        String startingDateTimeString = timeArray[timeArray.length - 2];
        String endingDateTimeString = timeArray[timeArray.length - 1];
        try{
            LocalDateTime startingDateTime = LocalDateTime.parse(startingDateTimeString);
            LocalDateTime endingDateTime = LocalDateTime.parse(endingDateTimeString);
    
            EventsTask newTask2 = new EventsTask (false, newTaskName1, startingDateTime, endingDateTime);
            String toPrint2 = taskList.add(newTask2);
            return toPrint2;
        }catch(DateTimeParseException e){
            throw new DukeException("☹ OOPS!!! Date time format is wrong!");
        }
    }

    /**
     * 
     * @return a String detailing the process.
     */
    @Override
    public String execute(String processedCommand){
        return processedCommand;
    }
    
}