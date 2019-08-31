public class AddTodoCommand extends AddCommand {

    public AddTodoCommand(String line) {
        super(line);
        if(line.isBlank()) {
            throw new InvalidParameterException();
        } else {
            super.task = new Todo(line);
        }
    }

    public boolean isExit() {
        return false;
    }

}
