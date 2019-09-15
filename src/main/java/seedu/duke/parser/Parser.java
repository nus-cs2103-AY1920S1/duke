package seedu.duke.parser;

import seedu.duke.command.*;

import seedu.duke.ui.Ui;

/**
 * Parser class helps to parse the user string.
 */
public class Parser {

    /**
     * Returns the first word of the string fullCommand.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return First word of a string, delimited by whitespaces, " ".
     */
    public static String parseCommand(String fullCommand) {
        return (fullCommand.split(" ")[0]);

    }

    /**
     * Returns the 2nd word for a command starting with "stats".
     *
     * @param fullCommand Input string from User.
     * @return Second word of user string.
     */
    public static String parseStatCommand(String fullCommand) {
        return (fullCommand.split(" ")[1]);
    }

    /**
     * Returns the todo task description of a Todo object.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Substring from index 5 (whitespace after "todo") to end of string.
     */
    public static String getTodoDescription(String fullCommand) {
        return (fullCommand.substring(5));
    }

    /**
     * Returns the deadline task description of a Deadline string.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Substring from index 9 (whitespace after "deadline") to the '/' char inside "/by".
     */
    public static String getDeadlineDescription(String fullCommand) {
        return (fullCommand.substring(9, fullCommand.indexOf('/')));
    }

    /**
     * Returns the unparsed DateTime string of a Deadline string.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Substring from 4 chars after '/' in "/by".
     */
    public static String getDeadlineDateTime(String fullCommand) {
        return (fullCommand.substring(4 + fullCommand.indexOf('/')));
    }

    /**
     * Returns the event task description of an Event string.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Substring from index 6 (whitespace after "event") to the '/' char inside "/at"
     */
    public static String getEventDescription(String fullCommand) {
        return (fullCommand.substring(6, fullCommand.lastIndexOf('/')));
    }

    /**
     * Returns the event location of an Event string.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Substring from 4 chars after '/' in "/at"
     */
    public static String getEventLocation(String fullCommand) {
        return (fullCommand.substring(4 + fullCommand.lastIndexOf('/')));
    }

    /**
     * Returns the specified task number for a "done" string command.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Int of the substring from index 5 ( whitespace after "done" string).
     */
    public static int getFinishedTaskNum(String fullCommand) {
        return (Integer.parseInt(fullCommand.substring(5)));

    }

    /**
     * Returns the specified task number for a "delete" string command.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Int of the substring from index 7 ( whitespace after "delete" string).
     */
    public static int getDeletedTaskNum(String fullCommand) {
        return (Integer.parseInt(fullCommand.substring(7)));
    }

    /**
     * Returns the task to find for "find" command.
     *
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Task to find.
     */
    public static String getFindTask(String fullCommand) {
        return (fullCommand.substring(5));

    }

    /**
     * Creates the command depending on the user string.
     *
     * @param fullCommand Input user String.
     * @param ui User Interface object.
     * @return Command object.
     */
    public static Command getCommand(String fullCommand, Ui ui) {
        String taskType = parseCommand(fullCommand);

        // Initialize commannd with UnknownCommand
        Command command = new UnknownCommand();

        switch (taskType) {

        case ("list"):

            command = new ListCommand();
            break;

        case ("done"):

            command = new DoneCommand();
            break;

        case("todo"):

            command = new TodoCommand();
            break;

        case("deadline"):

            command = new DeadlineCommand();
            break;

        case("event"):

            command = new EventCommand();
            break;

        case("delete"):

            command = new DeleteCommand();
            break;

        case("find"):

            command = new FindCommand();
            break;

        case("bye"):

            if (ui.isCommandLineInterface()) {
                command = new ByeCommandCli();
            } else if (ui.isGraphicalUserInterface()) {
                command = new ByeCommandGui();
            } else {
                command = new UnknownCommand();
            }

            break;

        case("stats"):

            String statCommandType = Parser.parseStatCommand(fullCommand);

            switch (statCommandType) {

            case ("all"):

                command = new StatsAllCommand();
                break;

            case ("reset"):

                command = new StatsResetCommand();
                break;

            case("event"):

                command = new StatsEventCommand();
                break;

            case("deadline"):

                command = new StatsDeadlineCommand();
                break;

            default:

                command = new UnknownCommand();
                break;
            }

            break;

        default:

            command = new UnknownCommand();
            break;

        }

        return command;
    }
}
