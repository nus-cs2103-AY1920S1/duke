public class CorruptedDataException extends DukeException {

    @Override
    public String toString() {
        return String.format("%s Data from file is corrupted!", super.toString());
    }
}
