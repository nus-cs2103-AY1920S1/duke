package seedu.duke;

import java.util.Scanner;
import seedu.duke.Ui;
import seedu.duke.Parser;
import seedu.duke.Storage;
import seedu.duke.TaskList;

/**
 * Main class of this project.
 * Personal assistant bot to help you keep track of tasks.
 */

public class Duke {

    private Storage storage;
    private TaskList taskList;

    private Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    private void run(String command) throws DukeException {
        String[] inputs = Parser.parseCommand(command);
        switch (inputs[0]) {
        case "bye":
            return;
        case "list":
            taskList.list();
            break;
        case "done":
            taskList.markAsDone(inputs[1]);
            break;
        case "todo":
            taskList.addTodo(inputs[1]);
            break;
        case "deadline":
            taskList.addDeadline(inputs[1]);
            break;
        case "event":
            taskList.addEvent(inputs[1]);
            break;
        case "delete":
            taskList.deleteTask(inputs[1]);
            break;
        default:
            throw new DukeNoSuchCommandException();
        }
        try {
            storage.save(taskList.getArray());
        } catch (DukeSaveFailedException e) {
            e.printMessage();
        }
    }


    public static void main(String[] args) {

        String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Ui.printBlock(new String[] {"Hello! I'm Duke.", "What can I do for you?"});

        Duke duke = new Duke("save.txt");

        // Begin input handling
        Scanner sc = new Scanner(System.in);
        String input;
        input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                duke.run(input);
            } catch (DukeException e) {
                e.printMessage();
            }
            input = sc.nextLine();
        }

        Ui.printBlock("Bye. Hope to see you again soon!");

    }
}
