import java.io.IOException;

public class HelpCommand extends Command {

    public HelpCommand(String action) {
        super(action);
    }

    @Override
    public String execute(CompleteList errands, Ui ui, Storage storage) throws IOException {
        assert errands != null;
        assert ui != null;
        assert storage != null;
        return ui.printHelp();
    }
}

