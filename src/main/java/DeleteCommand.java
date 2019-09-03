public class DeleteCommand extends Command {

    public DeleteCommand(String command, TaskList taskList ){
        super(command, taskList);
    }

    @Override
    public String processCommand(){
        String taskName = super.command.split(" ", 2) [1];
        int offset = Integer.parseInt(taskName) - 1;
        return Integer.toString(offset);

    }

    @Override
    public String execute(String processedCommand){
        return taskList.deleteTask(Integer.parseInt(processedCommand));
    }
} 