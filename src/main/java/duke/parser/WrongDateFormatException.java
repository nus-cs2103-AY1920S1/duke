package duke.parser;

import duke.command.UnknownCommandException;

public class WrongDateFormatException extends UnknownCommandException {
    private String date;

    /**
     * Constructor.
     * @param date - Given date format from user
     */
    public WrongDateFormatException(String date) {
        super(date);
        this.date = date;
    }

    @Override
    public String getMessage() {
        return "The format for \"" + this.date
                + "\" is wrong. The correct format for dates should be: "
                + "\"dd/MM/yyyy HHmm\"";
    }
}
