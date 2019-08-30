/**
 * Parser deciphers the user input and breaks it down into meaningful parts that make a task.
 */
public class Parser {
    /**
     * The string input of the user.
     */
    protected String input;
    /**
     * The string command by the user.
     */
    protected String command;
    /**
     * The string array of the input broken down into its constituents.
     */
    protected String[] inputArr;

    /**
     * The task description of a todo task.
     */
    protected String todoDescript;

    /**
     * The string array of an input that is an event creation.
     */
    protected String[] eventCommand;
    /**
     * The task description of an event task.
     */
    protected String eventDescript;
    /**
     * The location of the event.
     */
    protected String at;

    /**
     * The string array of an input that is a deadline creation.
     */
    protected String[] deadlineCommand;
    /**
     * The task description of a deadline task.
     */
    protected String deadlineDescript;
    /**
     * The date of the deadline.
     */
    protected String by;

    public Parser(String input) {
        input = input.trim();
        String[] inputArr = input.split("\\s+");
        String command = inputArr[0];
        this.command = command;
        this.input = input;
        this.inputArr = inputArr;
    }

    /**
     * Breaks down the input into its significant parts, after the input has been identified as an event creation.
     */
    public void parseEvent() {
        this.eventCommand = this.input.split("/at");
        this.eventDescript = this.eventCommand[0].trim().substring(6);
        this.at = this.eventCommand[1].substring(1);
    }

    /**
     * Breaks down the input into its significant parts, after the input has been identified as a deadline creation.
     */
    public void parseDeadline() {
        this.deadlineCommand = this.input.split("/by");
        this.deadlineDescript = deadlineCommand[0].trim().substring(9);
        this.by = deadlineCommand[1].substring(1);
    }


    /**
     * Breaks down the input into its significant parts, after the input has been identified as a todo creation.
     */
    public void parseToDo() {
        this.todoDescript = this.input.substring(5);
    }
}