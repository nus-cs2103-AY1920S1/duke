package duke.utils;

/**
 * Handles the building of the strings to be displayed in the GUI.
 */
public class UiResponse {
    private StringBuilder sb;

    /**
     * Constructor for UiResponse.
     */
    public UiResponse() {
        this.sb = new StringBuilder();
    }

    /**
     * Clears the stored String.
     */
    public void reset() {
        this.sb = new StringBuilder();
    }

    /**
     * Adds the input String. This String will be appended to the existing
     * text that will be displayed on the GUI.
     * @param s input String to be appended
     */
    public void addSentence(String s) {
        this.sb.append(s + "\n");
    }

    /**
     * Obtains the stored text.
     * @return String representing the text stored thus far
     */
    public String getResponse() {
        return this.sb.toString();
    }
}
