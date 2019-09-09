public class Parser {
    public static Command parse(String fullCommand) throws DukeException{
        fullCommand = fullCommand.trim();
        int spacePosition = fullCommand.indexOf(" ");
        if(spacePosition == -1 && !(fullCommand.equals("list") || fullCommand.equals("bye"))){
            raiseSingleBadInput(fullCommand);
        }
        String[] taskInfo = fullCommand.split(" ", 2);
        switch(taskInfo[0]){
        case "todo":
        case "event":
        case "deadline":
            return new Command(taskInfo, true, false, false);
        case "delete":
            return new Command(taskInfo, false, true, false);
        case "done":
            return new Command(taskInfo, false, false, true);
        case "list":
        case "bye":
            return new Command(taskInfo, false, false, false);
        default:
            throw new DukeException("Command not recognized");
        }
    }

    private static void raiseSingleBadInput(String badInput) throws DukeException {
        if (badInput.equals("todo") || badInput.equals("event") || badInput.equals("deadline")) {
            throw new DukeException("No description for task.");
        } else if (badInput.equals("done") || badInput.equals("delete")) {
            throw new DukeException("Task index missing");
        } else {
            throw new DukeException("No such command");
        }
    }
}
