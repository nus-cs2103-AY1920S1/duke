package org.duke.ui;

import org.duke.DukeException;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * CLI interface for Duke, wrapping standard input and output.
 */
public class DukeIO {

    private static final String lineRule =
            "____________________________________________________________";

    private final Map<String, Predicate<Command>> commandMap
            = new HashMap<>();
    private final Scanner scanner;
    private Predicate<Command> defaultHandler = null;
    private boolean printingDialogBlock;

    /**
     * Constructs a DukeIO instance wrapping standard input.
     */
    public DukeIO() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Output several lines, in context of the current dialog block.
     *
     * @param lines Lines to print.
     */
    public void say(String... lines) {
        say(Arrays.asList(lines).iterator());
    }

    /**
     * Output several lines, in context of the current dialog block.
     *
     * @param lines Lines to print.
     */
    public void say(Iterator<String> lines) {
        if (!this.printingDialogBlock) {
            //Print start of reply line
            System.out.println(lineRule);
            this.printingDialogBlock = true;
        }

        //Print each given line
        while (lines.hasNext()) {
            System.out.println(lines.next());
        }
    }


    /**
     * Output an error, in context of the current dialog block.
     *
     * @param e Exception to print
     */
    public void sayError(DukeException e) {
        this.say(String.format("☹ OOPS!!! %s", e.getMessage()));
    }

    /**
     * Bind a command handler, for a type of command
     *
     * @param command Type of command
     * @param handler Handler for command
     */
    public void bindCommand(String command, Predicate<Command> handler) {
        this.commandMap.put(command, handler);
    }


    /**
     * Bind a fallback command handler, for unknown command types
     *
     * @param handler Fallback handler for unknown commands
     */
    public void setUnknownCommandHandler(Predicate<Command> handler) {
        this.defaultHandler = handler;
    }


    /**
     * Run an action in the context of a dialog block.
     *
     * @param action Action to run
     */
    public void withDialogBlock(Runnable action) {
        this.withDialogBlock(() -> {
            action.run();
            return null;
        }, null);
    }

    /**
     * Run a value-returning function in the context of a dialog block
     *
     * @param action   Function to run
     * @param fallback Value to return, on error
     * @param <T>      Return value type
     * @return Result of function, or fallback if error encountered.
     */
    public <T> T withDialogBlock(Supplier<T> action, T fallback) {
        this.printingDialogBlock = false;
        try {
            return action.get();
        } catch (DukeException e) {
            this.sayError(e);
            return fallback;
        } finally {
            if (this.printingDialogBlock) {
                //Print end of reply line, and extra empty line
                System.out.println(lineRule);
                System.out.println();
            }
        }
    }


    /**
     * Start running the listen loop, and respond to commands.
     */
    public void listen() {
        //While there is still input from user
        while (scanner.hasNextLine()) {
            //Read single line of user input, and remove extra spaces
            String userInput = scanner.nextLine();

            Command command = Command.parse(userInput);

            Predicate<Command> cmdHandler = commandMap.get(command.type);
            boolean shouldExit = this.withDialogBlock(() -> {
                if (cmdHandler != null) {
                    return cmdHandler.test(command);
                } else if (defaultHandler != null) {
                    return defaultHandler.test(command);
                } else {
                    this.say(String.format("Unknown command %s", command.type));
                    return false;
                }
            }, false);

            if (shouldExit) {
                break;
            }
        }
    }
}
