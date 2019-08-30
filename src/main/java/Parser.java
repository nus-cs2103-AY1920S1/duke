public class Parser {
    protected String command;
    protected String input;
    protected String[] inputArr;

    protected String todoDescript;

    protected String[] eventCommand;
    protected String eventDescript;
    protected String at;

    protected String[] deadlineCommand;
    protected String deadlineDescript;
    protected String by;

    protected String search;

    public Parser(String input) {
        input = input.trim();
        String[] inputArr = input.split("\\s+");
        String command = inputArr[0];
        this.command = command;
        this.input = input;
        this.inputArr = inputArr;
    }

    public void parseEvent() {
        this.eventCommand = this.input.split("/at");
        this.eventDescript = this.eventCommand[0].trim().substring(6);
        this.at = this.eventCommand[1].substring(1);
    }

    public void parseDeadline() {
        this.deadlineCommand = this.input.split("/by");
        this.deadlineDescript = deadlineCommand[0].trim().substring(9);
        this.by = deadlineCommand[1].substring(1);
    }

    public void parseToDo() {
        this.todoDescript = this.input.substring(5);
    }

    public void parseFind() {
        this.search = this.input.substring(5);
    }
}