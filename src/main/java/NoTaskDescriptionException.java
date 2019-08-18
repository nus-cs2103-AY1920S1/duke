public class NoTaskDescriptionException extends DukeException {
    public NoTaskDescriptionException (String errorMessage) {
        super(DukeTextFormatter.makeFormattedText(String.format("Open up a little, won'tcha? Just '%s' ain't enough!", errorMessage)));
    }
}
