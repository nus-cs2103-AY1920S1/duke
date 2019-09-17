package duke;

/**
 * A Quote is a phrase or sentence that will motivate the user.
 */
public class Quote {
    private String quote; // quote itself
    private String author; // person who writes the quote

    /**
     * Creates a Quote object with a string of quote and its author.
     * @param fullQuote a String with a quote and its author
     */
    public Quote(String fullQuote) {
        String[] splitQuote = fullQuote.split(" - ", 2);
        this.quote = splitQuote[0];
        this.author = splitQuote[1];
    }

    /**
     * Returns the quote, with its author.
     * @return a String representation of the full quote
     */
    public String toString() {
        return String.format("%s%n -%s", this.quote, this.author);
    }
}
