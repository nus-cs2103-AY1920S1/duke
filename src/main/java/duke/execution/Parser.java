package duke.execution;
/**
 * Parser class of Duke.
 * Translates user input into a function.
 */
public class Parser {
    /**
     * Returns Command object that does what user input requires when executed
     * @param fullCommand full user input
     * @return Command object
     * @throws DukeException when user input is not formatted correctly/not recognized
     */
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
            return new Command(taskInfo, true, false, false, false);
        case "delete":
            return new Command(taskInfo, false, true, false, false);
        case "done":
            return new Command(taskInfo, false, false, true, false);
        case "find":
            return new Command(taskInfo, false, false, false, true);
        case "list":
        case "bye":
            return new Command(taskInfo, false, false, false, false);
        default:
            throw new DukeException("Command not recognized");
        }
    }

    /**
     * helper function to raise specific error when only 1 word is given as user input
     * @param badInput the erroneous user input
     * @throws DukeException thrown with a specific reason included in message
     */
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
