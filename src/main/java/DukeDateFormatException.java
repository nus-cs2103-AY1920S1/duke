class DukeDateFormatException extends Exception {

    private String message;

    DukeDateFormatException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
