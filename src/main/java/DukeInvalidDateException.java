public class DukeInvalidDateException extends DukeException {
    String date;

    DukeInvalidDateException(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.date + " is an invalid date format!";
    }
}
