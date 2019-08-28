import java.text.ParseException;

public class DeadlineTask extends Task {

    public DeadlineTask(String description, String deadline) throws WrongDateFormatException {
        super(description);
        try {
            this.dateTime = Consts.DATE_TIME_FORMATTER.parse(deadline);
        } catch (ParseException e) {
            throw new WrongDateFormatException();
        }
        this.type = "D";
    }

    public DeadlineTask(String description, String deadline, boolean isDone) throws WrongDateFormatException {
        super(description, isDone);
        try {
            this.dateTime = Consts.DATE_TIME_FORMATTER.parse(deadline);
        } catch (ParseException e) {
            throw new WrongDateFormatException();
        }
        this.type = "D";
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Consts.DATE_TIME_OUTPUT_FORMATTER.format(dateTime));
    }
}
