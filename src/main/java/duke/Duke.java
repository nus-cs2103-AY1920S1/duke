package duke;

import duke.command.Command;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(){
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        taskList = new TaskList(storage.load());
    }

    public void run(){
        boolean shouldContinue = true;

        while(shouldContinue) { //Read input until 'bye' command is entered
            String input = ui.readCommand();
            Command command = Parser.parse(input); // Parse and perform logic
            command.execute(ui, storage, taskList);
            shouldContinue = command.shouldContinue();
        }
        ui.printExit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
