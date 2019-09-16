package duke;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.DuplicateCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.commands.ByeCommand;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

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
    public static int getTaskNum(String inputInstruction, Ui ui) throws DukeException {
        try {
            String[] taskNumString = inputInstruction.split(" ");
            int taskNum = Integer.parseInt(taskNumString[1]);
            return taskNum;
        } catch (NumberFormatException e) {
            ui.printException(e);
        }
        return -1;
    }


    /**
     * Returns a String of the DateTime, in the correct/preferred format.
     * @param stringInput string of a DateTime in the format that is found in the textFile
     * @return a String output of a reformatted String of the DateTime
     */
    public static String extractDateTime(String stringInput, String splitKey) {
        String timeDateString = stringInput.substring(stringInput.lastIndexOf(splitKey) + 5);
        return timeDateString.substring(0, timeDateString.length() - 1);
    }

    /**
     * Parses the a textFile if it exist. Upon application start up, if an
     * existing textFile is found, function will load the textFile line by line
     * and create the respective Task objects accordingly and store them.
     * @param stringInput a string of the Task input from the text file
     * @return a Task object that corresponds to the stringInput from the text file.
     * @throws DukeException for invalid input
     * @throws ParseException for invalid inputs
     */
    public static Task parseFileInput(String stringInput) throws ParseException, DukeException {
        String[] stringCommand = stringInput.split(" ");
        Task currentTask;
        switch (Character.toString(stringCommand[0].charAt(1))) {
        case "D":
            String deadlineDesc = stringInput.substring(7, stringInput.lastIndexOf("(by:") - 1);
            currentTask = new Deadline(deadlineDesc, Parser.extractDateTime(stringInput, "(by:"));
            break;
        case "E":
            String eventDesc = stringInput.substring(7, stringInput.lastIndexOf("(at:") - 1);
            currentTask = new Event(eventDesc, Parser.extractDateTime(stringInput, "(at:"));
            break;
        case "T":
            currentTask = new Todo(stringInput.substring(7));
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
     * @throws DukeException for invalid input
     */
    public static Command getCommand(String inputInstruction, TaskList currentTaskList, Ui ui) throws DukeException {
        String inputCommand = inputInstruction.split(" ")[0];
        switch (inputCommand.toLowerCase()) {
        case "list" :
            return new ListCommand();
        case "done" :
            return new DoneCommand(inputInstruction);
        case "todo" :
            if (currentTaskList.isDuplicatedTask(inputInstruction, "todo", ui)) {
                return new DuplicateCommand(inputInstruction);
            } else {
                return new TodoCommand(inputInstruction);
            }
        case "deadline" :
            if (currentTaskList.isDuplicatedTask(inputInstruction, "deadline", ui)) {
                return new DuplicateCommand(inputInstruction);
            } else {
                return new DeadlineCommand(inputInstruction);
            }
        case "event" :
            if (currentTaskList.isDuplicatedTask(inputInstruction, "event", ui)) {
                return new DuplicateCommand(inputInstruction);
            } else {
                return new EventCommand(inputInstruction);
            }
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
        System.out.println("________________________________________");
    }

}
