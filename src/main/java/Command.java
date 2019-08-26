public abstract class Command {

    String fullCommand;
    String[] splitCommand;

    public Command(String fullCommand, String[] splitCommand) {
        this.fullCommand = fullCommand;
        this.splitCommand = splitCommand;
    }
    abstract void execute(ListManager listManager, Ui ui, Storage storage);
    abstract boolean isExit();
}