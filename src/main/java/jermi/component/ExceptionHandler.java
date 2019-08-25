package jermi.component;

import jermi.exception.JermiException;

/**
 * A class that deals with handling exceptions.
 */
public class ExceptionHandler {
    /** UI */
    Ui ui;

    /**
     * Public constructor for class.
     * @param ui UI.
     */
    public ExceptionHandler(Ui ui) {
        this.ui = ui;
    }

    /**
     * Handles checked exceptions.
     *
     * @param e JermiException.
     */
    public void handleCheckedExceptions(JermiException e) {
        this.ui.echo(e.getMessage());
    }

    /**
     * Handles unchecked exceptions.
     *
     * @param e Exception.
     */
    public void handleUncheckedExceptions(Exception e) {
        e.printStackTrace();
    }
}
