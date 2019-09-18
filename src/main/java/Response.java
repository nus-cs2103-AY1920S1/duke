class Response {

    private String message;
    private boolean isError;

    Response(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }


    String getMessage() {
        return message;
    }

    boolean getErrorStatus() {
        return isError;
    }




}
