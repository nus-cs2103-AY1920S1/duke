/**
 * This is a class for an exception where the file is not found.
 * @author Choong Yong Xin
 */
public class FileNotFoundDukeException extends DukeException {
    public FileNotFoundDukeException(String filePath) {
        super(filePath + " not found");
    }
}
