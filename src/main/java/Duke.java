
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

    public String dukeRun(String input) {
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

    String welcomeMessage() {
        return ui.showWelcome();
    }

    String getResponse(String input) {
        return dukeRun(input); //returns output from duke.run()
    }

}