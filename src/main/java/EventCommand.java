import java.time.LocalDateTime;
/**
 * Event command is used to create Event tasks.
 */
public class EventCommand extends Command {

    private String taskName; 
    private String[] deadlineArray;
    /**
     * Constructor for EventCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public EventCommand(String command, String taskName, String [] deadlineArray,TaskList taskList ){
        super(command, taskList);
        this.taskName = taskName;
        this.deadlineArray = deadlineArray;
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * Also creates a new event task.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand(){

        String newTaskName1 = taskName.split("/")[0];
        String [] timeArray = taskName.split(" ");
        String startingDateTimeString = timeArray[timeArray.length - 2];
        String endingDateTimeString = timeArray[timeArray.length - 1];

        LocalDateTime startingDateTime = LocalDateTime.parse(startingDateTimeString);
        LocalDateTime endingDateTime = LocalDateTime.parse(endingDateTimeString);

        EventsTask newTask2 = new EventsTask (false, newTaskName1, startingDateTime, endingDateTime);
        String toPrint2 = taskList.add(newTask2);
        return toPrint2;
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