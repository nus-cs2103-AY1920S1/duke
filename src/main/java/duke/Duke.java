package duke;

import duke.exception.DukeException;
import duke.ui.Ui;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import duke.command.Command;

public class Duke {
    private TaskList tasks;
    private Saved savedFile;
    private Scanner scan;
    private Ui ui;

    public Duke(String filePath) throws IOException, ParseException {
        ui = new Ui();
        savedFile = new Saved(filePath);

        tasks = new TaskList(savedFile.loadData());
    }

    public void run() throws IOException, ParseException {
        String input;
        Command cmd;

        ui.printIntro();

        Boolean isBye = false;
        while(!isBye) {
            try {
                input = ui.scanCmd();
                ui.printLine();
                cmd = Parser.parse(input);
                cmd.execute(tasks, ui, savedFile);
                isBye = cmd.isBye();
            } catch (DukeException error) {
                ui.printError(error.getMessage());
            }
            ui.printLine();
        }
        savedFile.saveToFile(tasks.getTaskArrayList());

    }

    public static void main(String[] args) throws IOException, ParseException {
        new Duke("src/main/java/data.txt").run();
    }


}