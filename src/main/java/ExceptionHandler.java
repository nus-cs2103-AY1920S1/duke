public class ExceptionHandler {

    static void handleKnownException(JermiException e) {
        Ui.echo(e.getMessage());
    }

    static void handleUnknownException(Exception e) {
        e.printStackTrace();
    }
}
