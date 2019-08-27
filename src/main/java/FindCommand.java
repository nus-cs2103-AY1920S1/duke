import java.util.ArrayList;

public class FindCommand extends Command {

    String command;
    
    public FindCommand(String command){
        this.command = command;
    }

    public void execute(TaskList taskList){
        TaskList taskListsWithKeyWords = new TaskList();
        
        int length = taskList.size();

        for(int i = 0; i < length; i++){
            Task currentTask = taskList.getTask(i);

            if(currentTask.getMessage().contains(command)){
                taskListsWithKeyWords.add(currentTask);
            }
        }

        taskListsWithKeyWords.printTaskList();
    }
    
}