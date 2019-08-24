public class ExceptionHandler {
    Ui ui;

    ExceptionHandler(Ui ui) {
        this.ui = ui;
    }

    void handleCheckedExceptions(JermiException e) {
        this.ui.echo(e.getMessage());
    }

    void handleUncheckedExceptions(Exception e) {
        e.printStackTrace();
    }
}
