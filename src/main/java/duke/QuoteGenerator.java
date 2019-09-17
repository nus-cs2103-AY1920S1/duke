package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Generates a random Quote to brighten up the user's day.
 */
public class QuoteGenerator {
    private static final Random RANDOMISER = new Random();
    private static final File QUOTES_FILE =
            new File("/Users/StudyBuddy/Desktop/CS2103/iP/duke/src/main/resources/quotes.txt");
    private Quote[] allQuotes = new Quote[74];

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
            BufferedReader br = new BufferedReader(new FileReader(QUOTES_FILE));
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
