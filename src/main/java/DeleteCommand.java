public class DeleteCommand extends Command{

    public DeleteCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            ui.deleteMessage(val-1, tasks);
            //System.out.println("Noted. I've removed this task:"+ "\n" + tasks.taskPrint(val-1) +
            //        "\n"+ "Now you have " + (tasks.size()-1) + " tasks in the list.");
            tasks.remove(val - 1);
            storage.updateFile(tasks);
        } catch (Exception e) {
            System.out.println("Error, you have entered an invalid number");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
