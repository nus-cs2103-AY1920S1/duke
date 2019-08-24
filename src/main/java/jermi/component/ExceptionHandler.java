package jermi.component;

import jermi.exception.JermiException;

public class ExceptionHandler {
    Ui ui;

    public ExceptionHandler(Ui ui) {
        this.ui = ui;
    }

    public void handleCheckedExceptions(JermiException e) {
        this.ui.echo(e.getMessage());
    }

    public void handleUncheckedExceptions(Exception e) {
        e.printStackTrace();
    }
}
