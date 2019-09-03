public class Command{
    protected String command;
    protected TaskList taskList;

    public Command (String command, TaskList taskList){
        this.command = command;
        this.taskList = taskList;
    }
    
    public String processCommand(){
        return null;
    }

    public String execute(String processedCommand){
        return null;
    };

}