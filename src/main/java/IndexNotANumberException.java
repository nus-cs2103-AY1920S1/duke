public class IndexNotANumberException extends DukeException {
    public IndexNotANumberException (String errorMessage) {
        super(DukeTextFormatter.makeFormattedText(String.format("Sorry, but I need a numerical value, not whatever \u0027%s\u0027 is.", errorMessage)));
    }
}
