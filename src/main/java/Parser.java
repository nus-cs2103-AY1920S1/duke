public class Parser {
    public static Command parse(String toParse) throws DukeException{
        String[] tokens = toParse.split(" ");
        String commandType = tokens[0];
        Command cToReturn = null;
        String dateString = "";
        String timeString = "";
        String toAdd = "";
        switch (commandType) {
        case "list":
            cToReturn = new ListCommand();
            break;
        case "done":
            //"done" logic
            if(tokens.length < 2){
                throw new DukeException("☹ OOPS!!! Please specify task to complete");
            }
            int toComplete = Integer.parseInt(tokens[1]) - 1;
            cToReturn = new DoneCommand(toComplete);
            break;
        case "delete":
            //"delete" logic
            if(tokens.length < 2){
                throw new DukeException("☹ OOPS!!! Please specify task to delete");
            }
            int toDelete = Integer.parseInt(toParse.split(" ")[1]) - 1;
            cToReturn = new DeleteCommand(toDelete);
            break;
        case "todo":
            //toodo logic
            if (tokens.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            for (int j = 1; j < tokens.length; j++) {
                toAdd = toAdd + tokens[j] + " ";
            }
            cToReturn = new TodoCommand(toAdd.trim());
            break;
        case "deadline":
            //"deadline" logic
            if (tokens.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            boolean dateFlag = false;
            //Check if both date and time are specified
            if(toParse.split("/by")[1].trim().split(" ").length != 2){
                throw new DukeException("☹ OOPS!!! Date and Timing not specified correctly!");
            }
            for (int m = 1; m < tokens.length; m++) {
                if (tokens[m].equals("/by")) {
                    dateFlag = true;
                } else {
                    if (dateFlag == false) toAdd = toAdd + tokens[m] + " ";
                    else {
                        if (m == tokens.length - 1) {
                            timeString = tokens[m];
                        } else {
                            dateString = tokens[m];
                        }
                    }
                }
            }
            cToReturn = new DeadlineCommand(toAdd.trim(), dateString.trim(), timeString.trim());
            break;
        case "event":
            //"event" logic
            if(tokens.length == 1){
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            boolean timeFlag = false;
            //Check if both date and time are specified
            if(toParse.split("/at")[1].trim().split(" ").length != 2){
                throw new DukeException("☹ OOPS!!! Date and Timing not specified correctly!");
            }
            for (int z = 1; z < tokens.length; z++) {
                if (tokens[z].equals("/at")) timeFlag = true;
                else {
                    if (timeFlag == false) toAdd = toAdd + tokens[z] + " ";
                    else{
                        if(z == tokens.length - 1){
                            timeString = tokens[z];
                        } else {
                            dateString = tokens[z];
                        }
                    }
                }
            }
            cToReturn = new EventCommand(toAdd.trim(), dateString.trim(), timeString.trim());
            break;
        case "bye":
            cToReturn = new ByeCommand();
            break;
        default:
            //unrecognized command
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return cToReturn;
    }
}
