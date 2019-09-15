package duke.main;

import duke.Duke;
import duke.command.*;
import duke.exception.DukeException;
import duke.exception.IncorrectNoteFormatException;
import duke.exception.InvalidInstructionException;
import duke.exception.InvalidNoteInstructionException;
import duke.note.Note;
import duke.task.Task;

import java.time.LocalDateTime;

/**
 * Parses Strings to identify different sub-commands in a given input command.
 */
public class Parser {
    private static String removeInstruction(String input) throws InvalidNoteInstructionException {
        try {
            return input.split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidNoteInstructionException("Please input a valid note instruction!");
        }
    }
    
    /**
     * Parses the instruction given in an input String.
     *
     * @param input The inputted String given by the user.
     * @return Returns the instruction in the given String.
     */
    private static String parseInstruction(String input) {
        return input.split(" ", 2)[0];
    }
    
    /**
     * Parses the index given in an input String.
     *
     * @param input The inputted String given by the user.
     * @return Returns the index integer value in the given String.
     */
    private static int parseIndex(String input) {
        return Integer.parseInt(input.split(" ", 2)[1]);
    }
    
    /**
     * Parses the inputted String for the regular expression to search for.
     *
     * @param input The inputted String that the user searches for.
     * @return Returns the expression searched for by the user, as a String.
     */
    private static String parseSearchPhrase(String input) {
        return input.split(" ", 2)[1];
    }
    
    /**
     * Parses the description given in an input String for a ToDoTask.
     *
     * @param input The inputted String given by the user.
     * @return Returns the description in the given String.
     */
    private static String parseToDoDescription(String input) {
        return input.split(" ", 2)[1];
    }
    
    /**
     * Parses the description given in an input String for a DeadlineTask or EventTask.
     *
     * @param input The inputted String given by the user.
     * @return Returns the description in the given String.
     */
    private static String parseNonToDoContent(String input) {
        return input.split("deadline|event", 2)[1];
    }
    
    /**
     * Parses the content in the description of an input String.
     *
     * @param input The inputted String given by the user.
     * @return Returns the content in the description of a given String.
     */
    private static String parseNonToDoDescription(String input) {
        return input.split("/by|/at", 2)[0].strip();
    }
    
    /**
     * Parses the time in the description of an input String.
     * Time is parsed as a LocalDateTime, and is assumed to be formatted correctly.
     *
     * @param input The inputted String given by the user.
     * @return Returns a LocalDateTime object representing the time of the Task.
     */
    private static LocalDateTime parseNonToDoTime(String input) {
        String taskTimeBeforeParse = input.split("/by|/at", 2)[1].strip();
        String[] taskTimeParsed = taskTimeBeforeParse.split("[ /]");
        return LocalDateTime.of(Integer.parseInt(taskTimeParsed[2]), Integer.parseInt(taskTimeParsed[1]),
                Integer.parseInt(taskTimeParsed[0]), Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
    }
    
    /**
     * Parses the data stored in the file saved on the hard drive.
     * Data is assumed to be formatted correctly.
     *
     * @param input The inputted String given by the user.
     * @return Returns a String array containing the values stored in the given formatted String.
     */
    static String[] parseStoredLine(String input) {
        return input.split(" \\| ");
    }
    
    /**
     * Parses the instruction in the String array of values.
     *
     * @param inputElements The inputted String array containing the stored values of the formatted String.
     * @return Returns the instruction value, as a String.
     */
    static String parseStoredInstruction(String[] inputElements) {
        return inputElements[0];
    }
    
    /**
     * Parses a Task to determine if it should be marked as done.
     *
     * @param inputElements The inputted String array containing the stored values of the formatted String.
     * @param task The given Task to be marked as done.
     */
    static void parseTaskForMarking(String[] inputElements, Task task) {
        if (inputElements[1].equals("+")) {
            task.markAsDone();
        }
    }
    
    /**
     * Parses the time of a given Task.
     * The Task is assumed to be correctly formatted.
     *
     * @param inputElements The inputted String array containing the stored values of the formatted String.
     * @return Returns a LocalDateTime object representing the date of the Task.
     */
    public static LocalDateTime parseStoredTime(String[] inputElements) {
        String[] taskTimeParsed = inputElements[3].split("[ /]");
        return LocalDateTime.of(Integer.parseInt(taskTimeParsed[2]),
                Integer.parseInt(taskTimeParsed[1]), Integer.parseInt(taskTimeParsed[0]),
                Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
    }
    
    private static Note parseWriteNote(String noteContents) throws IncorrectNoteFormatException {
        if (!noteContents.contains("|")) {
            throw new IncorrectNoteFormatException("Please write the note in the format "
                    + "'note write <note title> | <note contents>'!");
        } else {
            noteContents = noteContents.split(" ", 2)[1];
            String[] noteParsed = noteContents.split(" \\| ");
            return new Note(noteParsed[0], noteParsed[1]);
        }
    }
    
    private static Note parseReadNote(String notePath) {
        return new Note(notePath.split(" ", 2)[1]);
    }
    
    private static Note parseDeleteNote(String notePath) {
        return new Note(notePath.split(" ", 2)[1]);
    }
    
    public static Command parse(String input) throws DukeException {
        String instruction = Parser.parseInstruction(input);
        switch (instruction) {
        case "bye":
            Duke.isExitRunLoop = true;
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Parser.parseIndex(input));
        case "delete":
            return new DeleteCommand(Parser.parseIndex(input));
        case "find":
            return new FindCommand(Parser.parseSearchPhrase(input));
        case "todo":
            return new ToDoCommand(Parser.parseToDoDescription(input));
        case "deadline":
            String deadlineTaskContent = Parser.parseNonToDoContent(input);
            return new DeadlineCommand(Parser.parseNonToDoDescription(deadlineTaskContent),
                    Parser.parseNonToDoTime(deadlineTaskContent));
        case "event":
            String eventTaskContent = Parser.parseNonToDoContent(input);
            return new EventCommand(Parser.parseNonToDoDescription(eventTaskContent),
                    Parser.parseNonToDoTime(eventTaskContent));
        case "note":
            String noteContents = Parser.removeInstruction(input);
            String noteInstruction = Parser.parseInstruction(noteContents);
            switch (noteInstruction) {
            case "list":
                return new ListNoteCommand();
            case "write":
                return new WriteNoteCommand(Parser.parseWriteNote(noteContents));
            case "read":
                return new ReadNoteCommand(Parser.parseReadNote(noteContents));
            case "delete":
                return new DeleteNoteCommand(Parser.parseDeleteNote(noteContents));
            default:
                throw new InvalidNoteInstructionException("Please input a valid note instruction!");
            }
        default:
            throw new InvalidInstructionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
