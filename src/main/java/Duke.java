import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
        storage = new Storage("D:\\NUS Computer Science\\CS2103T\\duke\\src\\main\\java\\duke.txt");
        tasks = new TaskList();
    }

    /**
     * Runs the entire program. Kick starts the entire computer duke Program
     *
     *  @param inputInstruction user input Instructions in String format
     *  @return a string format output for Duke to reply
     *  @throws IOException throws exception if user input invalid
     */
    public String getResponse(String inputInstruction) throws IOException {
        String outputContent;
        String inputCommand = Parser.getInputCommand(inputInstruction);
        if (inputCommand.equals("bye")) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(buffer));
            System.out.println("___________________________________");
            System.out.println("Bye. Hope to see you again soon!!");
            System.out.println("___________________________________");
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            outputContent = buffer.toString();
            buffer.reset();
        } else {
            outputContent = ui.executeInstructions(inputInstruction, inputCommand, storage, tasks);
        }
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
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        System.out.println("___________________________________");
        System.out.println(input);
        System.out.println("___________________________________");
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    public String getStartMessage() {
        return ui.initiate();
    }
}
