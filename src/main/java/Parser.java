/**
 * Class to handle parsing of user input.
 */
public class Parser {

    /**
     * Given a line, obtains the command that was present in that line.
     * 
     * @param line The line input by the user
     * @return Command object representing the command that was present in the line
     * @throws InvalidCommandException If there is no valid Command in the line
     */
    public Command getCommandFromLine(String line) throws InvalidCommandException {
        return Command.getFromString(line.split(" ")[0].trim());
    }

    /**
     * Given a line, obtains the (numerical) index present in the line.
     * 
     * @param line The line input by the user
     * @return The int index present in the line
     */
    public int getIndexFromLine(String line) {
        try {
            return Integer.parseInt(line.split(" ")[1]) - 1;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Given a line, return the part of the line between the command and the provided delimiter.
     * 
     * @param line Line input by user
     * @param command Command text within line
     * @param delim Desired delimiter
     * @return String representation of the part of the line between the command and delimiter
     */
    public String getBeforeDelim(String line, String command, String delim) {
        try {
            return line.split(command)[1].split(delim)[0].trim();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Given a line, return the part of the line after the provided delimiter.
     * 
     * @param line Line input by user
     * @param command Command text within line
     * @param delim Desired delimiter
     * @return String representation of the part of the line after the delimiter
     */
    public String getAfterDelim(String line, String command, String delim) {
        try {
            return line.split(command)[1].split(delim)[1].trim();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Returns true if the provided line is not the BYE command.
     * 
     * @param line Line input by user
     * @return true if the provided line is not the BYE command, false otherwise
     */
    public boolean isNotByeCommand(String line) {
        return !line.trim().equals(Command.BYE.toString());
    }

    /**
     * Gets the argument from the line input by user, i.e the part of the line excluding the command
     * 
     * @param line Line input by user
     * @param command String representing the command within the line
     * @return The argument within the line
     */
    public String getArg(String line, String command) {
        try {
            return line.split(command)[1].trim();
        } catch (Exception e) {
            return "";
        }
    }
}
