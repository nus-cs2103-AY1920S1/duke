public class StatisticsCommand extends Command {

    StatisticsCommand () {
        super();
    }

    /**
     * Executes the command.
     * Displays current statistics
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Statistics s = new Statistics(tasks);
        s.generateStatistics();
        return ui.getStatistics(s);
    }

    /**
     * Boolean to exit from program
     * @return true or false depending if we want to stop the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
