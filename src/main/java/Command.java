import java.util.ArrayList;

public enum Command {
    EXIT(0),
    LIST(0),
    DELETE(1, "task number"),
    DONE(1, "task number"),
    TODO(1, "description"),
    DEADLINE(2, "description", "time", " /by "),
    EVENT(2, "description", "time", " /at ");

    private Command(int parametersExpected, String... parameterNamesAndDelimiters) {
        this.parametersExpected = parametersExpected;
        this.parameters = new ArrayList<String>();
        this.delimiters = new ArrayList<String>();
        for (int i = 0; i < parameterNamesAndDelimiters.length; i++) {
            if (i < parametersExpected) {
                this.parameters.add(parameterNamesAndDelimiters[i]);
            } else {
                this.delimiters.add(parameterNamesAndDelimiters[i]);
            }
        }
    }

    public final int parametersExpected;
    public ArrayList<String> parameters, delimiters;

    public static Command fromString(String command) throws DukeException {
        switch(command) {
            case "":
                throw new DukeMissingCommandException();
            case "bye":
                return EXIT;
            case "list":
                return LIST;
            case "delete":
                return DELETE;
            case "done":
                return DONE;
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            default:
                throw new DukeUnknownCommandException();
        }
    }
}
