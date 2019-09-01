package duke.exception;

public class MissingKeywordException extends DukeException {

    @Override
    public String toString() {
        return String.format("%s Keyword cannot be found!", super.toString());
    }
}
