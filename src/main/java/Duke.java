import Logic.Command;
import Logic.CommandParser;
import Model.Tasklist;
import Storage.Storage;
import UI.UI;
import UI.UI_CLI;

import java.nio.file.Paths;

public class Duke{
    private Storage storage;
    private Tasklist tasks;
    private UI ui;

    private final String INPUT_DELIMITER = " ";

    private CommandParser commandParser = new CommandParser(INPUT_DELIMITER);
    private boolean isExit;

    public static void main(String[] args){
        new Duke("duke.txt", false).run();
    }

    public Duke(String filepath, boolean isGUI){
        if(isGUI){
            ui = new UI_GUI();
        } else {
            ui = new UI_CLI();
        }
        storage = new Storage(Paths.get(filepath));
        tasks = storage.load();

    }

    public void run() {
        ui.printWelcome();

        isExit = false;

        while(!isExit){
            String userInput = ui.getUserInput();
            Command command = commandParser.parseCommand(userInput);
            String response = command.execute(tasks, ui, storage);
            ui.printContent(response);
            isExit = command.isExit();
        }
    }

    public String runCommand(String userInput){
        System.out.println(userInput);
        Command command = commandParser.parseCommand(userInput);
        String response = command.execute(tasks, ui, storage);
        isExit = command.isExit();
        return response;
    }

    public boolean isExit(){
        return isExit;
    }
}
