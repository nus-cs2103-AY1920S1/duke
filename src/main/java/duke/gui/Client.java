package duke.gui;

import duke.Duke;

public class Client {
    private Duke app;

    /**
     * Gets Duke's response.
     * @param input User's input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        if (app == null) {
            app = new Duke();
        }

        try {
            return Duke.parser.parse(input).executeCommand();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
