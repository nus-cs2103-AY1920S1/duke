package q.error;

import java.util.Random;

/**
 * An abstract exception for all errors in Q.
 */
public abstract class QException extends Exception {

    private static Random random = new Random();
    private static final String[] errorStatements = {"Too bad. ", "What? ", "Sorry...? ", "!!! ", "Ugh. ", "So... ",
        "You messed up. ", "You did something wrong. ", "Nope. ", "Something's wrong. ", "No. "};

    public QException(String s) {
        super(errorStatements[random.nextInt(errorStatements.length)] + s);
    }
}
