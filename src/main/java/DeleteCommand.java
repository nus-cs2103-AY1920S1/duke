public class DeleteCommand extends Command {
    private DeleteType deleteType;

    public enum DeleteType {
        INDEX;
    }

    public DeleteCommand(DeleteType type, String input) {
        super(input);
        deleteType = type;
    }

    public void execute(TaskList tasks, UI ui, DukeDatabase database) throws DukeException {
        initialise(tasks, ui, database);

        if (DeleteType.INDEX.equals(deleteType)) {
            deleteTask();
        }
    }

    // delete a task from the taskList.
    private void deleteTask() throws DukeException {
        try {
            int index = Integer.parseInt(input.substring(6).trim());

            Task task = taskList.deleteTask(index - 1);

            ui.echo(() -> {
                System.out.printf("%sNoted. I've removed this task:\n", UI.INDENTATION_LVL1);
                System.out.printf(ui.indentAndSplit(task.toString(), UI.INDENTATION_LVL2)); // task details
                System.out.printf("%sNow you have %s in the list.\n", UI.INDENTATION_LVL1, ui.getTaskPhrase(taskList.size()));
            });
        } catch (NumberFormatException e) {
            ui.echo(String.format("%s There can only be an integer after the word \"delete\"", DukeException.PREFIX ));
        }
    }
}
