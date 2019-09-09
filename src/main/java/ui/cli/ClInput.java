package ui.cli;

import ui.input.InputHandler;
import ui.input.InputListener;
import ui.output.OutputHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClInput implements InputHandler {
    private boolean isActive;
    private Scanner scanner;
    private List<InputListener> listeners;

    private static ClInput singleton;

    private static final String DUKE_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING =
            DUKE_LOGO + "\n" + "\n"
                    + "Hello! I'm duke.Duke\n"
                    + "What can I do for you?";


    private ClInput() {
        isActive = true;
        scanner = new Scanner(System.in);

        listeners = new ArrayList<>();
    }

    public static ClInput getInstance() {
        if (singleton == null) {
            singleton = new ClInput();
        }

        return singleton;
    }

    @Override
    public void updateAllListeners(String input) {
        listeners.forEach(listener -> listener.update(input));
    }

    @Override
    public void addListener(InputListener listener) {
        listeners.add(listener);
    }

    @Override
    public void startHandler(OutputHandler output) {
        if (output != null) {
            output.display(GREETING);
        }

        while (isActive) {
            String input = scanner.nextLine();
            updateAllListeners(input);
        }
    }

    @Override
    public void stopHandler() {
        isActive = false;
        scanner.close();
    }
}
