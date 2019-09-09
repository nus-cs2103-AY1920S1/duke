package duke;
import duke.Commands.Command;
import duke.Commands.DeadlineCommand;
import duke.Commands.DeleteCommand;
import duke.Commands.DoneCommand;
import duke.Commands.EventCommand;
import duke.Commands.FindCommand;
import duke.Commands.InvalidCommand;
import duke.Commands.ListCommand;
import duke.Commands.TodoCommand;
import duke.Commands.ByeCommand;
import duke.Exceptions.DukeException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

import java.text.ParseException;


/**
 * Represents a Parser Class which is used to process the user
 * inputs to allow the rest of the program to use the processed
 * information.
 */

public class Parser {

    /**
     * Returns the task index from the inputInstructions. Helps to identify the index number
     * @param inputInstruction user string input which includes task index and description
     * @return the task index after separating from the rest of the string
     */
    public static int getTaskNum(String inputInstruction) {
        String[] taskNumString = inputInstruction.split(" ");
        int taskNum = Integer.parseInt(taskNumString[1]);
        return taskNum;
    }


    /**
     * Returns a String of the DateTime, in the correct/preferred format.
     * @param stringInput string of a DateTime in the format that is found in the textFile
     * @return a String output of a reformatted String of the DateTime
     */
    public static String extractDateTime(String stringInput) {
        String[] stringSplit = stringInput.split(" ");
        String timeDateString = stringSplit[4] + " " + stringSplit[5];
        return timeDateString.substring(0, timeDateString.length() - 1);
    }

    /**
     * Parses the a textFile if it exist. Upon application start up, if an
     * existing textFile is found, function will load the textFile line by line
     * and create the respective Task objects accordingly and store them.
     * @param stringInput a string of the Task input from the text file
     * @return a Task object that corresponds to the stringInput from the text file.
     */
    public static Task parseFileInput(String stringInput) throws ParseException, DukeException {
        String[] stringCommand = stringInput.split(" ");
        Task currentTask;
        switch (Character.toString(stringCommand[0].charAt(1))) {
        case "D":
            currentTask = new Deadline(stringCommand[1], Parser.extractDateTime(stringInput));
            break;
        case "E":
            currentTask = new Event(stringCommand[1], Parser.extractDateTime(stringInput));
            break;
        case "T":
            currentTask = new Todo(stringCommand[1]);
            break;
        default:
            throw new DukeException("file");
        }
        if (Character.toString(stringCommand[0].charAt(4)).equals("O")) {
            currentTask.markAsDone();
        }
        return currentTask;
    }

    /**
     * returns a Command Object according to the user instruction.
     * @param inputInstruction a string input by the user do different commands/task
     * @return a Command Object according to the user input
     */
    public static Command getCommand(String inputInstruction) throws DukeException {
        String inputCommand = inputInstruction.split(" ")[0];
        switch (inputCommand) {
        case "list" :
            return new ListCommand();
        case "done" :
            return new DoneCommand(Parser.getTaskNum(inputInstruction));
        case "todo" :
            return new TodoCommand(inputInstruction);
        case "deadline" :
            return new DeadlineCommand(inputInstruction);
        case "event" :
            return new EventCommand(inputInstruction);
        case "delete" :
            return new DeleteCommand(inputInstruction);
        case "find" :
            return new FindCommand(inputInstruction);
        case "bye" :
            return new ByeCommand();
        default :
            return new InvalidCommand();
        }

    }

    public static void printLine() {
        System.out.println("___________________________________");
    }

}
