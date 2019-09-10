/**
 * Tag command is used tag the task.
 */
public class TagCommand extends Command {
    private String taskName;

    /**
     * Constructor for TagCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public TagCommand(String command, String taskName, TaskList taskList ){
        super(command, taskList);
        this.taskName = taskName;
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand(){
        String [] tagCommandArray = taskName.split(" ");
        String userInputIndex = tagCommandArray[0] ;

        Integer tagIndex = Integer.parseInt(userInputIndex) - 1;
        String tagName = tagCommandArray[1];
        String message = taskList.addTag(tagIndex, tagName);
        return message;
    }

    /**
     * Creates a new Todo task and adds that task to the tasklist
     * 
     * @return a String detailing the process (i.e. task added)
     */
    @Override
    public String execute(String processedCommand){
        return processedCommand;
    }
} 