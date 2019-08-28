public class Parser {
    Parser(){
    }

    /**
     * This method takes in exactly what the user types and determine if should return a command or an error
     * @param fullCommand string of the input
     * @return  the relevant command for that string
     * @throws DukeException this error will be raised if the user does not enter a valid input
     */
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

    /**
     * Helper function to get the relevant command
     * @param string input
     * @return command
     */
    static Command parseDeleteCommand(String string){
        int index = Integer.parseInt(string.split("delete ")[1]);
        return new DeleteTaskCommand(index);
    }
    /**
     * Helper function to get the relevant command
     * @param string input
     * @return command
     */
    static Command parseDoneCommand(String string){
        int index = Integer.parseInt(string.split("done ")[1]);
        return new DoneTaskCommand(index);
    }

}
