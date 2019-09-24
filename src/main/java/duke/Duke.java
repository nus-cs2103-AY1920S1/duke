package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;

/**
 * Represents a Duke class which is used to run a taskList computer program
 * that allows users to store their various task (Deadline, Event, Things to do)
 * and store them in a text file. The program also allows them to delete and update
 * their list once they have completed it.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class
     * instantiate a UI, Storage and TaskList object.
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("duke.txt");
        try {
            tasks = new TaskList(storage.loadFromTextFile());
        } catch (IOException | ParseException | DukeException e) {
            ui.printException(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the entire program. Kick starts the entire computer duke Program
     *
     *  @param inputInstruction user input Instructions in String format
     *  @return a string format output for Duke to reply
     * @throws DukeException for invalid inputs
     */
    public String getResponse(String inputInstruction) throws DukeException {
        assert inputInstruction != null;
        Command currentCommand = Parser.getCommand(inputInstruction, tasks, ui);
        String outputContent = currentCommand.execute(tasks, ui, storage);
        return outputContent;
    }

    /**
     * Processes user input and return it into reformatted input structure.
     * to display
     *
     * @param input String format user input
     * @return String format, reformatted version
     */
    public String reformatInput(String input) {
        assert input != null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        Parser.printLine();
        System.out.println(input);
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    /**
     * returns a ui initiate message according to the current status of the program
     * if it has an existing file present (status : "loaded") else (status : "new").
     * @return a String output initiate message according to the program status type
     */
    public String getStartMessage() {
        if (tasks.isEmpty()) {
            return ui.initiate("new");
        } else {
            return ui.initiate("loaded");
        }
    }
}
