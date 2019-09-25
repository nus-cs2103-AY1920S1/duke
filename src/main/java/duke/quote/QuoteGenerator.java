package duke.quote;

import duke.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Generates a random Quote to brighten up the user's day.
 */
public class QuoteGenerator {
    private static final Random RANDOMISER = new Random();
    private final InputStream QUOTES_FILE =
            this.getClass().getResourceAsStream("/quotes.txt");
    private Quote[] allQuotes = new Quote[74]; // number of quotes

    /**
     * Creates a new QuoteGenerator and loads all the quotes available.
     */
    public QuoteGenerator() {
        loadQuotes();
    }

    /**
     * This methods generates a random quote from the library.
     * @return a String representation of the randomised quote
     */
    public String getRandomQuote() {
        Quote randomQuote = allQuotes[RANDOMISER.nextInt(allQuotes.length - 1)];
        return randomQuote.toString();
    }

    private void loadQuotes() {
        int countQuote = 0;
        try {
            // load the file of quotes into allQuotes using BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(QUOTES_FILE));
            String nextQuote = br.readLine();
            while (nextQuote != null) {
                allQuotes[countQuote] = new Quote(nextQuote);
                countQuote++;
                nextQuote = br.readLine();
            }
        } catch (IOException e) {
            System.err.println(Ui.addLines(e.getMessage()));
        }
    }
}
