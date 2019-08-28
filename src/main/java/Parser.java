public class Parser {
    public Command getCommandFromLine(String line) throws InvalidCommandException {
        return Command.getFromString(line.split(" ")[0]);
    }

    public int getIndexFromLine(String line) {
        return Integer.parseInt(line.split(" ")[1]) - 1;
    }
}