public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        StringBuilder errMessage = new StringBuilder();
        String horizontal_line = "\t____________________________________________________________";
        errMessage.append(horizontal_line +"\n");
        errMessage.append("\t " + this.getMessage() + "\n");
        errMessage.append(horizontal_line);

        return errMessage.toString();
    }
}
