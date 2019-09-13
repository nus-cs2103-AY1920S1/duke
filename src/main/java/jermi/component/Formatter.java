package jermi.component;

import static jermi.misc.Constant.FORMATTER_BORDER;
import static jermi.misc.Constant.FORMATTER_COMMAND_DETAILS_DELIMITER;
import static jermi.misc.Constant.FORMATTER_COMMAND_DETAILS_SPLIT_LIMIT;
import static jermi.misc.Constant.FORMATTER_COMMAND_INDEX;
import static jermi.misc.Constant.FORMATTER_DEFAULT_DETAILS;
import static jermi.misc.Constant.FORMATTER_DETAILS_INDEX;
import static jermi.misc.Constant.FORMATTER_EXIT_MESSAGE;
import static jermi.misc.Constant.FORMATTER_MESSAGE_FORMAT;
import static jermi.misc.Constant.FORMATTER_ADD_BLANK_LINE;
import static jermi.misc.Constant.FORMATTER_WELCOME_MESSAGE_HELP;
import static jermi.misc.Constant.FORMATTER_WELCOME_MESSAGE_LINE_1;
import static jermi.misc.Constant.FORMATTER_WELCOME_MESSAGE_LINE_2;

/**
 * A class that deals with formatting I/O strings.
 */
public class Formatter {
    /**
     * Formats a message.
     *
     * @param message Message.
     * @return Formatted message.
     */
    private String formatMessage(String message) {
        return String.format(FORMATTER_MESSAGE_FORMAT, message);
    }

    /**
     * Returns formatted messages.
     * Takes in an arbitrary number of messages to be formatted.
     *
     * @param messages Message.
     * @return Formatted messages.
     */
    public String echo(String... messages) {
        StringBuilder toEcho = new StringBuilder(FORMATTER_BORDER);

        for (String message : messages) {
            toEcho.append(this.formatMessage(message));
        }
        toEcho.append(FORMATTER_BORDER);
        System.out.println(toEcho);
        return toEcho.toString();
    }

    /**
     * Returns welcome message.
     *
     * @return Welcome message.
     */
    public String greet() {
        return this.echo(FORMATTER_WELCOME_MESSAGE_LINE_1,
                FORMATTER_WELCOME_MESSAGE_LINE_2,
                FORMATTER_ADD_BLANK_LINE,
                FORMATTER_WELCOME_MESSAGE_HELP);
    }

    /**
     * Returns exit message.
     *
     * @return Exit message.
     */
    public String exit() {
        return this.echo(FORMATTER_EXIT_MESSAGE);
    }

    /**
     * Reads the entire user input and returns the input command.
     *
     * @param input User input.
     * @return Input command.
     */
    public String readCommand(String input) {
        return input.split(FORMATTER_COMMAND_DETAILS_DELIMITER,
                FORMATTER_COMMAND_DETAILS_SPLIT_LIMIT)[FORMATTER_COMMAND_INDEX];
    }

    /**
     * Reads the entire user input and returns the input details.
     *
     * @param input User input.
     * @return Input details.
     */
    public String readDetails(String input) {
        try {
            return input.split(FORMATTER_COMMAND_DETAILS_DELIMITER,
                    FORMATTER_COMMAND_DETAILS_SPLIT_LIMIT)[FORMATTER_DETAILS_INDEX];
        } catch (ArrayIndexOutOfBoundsException e) {
            return FORMATTER_DEFAULT_DETAILS;
        }
    }
}