/**
 * Parser class takes in the command passes the command to the correct 
 * command class.
 */
public class Parser{
    
    private Storage storage;
    private TaskList taskList;

    public Parser(Storage storage, TaskList taskList){
        this.storage = storage;
        this.taskList = taskList;

    }

    /**
     * The parse method is the 'main' method of the parser class and basically
     * a switch case statement that calls the right command based on the 
     * first word of the command (e.g. 'done'/'bye'/'list')
     * 
     * @param line takes in the command that the user types
     * @return a string that corresponds to the command given
     * @throws DukeException 
     */
    public String parse (String line) throws DukeException{
        switch (line){
            case "bye":
                return new Response( "Bye. Hope to see you again soon!").toString();
            
            case "list":
                return new Response(taskList.toString()).toString(); 

            default:
                String inputnoun = line.split(" ")[0];
                if (line.split(" ").length < 2){
                    throw new DukeException("☹ OOPS!!! I'm sorry, but the description of a task cannot be empty."); 

                }
                String taskName = line.split(" ", 2) [1];

                switch (inputnoun){    
                    case "done" :
                        Command doneCommand = new DoneCommand(line, taskList);
                        String processedDoneCommand = doneCommand.processCommand();
                        String doneResponse = doneCommand.execute(processedDoneCommand);
                        storage.save();
                        return new Response(doneResponse).toString();

                    case "todo" :
                        Command toDoCommand = new ToDoCommand(line, taskName, taskList);
                        String processedtoDoCommand = toDoCommand.processCommand();
                        String toDoResponse = toDoCommand.execute(processedtoDoCommand);
                        storage.save();
                        return new Response(toDoResponse).toString();

                    case "deadline" :
                        Command deadLineCommand = new DeadLineCommand(line, taskName, taskList);
                        String processeddeadLineCommand = deadLineCommand.processCommand();
                        String deadlineResponse = deadLineCommand.execute(processeddeadLineCommand);
                        storage.save();
                        return new Response(deadlineResponse).toString();
                        
                    case "event" :
                        Command eventCommand = new EventCommand(line, taskName, taskList);
                        String processedeventCommand = eventCommand.processCommand();
                        String eventResponse = eventCommand.execute(processedeventCommand);
                        storage.save();
                        return new Response(eventResponse).toString();
          
                    case "find" :
                        Command findCommand = new FindCommand(taskName, taskList);
                        String processedCommand = findCommand.processCommand();
                        String response = findCommand.execute(processedCommand);
                        return new Response(response).toString();
                        
                    case "delete" :
                        Command deleteCommand = new DeleteCommand(line, taskName, taskList);
                        String processedDeleteCommand = deleteCommand.processCommand();
                        String deleteResponse = deleteCommand.execute(processedDeleteCommand);
                        storage.save();
                        return new Response(deleteResponse).toString();
                        
                    case "tag":
                        Command TagCommand = new TagCommand(line, taskName, taskList);
                        String processedTagCommand = TagCommand.processCommand();
                        String tagResponse = TagCommand.execute(processedTagCommand);
                        return new Response(tagResponse).toString();

                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("); 
            }
        }
    }
}