package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.UI;

/**
 * Parser class takes in the command passes the command to the correct 
 * command class.
 */
public class Parser{
    
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Parser(Storage storage, TaskList taskList, UI ui){
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * The parse method is the 'main' method of the parser class and basically
     * a switch case statement that calls the right command based on the 
     * first word of the command (e.g. 'done'/'bye'/'list')
     * 
     * @param userInput takes in the command that the user types
     * @return a string that corresponds to the command given
     * @throws DukeException 
     */
    public String parse (String userInput) throws DukeException {
        String inputKeyWord = userInput.split(" ")[0];
        
        switch (inputKeyWord){
            case "bye":
                return ui.returnReponse("Bye. Hope to see you again soon!");
            
            case "list":
                return ui.returnReponse(taskList.toString()); 

            case "done" :
                Command doneCommand = new DoneCommand(userInput, taskList);
                String processedDoneCommand = doneCommand.processCommand();
                String doneResponse = doneCommand.execute(processedDoneCommand);
                storage.save();
                return ui.returnReponse(doneResponse);

            case "todo" :
                Command toDoCommand = new ToDoCommand(userInput, taskList);
                String processedtoDoCommand = toDoCommand.processCommand();
                String toDoResponse = toDoCommand.execute(processedtoDoCommand);
                storage.save();
                return ui.returnReponse(toDoResponse);

            case "deadline" :
                Command deadLineCommand = new DeadLineCommand(userInput, taskList);
                String processeddeadLineCommand = deadLineCommand.processCommand();
                String deadlineResponse = deadLineCommand.execute(processeddeadLineCommand);
                storage.save();
                return ui.returnReponse(deadlineResponse);
                
            case "event" :
                Command eventCommand = new EventCommand(userInput, taskList);
                String processedeventCommand = eventCommand.processCommand();
                String eventResponse = eventCommand.execute(processedeventCommand);
                storage.save();
                return ui.returnReponse(eventResponse);
    
            case "find" :
                Command findCommand = new FindCommand(userInput, taskList);
                String processedCommand = findCommand.processCommand();
                String response = findCommand.execute(processedCommand);
                return ui.returnReponse(response);
                
            case "delete" :
                Command deleteCommand = new DeleteCommand(userInput, taskList);
                String processedDeleteCommand = deleteCommand.processCommand();
                String deleteResponse = deleteCommand.execute(processedDeleteCommand);
                storage.save();
                return ui.returnReponse(deleteResponse);
                
            case "tag":
                Command TagCommand = new TagCommand(userInput, taskList);
                String processedTagCommand = TagCommand.processCommand();
                String tagResponse = TagCommand.execute(processedTagCommand);
                return ui.returnReponse(tagResponse);

            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            
        }
    }
}