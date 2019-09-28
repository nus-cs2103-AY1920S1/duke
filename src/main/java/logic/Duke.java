package logic;

import commands.Command;
import commands.TaskCommands;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Driver Class for Program.
 */
public class Duke {
    private String taskPath = "./tasks.txt";
    private String contactPath = "./contacts.txt";
    private Scanner sc = new Scanner(System.in);
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private ContactList contacts;

    /**
     * Constructor.
     */
    public Duke() {
        File taskFile = new File(taskPath);
        File contactFile = new File(contactPath);
        try {
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }

            if (!contactFile.exists()) {
                contactFile.createNewFile();
            }
            storage = new Storage(taskFile, contactFile);
            ui = new Ui();
            tasks = new TaskList(storage.loadTasks());
            contacts = new ContactList(storage.loadContacts());
        } catch (DukeException e) {
            Ui.loadStr(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Continuously scans for User Input, Creates Command Objects and execute accordingly.
     */
    public void run() {
        ui.greet();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
            } catch (DukeException e) {
                Ui.loadStr(e.getMessage());
            }
        }
    }

    /**
     * Returns string representation of response from Duke.
     *
     * @param input User-typed input
     * @return Duke's generated response string
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            if (c instanceof TaskCommands) {
                c.execute(tasks, ui, storage);
            } else {
                c.execute(contacts, ui, storage);
            }
        } catch (DukeException e) {
            Ui.loadStr(e.getMessage());
        } finally {
            return Ui.getLoadedStr();
        }

    }

    public String startMessage() {
        ui.greet();
        return Ui.getLoadedStr();
    }

}