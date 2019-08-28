public class FileNotFoundDukeException extends DukeException {
    public FileNotFoundDukeException(String filePath) {
        super(filePath + " not found");
    }
}
