package duke;

import duke.command.Command;
import duke.ui.Main;
import javafx.application.Application;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a new Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        taskList = new TaskList(storage.load());
    }

    /**
     * Returns the upcoming tasks.
     *
     * @return Upcoming tasks.
     */
    public String upcomingTasks(){
        return taskList.upcomingTasks();
    }

    /**
     * Returns the response from executing a command.
     *
     * @param input User input command.
     * @return Response returned from command.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input); // Parse and perform logic
        assert command != null : "Command cannot be null";
        return command.execute(ui, storage, taskList);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
