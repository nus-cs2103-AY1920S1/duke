import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Parser {

	private static String action;
    private static int index;
    private static String nameOfTask; 
    private static String dateStr;
    private static String wordToFind;

	/**
	 * Returns the correct command from user input
	 * If command is invalid or incomplete, throw exception
	 *
	 * @param fullCommand String of command read from user input
	 * @return Command
	 * @throws DukeException If invalid or incomplete command is parsed
	 */
    public static Command parse(String fullCommand) throws DukeException {

    	action = fullCommand.split(" ")[0];

    	if (action.equals("list")) {
            index = -1;
            nameOfTask = "";
            dateStr = "";
			wordToFind = "";
    	} else if (action.equals("delete")) {
            nameOfTask = "";
            dateStr = "";
			wordToFind = "";
    		try {
                index = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
    		} catch (NumberFormatException e) {
                throw new InvalidCommandException("Invalid Command");
    		} catch (IndexOutOfBoundsException e) {
    			throw new IncompleteCommandException("Incomplete Command");
    		}
    	} else if (action.equals("done")) {
            nameOfTask = "";
            dateStr = "";
			wordToFind = "";
    		try {
                index = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
    		} catch (NumberFormatException e) {
                throw new InvalidCommandException("Invalid Command");
    		} catch (IndexOutOfBoundsException e) {
    			throw new IncompleteCommandException("Incomplete Command");
    		}
    	} else if (action.equals("bye")) {
    		index = -1;
            nameOfTask = "";
            dateStr = "";
			wordToFind = "";
    	} else if (action.equals("todo")) {
    		index = -1;
            dateStr = "";
			wordToFind = "";
    		try {
    			nameOfTask = fullCommand.split(" ", 2)[1];
    		} catch (IndexOutOfBoundsException e) {
    			throw new IncompleteCommandException("Incomplete Command");
    		}
    	} else if (action.equals("deadline")) {
    		index = -1;
			wordToFind = "";
    		try {
    			nameOfTask = fullCommand.split(" ", 2)[1].split("/by")[0];
    			dateStr = fullCommand.split(" ", 2)[1].split("/by")[1];
    		} catch (IndexOutOfBoundsException e) {
    			throw new IncompleteCommandException("Incomplete Command");
    		}    		
    	} else if (action.equals("event")) {
    		index = -1;
			wordToFind = "";
    		try {
    			nameOfTask = fullCommand.split(" ", 2)[1].split("/at")[0];
    			dateStr = fullCommand.split(" ", 2)[1].split("/at")[1];
    		} catch (IndexOutOfBoundsException e) {
    			throw new IncompleteCommandException("Incomplete Command");
    		} 
    	} else if (action.equals("find")) {
			index = -1;
			nameOfTask = "";
			dateStr = "";
			try {
				wordToFind = fullCommand.split(" ")[1];
			} catch (IndexOutOfBoundsException e) {
				throw new IncompleteCommandException("Incomplete Command");
			}
		} else {
    		throw new InvalidCommandException("Invalid Command");
    	}
        
        String d = dateStr;
    	if (!d.equals("")) {
    		try {
                Date date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(dateStr);
    		} catch (ParseException e) {
    			throw new DukeException("Wrong date and time format");
    		}
    	}

        return new Command(action, index, nameOfTask, dateStr, wordToFind);
    }
}
