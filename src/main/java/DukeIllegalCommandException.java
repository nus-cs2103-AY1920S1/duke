class DukeIllegalCommandException extends Exception {

    private String message;

    DukeIllegalCommandException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
