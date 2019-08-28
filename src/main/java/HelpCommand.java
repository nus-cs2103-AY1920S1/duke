public class HelpCommand extends Command {
    public HelpCommand() {
        super();
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Helper helper = new Helper();
        ui.dukeEcho(helper.getCommands());
    }
}
