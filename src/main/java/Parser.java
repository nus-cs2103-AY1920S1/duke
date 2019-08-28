public class Parser {
    Parser(){
    }
    public static Command parse(String fullCommand) throws DukeException{
        if (fullCommand.equals("bye")){
            return new ByeTaskCommand();
        } else if (fullCommand.contains("delete")){
            return parseDeleteCommand(fullCommand);
        } else if (fullCommand.contains("todo") || fullCommand.contains("event") || fullCommand.contains("deadline")){
            return new AddTaskCommand(fullCommand);
        } else if (fullCommand.equals("list")) {
            return new ListTaskCommand();
        } else if(fullCommand.contains("done")){
            return parseDoneCommand(fullCommand);
        } else if(fullCommand.contains("find")){
            return new FindTaskCommand(fullCommand.split("find ")[1]);
        }
        else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    static Command parseDeleteCommand(String string){
        int index = Integer.parseInt(string.split("delete ")[1]);
        return new DeleteTaskCommand(index);
    }

    static Command parseDoneCommand(String string){
        int index = Integer.parseInt(string.split("done ")[1]);
        return new DoneTaskCommand(index);
    }

}
