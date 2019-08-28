import java.io.File;
import java.io.FileNotFoundException;

public class DukeCLI {
    private TaskList taskList;
    private UI ui;
    private Storage storage = new Storage(new File("data/tasks.txt"));

    /***
     * Main function for Duke CLI application.
     */
    public void run() {
        // Instantiating fields
        ui = new UI();
        taskList = new TaskList();

        // Try to load data
        try {
            taskList.loadData(storage.getTaskList());
            ui.echoMessage("    *** EXISTING FILE LOADED ***");
        } catch (FileNotFoundException e) {
            ui.echoMessage("    *** NO EXISTING FILE FOUND ***");
        } catch (DukeException e) {
            ui.echoException(e);
        }

        // Greet User
        ui.greet();

        // User input
        boolean isByeBye = false;
        // Try is inside while loop so that user can continue to enter commands despite invalid commands
        while (!isByeBye) {
            try {
                String inputCommand = ui.readCommand(); // Initial Input
                Command c = Parser.parseCommand(inputCommand);
                c.execute(storage, taskList, ui);
                if(c.isExit)
                    isByeBye = true;
            } catch(IndexOutOfBoundsException e) {
                ui.echoException(new DukeException("Index Out of Bounds Exception Caught"));
            } catch (DukeException e) {
                ui.echoException(e);
            } catch(Exception e) {
                ui.echoException((new DukeException(e.getMessage())));
            }
        }
    }
}
