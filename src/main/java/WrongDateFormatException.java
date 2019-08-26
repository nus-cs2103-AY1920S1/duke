public class WrongDateFormatException extends UnknownCommandException {
    private String date;

    public WrongDateFormatException(String date) {
        super(date);
        this.date = date;
    }

    @Override
    public String getMessage() {
        return "The format for \"" + this.date + "\" is wrong. The correct format for dates should be: " +
                "\"dd/MM/yyyy HHmm\"";
    }
}
