/**
 * Deadline command is used to create DeadLine tasks.
 */
public class DeadLineCommand extends Command {

    /**
     * Constructor for DeadLineCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public DeadLineCommand(String command, TaskList taskList ){
        super(command, taskList);
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * Also creates the deadline task and executes it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand(){
        String line = super.command;
        String taskName = line.split(" ", 2) [1];
        String [] deadlineArray = line.split("/");

        int date =  Integer.parseInt(deadlineArray[1].replace("by ", ""));
        String month = deadlineArray[2];
        int year = Integer.parseInt(deadlineArray[3]);
        int hour = Integer.parseInt(deadlineArray[4]);
        int min = Integer.parseInt(deadlineArray[5]);

        DateTime deadlineDateTime = new DateTime(hour, min, date, month, year);
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