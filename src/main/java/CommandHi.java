public class CommandHi extends Command {

    public CommandHi(String command) {
        super(command);
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) {
        ui.sayHi();
    }
}
