public class ToDoCommand extends Command {

    public ToDoCommand(String command, TaskList taskList ){
        super(command, taskList);
    }

    @Override
    public String processCommand(){
        String taskName = super.command.split(" ", 2) [1];
        return taskName;
    }

    @Override
    public String execute(String processedCommand){
        ToDoTask newTask = new ToDoTask (false, processedCommand);
        String toPrint = taskList.add(newTask);
        return toPrint;
    }
} 