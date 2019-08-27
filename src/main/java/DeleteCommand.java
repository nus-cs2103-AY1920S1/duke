class DeleteCommand extends Command {
    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = tasks.remove(index);

        //display successful message and task count
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + task.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }
}