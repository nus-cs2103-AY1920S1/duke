package error.task;

public class UnknownDateTimeException extends TaskCreationException {
    @Override
    public String getTaskErrorMessage() {
        return "☹ OOPS!!! Did you enter a valid date and time?" +
                "\n" +
                "Please use the format: dd/mm/yyyy HHmm" +
                "\n" +
                "e.g. 20/02/2020 2240";
    }
}
