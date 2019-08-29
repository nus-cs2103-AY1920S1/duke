package logic;

import commands.Command;

import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private String taskListPath;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) throws DukeException {
        taskListPath = filePath;
        storage = new Storage(taskListPath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
    }

    void start() {
        ui.greet();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
            } catch (DukeException e) {
                Ui.printStr(e.getMessage());
            }
        }
    }

    public static void main (String args[]) throws DukeException {
        Duke duke = new Duke("./src/main/data/taskList.txt");
        duke.start();
    }
}