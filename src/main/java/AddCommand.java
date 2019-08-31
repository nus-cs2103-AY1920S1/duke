public class AddCommand extends Command {
    private String type;
    private String input;

    public AddCommand(String type, String input){
        this.type = type;
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    /**
     * Creates Task from stored task data
     * @param code Stored task data
     * @return Task
     */
    public static Task create(String[] code) {
        boolean done = "1".equals(code[1]);
        switch (code[0]) {
        case "T":
            return new Todo(code[2], done);
        case "D":
            return new Deadline(code[2], code[3], done);
        case "E":
            return new Event(code[2], code[3], done);
        default:
            return null;
        }
    }
}
