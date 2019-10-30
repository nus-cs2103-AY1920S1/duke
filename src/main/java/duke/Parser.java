package duke;

import duke.task.*;
import duke.command.*;

/**
 * Deals with making sense of the user command,
 * and simulates the supposed interaction from the command.
 */
public class Parser {

    /**
     * The parse method handles all input and output from the Scanner,
     * as well as their corresponding interactions through the Duke program.
     * @param input the string of command that is to given to Duke
     * @return the Command is to be executed with the parsed input
     */
    public static Command parse(String input) {
        String[] inputSplit = input.split(" ", 2);
        String cmdWord = inputSplit[0]; // extract first only
        String cmdLine; // variable to store the rest of input if any

        try {
            switch (cmdWord) {
            case "list":
                return new ListCommand();
            case "data":
                return new DataCommand();
            case "bye":
                return new ByeCommand();
            case "done":
                cmdLine = inputSplit[1]; // extract the rest of the line
                int taskNo = Integer.parseInt(cmdLine);
                return new DoneCommand(taskNo - 1); // taskIndex is -1 of taskNo
            case "delete":
                cmdLine = inputSplit[1]; // extract the rest of the line
                int taskNum = Integer.parseInt(cmdLine);
                return new DeleteCommand(taskNum - 1); // taskIndex is -1 of taskNum
            case "todo":
                cmdLine = inputSplit[1]; // extract the rest of the line
                if (cmdLine.isEmpty()) {
                    throw new ToDoException(cmdLine);
                } else {
                    ToDo td = new ToDo(cmdLine);
                    return new AddCommand(td);
                }
            case "deadline":
                cmdLine = inputSplit[1]; // extract the rest of the line
                if (cmdLine.isEmpty()) {
                    throw new DeadlineException(cmdLine, 3);
                } else if (!cmdLine.contains("/by")) {
                    throw new DeadlineException(cmdLine, 1);
                } else {
                    String[] cmdSplit = cmdLine.split("/by ");
                    if (cmdSplit.length <= 1) {
                        throw new DeadlineException(cmdLine, 3);
                    } else if (cmdSplit[0].equals(" ")) {
                        throw new DeadlineException(cmdLine, 2);
                    } else {
                        Deadline dl = new Deadline(cmdSplit[0], cmdSplit[1]);
                        return new AddCommand(dl);
                    }
                }
            case "event":
                cmdLine = inputSplit[1]; // extract the rest of the line
                if (cmdLine.isEmpty()) {
                    throw new EventException(cmdLine, 3);
                } else if (!cmdLine.contains("/at")) {
                    throw new EventException(cmdLine, 1);
                } else {
                    String[] cmdSplitt = cmdLine.split("/at ");
                    if (cmdSplitt.length <= 1) {
                        throw new EventException(cmdLine, 3);
                    } else if (cmdSplitt[0].equals(" ")) {
                        throw new EventException(cmdLine, 2);
                    } else {
                        Event ev = new Event(cmdSplitt[0], cmdSplitt[1]);
                        return new AddCommand(ev);
                    }
                }
            case "find":
                cmdLine = inputSplit[1]; // extract the rest of the line
                return new FindCommand(cmdLine);
            case "quote":
                return new QuoteCommand();
            default: // if it is not any of the above commands
                return new WrongCommand(input);
            }
        } catch (ToDoException e) {
            System.err.println(Ui.addLines(e.getMessage()));
        } catch (DeadlineException e) {
            System.err.println(Ui.addLines(e.getMessage()));
        } catch (EventException e) {
            System.err.println(Ui.addLines(e.getMessage()));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }
}