import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public void execute(ListManager listManager, Ui ui, Storage storage) {
        String output = "";
        for (int i = 0; i < listManager.actualList.size(); i++) {
            output += listManager.actualList.get(i).saveText() + "\n";
        }
        try {
            storage.save(storage.filePath, output);
        } catch (IOException e) {
            System.out.println("Failed to save:" + e.getMessage());
        }
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
