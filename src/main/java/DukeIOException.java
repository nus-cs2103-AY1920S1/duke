public class DukeIoException extends DukeException {
    @Override
    public String toString() {
        return "An error occurred with reading / writing to the storage file.";
    }
}
