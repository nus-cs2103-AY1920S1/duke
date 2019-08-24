package duke.command;

public abstract class AddCommand extends Command {
    /**
     * Notify the program to exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
