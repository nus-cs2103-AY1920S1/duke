import java.util.Random;

public class FunFactCommand extends Command{
    private int randomInt;

    public FunFactCommand() {
        super();
        this.randomInt = (new Random()).nextInt(20);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printFunFact() + "\n\t" + storage.getFunFact(randomInt);
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
