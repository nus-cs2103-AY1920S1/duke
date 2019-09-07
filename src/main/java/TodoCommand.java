public class TodoCommand extends AddCommand {

    public TodoCommand(String command) {
        super(new Todo(command));
    }

}
