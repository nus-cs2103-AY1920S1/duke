public class IndexNotInRangeException extends DukeException {
    public IndexNotInRangeException (String errorMessage) {
        super(DukeTextFormatter.makeFormattedText(String.format("I'm no good at math, but are you sure '%s' is on your list?", errorMessage)));
    }
}
