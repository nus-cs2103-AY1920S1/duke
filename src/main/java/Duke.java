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
     * Starts the Duke program.
     */
    public void run() {
        ui.hello();
        ui.takeInUserInput();
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
                storage.overwriteTasks();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ui.getByeResponse();
        }
        return ui.readUserInput(input);
    }

    public String getHello() {
        return ui.getHelloMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
