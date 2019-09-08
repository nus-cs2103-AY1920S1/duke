package ui.cli;

import ui.input.InputHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class ClInput extends InputHandler {
    private boolean isActive;
    private Scanner scanner;

    private static ClInput singleton;

    private ClInput() {
        super(new ArrayList<>());
        isActive = true;
        scanner = new Scanner(System.in);
    }

    public static ClInput getInstance() {
        if (singleton == null) {
            singleton = new ClInput();
        }

        return singleton;
    }

    @Override
    public void start() {
        while (isActive) {
            String input = scanner.nextLine();
            updateAllListeners(input);
        }
    }

    @Override
    public void stop() {
        isActive = false;
        scanner.close();
    }
}
