public class CompleteTaskException extends DukeException {
    public CompleteTaskException() {
        super("Sorry, you don't have that task in the list to complete.");
    }
}
