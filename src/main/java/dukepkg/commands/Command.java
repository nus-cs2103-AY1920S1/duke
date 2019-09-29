package dukepkg.commands;
import dukepkg.*;
import dukepkg.exceptions.FormatException;

/**
 * The range of commands to interact with the bot.
 */
public abstract class Command {
    /**
     * The flag to check whether the command leads to a shutdown of system.
     */
    protected boolean isExit = false;

    /**
     * Parses the input line as a command to modify existing tasks.
     *
     * @param arr the input line passed in by user.
     * @return a command used for modifying existing tasks. Either a done command or a delete command.
     */
    public static Command getModifyExistingTaskCommand(String[] arr) {
        int index = Integer.parseInt(arr[1]) - 1;
        if(arr[0].equals("done")) {
            return new DoneCommand(index);
        } else {
            return new DeleteCommand(index);
        }
    }

    /**
     * Parses the input line as a command to add new tasks.
     *
     * @param arr the input line passed in by user.
     * @return a command used for adding new tasks. Either a todo command, a deadline command or a event command.
     * @throws FormatException when the user input is incompatible with existing command formats.
     */
    public static Command getAddTaskCommand(String[] arr) throws FormatException {
        Task t = new Todo(arr[1]);
        if (!arr[0].equals("todo")) {
            if(arr[0].equals("deadline")) {
                Parser.validateDeadlineFormat(arr);
                t = Parser.standardizeDeadlineTime(arr);
                return new DeadlineCommand(t);
            } else if(arr[0].equals("event")){
                Parser.validateEventFormat(arr);
                t = Parser.standardizeEventTime(arr);
                return new EventCommand(t);
            } else if (arr[0].equals("time")) {
                Parser.validateTimeFormat(arr);
                t = Parser.constructTimeCommand(arr);
                return new TimeCommand(t);
            }
        } else {
            return new TodoCommand(t);
        }
        return new TodoCommand(t);
    }

    /**
     * Construct a find task command that finds tasks based on keywords.
     *
     * @param arr the user input line
     * @return the find task command
     */
    public static Command getFindTaskCommand(String[] arr) {
        String[] keywords = arr[1].trim().split(" ");
        return new FindCommand(keywords);
    }

    /**
     * An abstract method to execute the user command.
     *
     * @param tasklist the TaskList object for adding and deleting tasks.
     * @param ui       the Ui object for raising prompts after executing tasks.
     * @return the string
     * @throws FormatException when the user input is incompatible with existing command formats.
     */
    public abstract String execute(TaskList tasklist, Ui ui) throws FormatException;

    /**
     * Flag of whether the program should terminate.
     *
     * @return the boolean.
     */
    public boolean isExit() {
        return isExit;
    }
}
