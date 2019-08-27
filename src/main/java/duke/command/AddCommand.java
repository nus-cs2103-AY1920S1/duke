package duke.command;

public abstract class AddCommand extends Command {
    private String desc;

    public AddCommand(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
