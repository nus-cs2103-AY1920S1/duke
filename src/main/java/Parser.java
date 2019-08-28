public class Parser {
    public Command getCommandFromLine(String line) throws InvalidCommandException {
        return Command.getFromString(line.split(" ")[0]);
    }

    public int getIndexFromLine(String line) {
        return Integer.parseInt(line.split(" ")[1]) - 1;
    }

    public String getBeforeDelim(String line, String command, String delim) {
        return line.split(command)[1].split(delim)[0].trim();
    }

    public String getAfterDelim(String line, String command, String delim) {
        return line.split(command)[1].split(delim)[1].trim();
    }

    public String getArg(String line, String command) {
        return line.split(command)[1];
    }
}
