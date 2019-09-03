public class DoneCommand extends Command {

    public DoneCommand(String command, TaskList taskList ){
        super(command, taskList);
    }

    @Override
    public String processCommand(){
        String stringFromUser = super.command.replaceAll("\\D+","");
        Integer indexFromUser = Integer.parseInt(stringFromUser);
        return Integer.toString(indexFromUser);
    }

    @Override
    public String execute(String processedCommand){
        Integer indexFromUser = Integer.parseInt(processedCommand); 
        return taskList.doneTask(indexFromUser - 1);
    }
    
}