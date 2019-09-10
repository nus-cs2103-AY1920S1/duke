import java.util.Arrays;
import java.util.*;

/**
 * Find command is used to create find tasks.
 */
public class FindCommand extends Command {

    /**
     * Constructor for FindCommand class.
     * 
     * @param command takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public FindCommand(String command, TaskList taskList ){
        super(command, taskList);
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand(){
        return super.command;
    }

    /**
     * Calls the getTask method of the tasklist.
     * Adds the task to a new taskList if the task contains the keyword
     * 
     * @return a String detailing the process (i.e. task deleted)
     */
    @Override
    public String execute(String processedCommand){
        if(processedCommand.contains("#")){
            return executeTagCommand(processedCommand.split(" ")[1]);
        }else{
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

    public String executeTagCommand(String tagName){
        System.out.println(tagName);
        TaskList taskListsWithKeyWords = new TaskList();
            int length = super.taskList.size();
            for(int i = 0; i < length; i++){
                Task currentTask = taskList.getTask(i);
                ArrayList <String> tagList = currentTask.getTagList();

                for (int j= 0; j < tagList.size(); j ++){
                    if(tagList.get(j).equals(tagName)){
                        taskListsWithKeyWords.add(currentTask);
                        System.out.println("yes");
                        break;
                    }
                }
            }
            return taskListsWithKeyWords.toString();
    }
    
}