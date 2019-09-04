/**
 * Event command is used to create Event tasks.
 */
public class EventCommand extends Command {

    /**
     * Constructor for EventCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public EventCommand(String command, TaskList taskList ){
        super(command, taskList);
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * Also creates a new event task.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand(){
        String line = super.command;
        String taskName = line.split(" ", 2) [1];
        String [] deadlineArray = line.split("/");
        String newTaskName1 = taskName.split("/")[0];

        int startingDate =  Integer.parseInt(deadlineArray[1].replace("at ", ""));
        String startingMonth = deadlineArray[2];
        int startingYear = Integer.parseInt(deadlineArray[3]);
        int startingHour = Integer.parseInt(deadlineArray[4]);
        int startingMin = Integer.parseInt(deadlineArray[5]);

        DateTime startingDateTime = new DateTime(startingHour, startingMin, startingDate, startingMonth, startingYear);

        int endingDate =  Integer.parseInt(deadlineArray[6]);
        String endingMonth = deadlineArray[7];
        int endingYear = Integer.parseInt(deadlineArray[8]);
        int endingHour = Integer.parseInt(deadlineArray[9]);
        int endingMin = Integer.parseInt(deadlineArray[10]);

        DateTime endingDateTime = new DateTime(endingHour, endingMin, endingDate, endingMonth, endingYear);

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