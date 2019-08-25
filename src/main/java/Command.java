public abstract class Command {
    CommandType commandType;

    public CommandType getCommandType() {
        return commandType;
    }

    public abstract void execute();

}
