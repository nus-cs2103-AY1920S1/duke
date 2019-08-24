public class DukeInvalidFilePathException extends DukeException {
    public DukeInvalidFilePathException(String path) {
        super("Your task list cannot be saved/loaded because the following is not a valid file path:\n", path);
    }
}