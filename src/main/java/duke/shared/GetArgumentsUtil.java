package duke.shared;

import duke.exception.DukeException;

import java.util.Arrays;
import java.util.List;

public class GetArgumentsUtil {

    /**
     * Retrieves two args from an arrays.
     * @param start is the start index where the method will sublist from
     * @param delimiter is the string that determine the 2 arguments
     * @param commands is the latest command inputted by the user
     * @return an array of 2 items, command description and sub-command(e.g. /at) description
     * @throws DukeException if user does not provide the sub-command / the sub-command does not have any description
     */
    public static String[] getTwoCommandArgs(int start, String delimiter, String[] commands) throws DukeException {
        String[] args = new String[2];
        List<String> commandList = Arrays.asList(commands);
        int indexOfSeparator = commandList.indexOf(delimiter);
        if (indexOfSeparator == commandList.size() - 1) {
            throw new DukeException(String.format(Messages.SUBDESCRIPTION_MISSING_EXCEPTION, delimiter));
        } else if (indexOfSeparator == -1) {
            throw new DukeException(String.format(Messages.NO_SUBDESCRIPTION_EXCEPTION, delimiter));
        } else {
            args[0] = concatStrings(commandList.subList(start, indexOfSeparator)
                    .toArray(new String[indexOfSeparator - 1]));
            args[1] = concatStrings(commandList.subList(indexOfSeparator + 1, commandList.size())
                    .toArray(new String[commandList.size() - (indexOfSeparator + 1)]));
        }
        return args;
    }

    /**
     * Concatenate the arguments (in array).
     * @param command is last command inputted by the user (in array)
     * @return argument of the command in String
     */
    public static String concatStrings(String[] command) {
        StringBuilder myStringBuilder = new StringBuilder();
        for (int i = 0; i < command.length; i++) {
            myStringBuilder.append(command[i]).append(" ");
        }
        myStringBuilder.deleteCharAt(myStringBuilder.length() - 1);
        return myStringBuilder.toString();
    }
}
