package jermi.component;

import jermi.exception.JermiException;

/**
 * A class that deals with handling exceptions.
 */
public class ExceptionHandler {
    /** Formatter. */
    Formatter formatter;

    /**
     * Public constructor for class.
     * @param formatter Formatter.
     */
    public ExceptionHandler(Formatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Handles checked exceptions.
     *
     * @param e JermiException.
     * @return Error message.
     */
    public String handleCheckedExceptions(JermiException e) {
        return this.formatter.echo(e.getMessage());
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
