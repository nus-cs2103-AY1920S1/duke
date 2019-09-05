package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;


public abstract class Command {

    private static Ui ui = new Ui();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
//    public static Command parse(String input) throws DukeException {
//        Parser parser = new Parser();
//        String action = parser.parseAction(input);
//
//        switch (action) {
//        case "bye":
//            return new ExitCommand();
//
//        case "list":
//            return new ListCommand();
//
//        case "done":
//            return new DoneCommand(input);
//
//        case "delete":
//            return new DeleteCommand(input);
//
//        case "find":
//            String keyword = parser.parseDescription("find", input);
//            return new FindCommand(keyword);
//
//        case "todo":
//        case "deadline":
//        case "event":
//            String description = parser.parseDescription(action, input);
//            return new AddCommand(input, action, description);
//
//        default:
//            try {
//                throw new DukeException(ui.invalidCommand());
//            } catch (DukeException err) {
//                System.out.println(err.getMessage());
//                ui.separator();
//                System.out.println("");
//            }
//        }
//    }
}
