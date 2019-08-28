public class Parser {
    public Command getCommandFromLine(String line) throws InvalidCommandException {
        return Command.getFromString(line.split(" ")[0]);
    }

    public int getIndexFromLine(String line) {
        try {
            return Integer.parseInt(line.split(" ")[1]) - 1;
        }
        catch (Exception e) {
            return -1;
        }
    }

    public String getBeforeDelim(String line, String command, String delim) {
        try {
            return line.split(command)[1].split(delim)[0].trim();
        }
        catch (Exception e) {
            return "";
        }
    }

    public String getAfterDelim(String line, String command, String delim) {
        try {
            return line.split(command)[1].split(delim)[1].trim();
        }
        catch (Exception e) {
            return "";
        }
    }

    public boolean isNotByeCommand(String line) {
        return !line.equals(Command.BYE.toString());
    }

    public String getArg(String line, String command) {
        try {
            return line.split(command)[1];
        }
        catch (Exception e) {
            return "";
        }
    }
}
