public class CommandList extends Command {

    public CommandList(String command) {
        super(command);
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) {
        sh.showList();
    }
}
