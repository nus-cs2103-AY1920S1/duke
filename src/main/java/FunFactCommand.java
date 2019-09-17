import java.util.Random;

public class FunFactCommand extends Command{
    private int randomInt;

    /**
     * Constructor to create a funfact object
     */

    public FunFactCommand() {
        super();
        this.randomInt = (new Random()).nextInt(19);
    }

    /**
     * Retrieves and displays a random funfact from database
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getFunFact() + "\n\t" + storage.getFunFact(randomInt);
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
