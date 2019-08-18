public class InvalidInputException extends DukeException {
    public InvalidInputException (String errorMessage) {
        super(DukeTextFormatter.makeFormattedText("Hmm-? Ah, yes! Haha, I totally get it!\n(Doesn't get it at all)"));
    }
}
