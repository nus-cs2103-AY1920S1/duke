public class Parser{
    
    private Storage storage;
    private TaskList taskList;

    public Parser(Storage storage, TaskList taskList){
        this.storage = storage;
        this.taskList = taskList;

    }

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
                        Command toDoCommand = new ToDoCommand(line, taskList);
                        String processedtoDoCommand = toDoCommand.processCommand();
                        String toDoResponse = toDoCommand.execute(processedtoDoCommand);
                        storage.save();
                        return new Response(toDoResponse).toString();

                    case "deadline" :
                        String [] deadlineArray = line.split("/");
                        if(deadlineArray.length < 2){
                            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty."); 
                        }
                        Command deadLineCommand = new DeadLineCommand(line, taskList);
                        String processeddeadLineCommand = deadLineCommand.processCommand();
                        String deadlineResponse = deadLineCommand.execute(processeddeadLineCommand);
                        storage.save();
                        return new Response(deadlineResponse).toString();
                        
                    case "event" :
                        String [] deadlineArray1 = line.split("/");
                        if(deadlineArray1.length < 2){
                            throw new DukeException("☹ OOPS!!! The date of an event cannot be empty."); 
                        }
                        Command eventCommand = new EventCommand(line, taskList);
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
                        int offset = Integer.parseInt(taskName) - 1;
                        if(offset > taskList.size() - 1){
                            throw new DukeException("☹ OOPS!!! There aren't so many tasks!"); 
                        }else{
                            Command deleteCommand = new DeleteCommand(taskName, taskList);
                            String processedDeleteCommand = deleteCommand.processCommand();
                            String deleteResponse = deleteCommand.execute(processedDeleteCommand);
                            storage.save();
                            return new Response(deleteResponse).toString();
                        }

                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("); 

            }
        }
    }
}