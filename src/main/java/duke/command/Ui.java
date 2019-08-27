package duke.command;

import duke.exception.DukeIllegalActionException;
import duke.exception.DukeIllegalDescriptionException;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {
    public void initMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private Storage storage;

    public Ui(Storage storage) {
        this.storage = storage;
    }

    static Scanner sc = new Scanner(System.in);

    public void showLoadingError() {
        System.out.println("duke.task.Task list not retrieved.");
    }

    public void readUserInput() {
        while(sc.hasNext()) {
            String act = sc.next();
                try {
                    Parser.parse(act, storage);
                } catch (DukeIllegalDescriptionException | DukeIllegalActionException e) {
                    System.out.println(e.getMessage());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
    }
}
