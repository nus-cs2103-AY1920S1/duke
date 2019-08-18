public class IncompleteInputException extends DukeException {
    public IncompleteInputException (String errorMessage) {
        super(DukeTextFormatter.makeFormattedText(String.format("You're lacking something in your '%s' command, frendo.", errorMessage)));
    }
}
