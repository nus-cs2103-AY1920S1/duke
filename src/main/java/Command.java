public abstract class Command {

    String input;
    boolean isExit = false;

    /**
     * Constructs new Command object.
     *
     * @param input input from user.
     */
    public Command(String input) {
        assert input.length() > 0 : "invalid input";
        this.input = input;
    }

    /**
     * Executes the action to be performed.
     *
     * @param tasks   current list of tasks.
     * @param ui      Ui object.
     * @param storage Storage object to save and load files.
     */
    abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Returns boolean object isExit to determine if program should exit.
     *
     * @return exit condition is true or false.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
