public class DeleteCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (ui.getRemainingWords().isEmpty()) {
            throw new DukeException("☹OOPS!!! Wrong format");
        }
        int position = Integer.parseInt(ui.getRemainingWords().trim());
        System.out.println("Noted. I've removed this task.");
        System.out.println(tasks.taskArrayList.get(position-1));
        tasks.taskArrayList.remove(position-1);
        storage.writeData();
        System.out.println("Now you have " + tasks.taskArrayList.size() + " tasks in the list");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
