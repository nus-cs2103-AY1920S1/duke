public class ExceptionHandler {

    static void handleKnownException(JermiException e) {
        Echoer.echo(e.getMessage());
    }

    static void handleUnknownException(Exception e) {
        e.printStackTrace();
    }
}
