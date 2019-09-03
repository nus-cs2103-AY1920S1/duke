public class FindCommand extends Command {

    public FindCommand(String command, TaskList taskList ){
        super(command, taskList);
    }

    @Override
    public String processCommand(){
        return super.command;
    }

    @Override
    public String execute(String processedCommand){
        TaskList taskListsWithKeyWords = new TaskList();
        
        int length = super.taskList.size();

        for(int i = 0; i < length; i++){
            Task currentTask = taskList.getTask(i);

            if(currentTask.getMessage().contains(command)){
                taskListsWithKeyWords.add(currentTask);
            }
        }
        return taskListsWithKeyWords.toString();
    }
    
}