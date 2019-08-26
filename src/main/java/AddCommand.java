public class AddCommand extends Command {

    protected String desc;
    protected String dateTime;

    public AddCommand(String command, String desc) {
        super(command);
        this.desc = desc;
    }

    public AddCommand(String command, String desc, String dateTime) {
        super(command);
        this.desc = desc;
        this.dateTime = dateTime;
    }

    public void execute() {

    }
}
