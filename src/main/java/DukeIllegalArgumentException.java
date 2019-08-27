class DukeIllegalArgumentException extends Exception {

    private String message;

    DukeIllegalArgumentException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
