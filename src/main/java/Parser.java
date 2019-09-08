import java.util.Scanner;
import java.io.IOException;

/**
 * Deals with reading and making sense of the user command.
 */
public class Parser {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new parser instance.
     *
     * @param s Storage instance to be used.
     * @param t TaskList containing task list.
     * @param u Ui instance to be used.
     */
    public Parser(Storage s, TaskList t, Ui u) {
        this.storage = s;
        this.tasks = t;
        this.ui = u;
    }

    /**
     * Reads user input as commands, deciphers commands and carries out the commands correspondingly.
     * Instructs Ui to return different messages to interact with users.
     *
     * @param command user input.
     * @return String to be displayed as reply to the user.
     * @throws DukeException self-defined exceptions caused by illegal input.
     */
    public String parse(String command) throws DukeException {
        // Read user input
        try {
            if (command.equals("bye")) {
                return ui.exit();
            } else if (command.equals("list")) {
                return ui.list(tasks);
            } else if (command.length() > 5 && command.substring(0, 4).equals("done")) {
                if (command.charAt(4) != ' ') {
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    Task task = tasks.done(Integer.parseInt(command.substring(5)));
                    String response = ui.done(task);
                    storage.writeToFile(tasks.generateInfo());
                    return response;
                }
            } else if (command.length() > 7 && command.substring(0, 6).equals("delete")) {
                if (command.charAt(6) != ' ') {
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    Task task = tasks.delete(Integer.parseInt(command.substring(7)));
                    String response = ui.delete(task, tasks);
                    storage.writeToFile(tasks.generateInfo());
                    return response;
                }
            } else if (command.length() > 5 && command.substring(0, 4).equals("find")) {
                if (command.charAt(4) != ' ') {
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    String response = ui.find(tasks, command.substring(5));
                    storage.writeToFile(tasks.generateInfo());
                    return response;
                }
            } else {
                Task task = tasks.add(command);
                String response = ui.add(task, tasks);
                storage.writeToFile(tasks.generateInfo());
                return response;
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}