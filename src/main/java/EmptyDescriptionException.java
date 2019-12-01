/**
 * Simulates invalid user input errors. Specifically, empty descriptions.
 * @author Fabian Chia Hup Peng
 */

public class EmptyDescriptionException extends InvalidInputException {

    public EmptyDescriptionException(String msg) {
        super(msg);
    }

}
