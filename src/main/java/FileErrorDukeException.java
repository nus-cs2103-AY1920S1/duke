public class FileErrorDukeException extends DukeException {
    String filePath;

    public FileErrorDukeException(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "OOPS!!! I'm sorry,File at " + filePath + " not Found.";
    }
}
