public class WrongDateFormatException extends DukeException {

    @Override
    public String toString() {
        return String.format("%s Please follow the correct datetime format(dd/mm/yyyy HHMM)", super.toString());
    }
}
