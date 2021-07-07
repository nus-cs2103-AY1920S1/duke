package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;
/**
 * Find command is used to create find tasks.
 */
public class FindCommand extends Command {

    /**
     * Constructor for FindCommand class.
     * 
     * @param rawCommand takes in the raw commmand
     * @param taskList taskList is used to store tasks
     */
    public FindCommand(String rawCommand, TaskList taskList ){
        super(rawCommand, taskList);
    }

    /**
     * Takes in 'dirty' string and cleans it.
     * 
     * @return a string containing the procesedCommand
     */
    @Override
    public String processCommand() throws DukeException {
        if (rawCommand.split(" ").length < 2){
            throw new DukeException("OOPS!!! I'm sorry, but the description of a task cannot be empty.");

        }
        String taskName = rawCommand.split(" ", 2) [1];

        return taskName;
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
            return executeTagCommand(processedCommand);
        }else{
            return executeNormalFindCommand(processedCommand);
        }
    }

    /**
     * Abstraction of finding a tag. 
     * 
     * @param tagName
     * @return string containing the tasks that contain this tag.
     */
    public String executeTagCommand(String tagName){
        TaskList taskListsWithKeyWords = new TaskList();
        int length = super.taskList.size();
        for(int i = 0; i < length; i++){
            Task currentTask = taskList.getTask(i);
            ArrayList <String> tagList = currentTask.getTagList();

            for (int j= 0; j < tagList.size(); j ++){
                if(tagList.get(j).equals(tagName)){
                    taskListsWithKeyWords.add(currentTask);
                    break;
                }
            }
        }

        if(taskListsWithKeyWords.size() < 1){
            return "Cannot find any tasks with that tag! ";
        }else{
            return taskListsWithKeyWords.toString();
        }
    }

    /**
     * Abstraction of finding a string. 
     * 
     * @param tagName
     * @return string containing the tasks that contain this string.
     */
    public String executeNormalFindCommand(String tagName){
        TaskList taskListsWithKeyWords = new TaskList();
        int length = super.taskList.size();
        for(int i = 0; i < length; i++){
            Task currentTask = taskList.getTask(i);

            if(currentTask.getMessage().contains(tagName)){
                taskListsWithKeyWords.add(currentTask);
            }
        }

        if(taskListsWithKeyWords.size() < 1){
            return "Cannot find any tasks with that keyword! ";
        }else{
            return taskListsWithKeyWords.toString();
        }
    }
    
}