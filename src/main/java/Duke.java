import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents Duke, a personal chatbot assistant.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/java/tasklists.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the appropriate response with the specified input/command given by the user.
     * @param input given by the user.
     * @return String the appropriate response to the user's input.hello
     */
    public String getResponse(String input) {
        assert(input != null);
        if (input.equals("bye")) {
            try {
                storage.updateTasks();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ui.getByeResponse();
        }

        return ui.getResponseToUserInput(input);
    }

    public String getHello() {
        return ui.getHelloMessage();
    }

}
