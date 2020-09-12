public class ShowHelpCommand extends Command {

    /**
     * Executes the command.
     *
     * @param tasks   list of tasks
     * @param ui      user interface
     * @param storage storage file
     * @throws DukeException exception specific to Duke application
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }

    @Override
    String executeForGui(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelpForGui();
    }
}
