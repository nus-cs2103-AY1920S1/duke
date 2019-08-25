public class DoneCommand extends Command {

    public DoneCommand(String filePath, String[] inputSplit) {
        super(filePath, inputSplit);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputSplit.length != 2) {
            // Exception if there is no task number or multiple words after "done"
            throw new DukeException(ui.separationLine
                    + "\n     \u2639 OOPS!!! Please specify number of a single task to mark as done.\n"
                    + ui.separationLine + "\n");
        }
        int specifiedDone = Integer.parseInt(inputSplit[1]); // throws NumberFormatException if not int
        if (specifiedDone < 1 || specifiedDone > tasks.getSize()) {
            // Exception if task number is beyond current number of tasks
            throw new DukeException(ui.separationLine
                    + "\n     \u2639 OOPS!!! Please specify valid task number.\n"
                    + ui.separationLine + "\n");
        }
        Task doneTask = tasks.getElement(specifiedDone - 1);
        doneTask.setDone();
        ui.printDoneNotification(doneTask.toString());

        // Write to file
        storage.overwriteFile(tasks.toArrayList());
    }
}
