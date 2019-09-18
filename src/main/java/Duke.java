/**
 * Represents the interface that the users will be interacting with.
 * Uses methods from other classes to control output depending on the user's input.
 */
public class Duke {

    private SaveToFile storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new SaveToFile("/Users/meiannn/Documents/GitHub/duke/src/main/java/TaskList.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    //this is supposed to return a string type
    /*public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }*/

    /**
     * Generates the output.
     * @param input refers to the request given by the user taken in as a String.
     * @return gives the response of duke depending on the request by user.
     *
     */


    private String dukeRun(String input) {
        String output;
        try {
            String fullCommand = input;
            //ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(fullCommand);
            output = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            output = ui.showError(e.getMessage());
        }
        return output;
    }


    //public static void main(String[] args) {
        //new Duke().run();
    //}

    /**
     *
     * @return the welcome message with duke's logo
     */
    String welcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Used by GUI
     * @param input is the user request read from GUI
     * @return the response of duke according to the request.
     */

    String getResponse(String input) {
        return dukeRun(input); //returns output from duke.run()
    }

}