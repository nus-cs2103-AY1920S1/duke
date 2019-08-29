package duke.exception;

class DukeIllegalCommandException extends Exception {

    private String message;

    public DukeIllegalCommandException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
