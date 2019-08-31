import java.text.ParseException;

public class AddCommand extends Command {
    private String command;
    private String input;

    public AddCommand(String command, String input){
        this.command = command;
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws UnsupportedOperationException {
        switch (command) {
        case "todo":
            return new Todo(input);
        case "deadline":
            String[] dead = Parser.process(input);
            return new Deadline(dead[0], Parser.getDate(dead[1]));
        case "event":
            String[] event = Parser.process(input);
            return new Event(event[0], Parser.getDate(dead[1]));
        default:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates Task from stored task data
     * @param code Stored task data
     * @return Task
     * @throws ParseException Error in stored date data
     */
    public static Task init(String[] code) throws ParseException {
        boolean done = "1".equals(code[1]);
        switch (code[0]) {
        case "T":
            return new Todo(code[2], done);
        case "D":
            return new Deadline(code[2], Parser.getAsDate(code[3]), done);
        case "E":
            return new Event(code[2], Parser.getAsDate(code[3]), done);
        default:
            return null;
        }
    }
}
