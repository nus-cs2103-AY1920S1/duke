public class DukeIllegalCommandException extends Exception {

    private String message;

    protected DukeIllegalCommandException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
