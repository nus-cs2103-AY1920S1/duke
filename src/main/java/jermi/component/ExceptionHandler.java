package jermi.component;

import jermi.exception.JermiException;

/**
 * A class that deals with handling exceptions.
 */
public class ExceptionHandler {
    /** UI. */
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
     * @return Error message.
     */
    public String handleCheckedExceptions(JermiException e) {
        return this.ui.echo(e.getMessage());
    }

    /**
     * Handles unchecked exceptions.
     *
     * @param e Exception.
     * @return Error message.
     */
    public String handleUncheckedExceptions(Exception e) {
        return e.toString();
    }
}
