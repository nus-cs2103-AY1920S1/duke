package duke;

public class ByeCommand extends Command {
    ByeCommand() {
        this.commandType = Commands.bye;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            handleBye(inputs);
            break mainLoop;
        } catch (DukeInvalidArgumentException ex) {
            ui.displayDukeException(ex);
            break;
        }

        private void handleBye(String[] inputs) throws DukeInvalidArgumentException {
            if (inputs.length > 1) {
                throw new DukeInvalidArgumentException(
                        "Encountered extraneous arguments after bye command",
                        " \u2639 OOPS!!! There shouldn't be anything following 'bye',\n"
                                + " are you sure you wanted to exit?");
            }

            System.out.println(" Bye. Hope to see you again soon!");
            System.out.println(Ui.HORIZONTAL_LINE);
        }
    }
}
