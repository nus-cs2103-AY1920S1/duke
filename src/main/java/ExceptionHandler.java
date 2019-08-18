public class ExceptionHandler {
    private Echoer echoer;

    ExceptionHandler(Echoer echoer) {
        this.echoer = echoer;
    }

    void handleKnownException(DukeException e) {
        this.echoer.echo(e.getMessage());
    }

    void handleUnknownException(Exception e) {
        e.printStackTrace();
    }
}
