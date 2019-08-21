public class DukeIllegalIndexException extends Exception {

    private String message;

    protected DukeIllegalIndexException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
