public class AddDeadline extends AddCommand {
    private String type;
    private String input;

    public AddDeadline(String type, String input){
        super(type, input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
