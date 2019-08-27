class DoneCommand extends Command {
    private int index;

    DoneCommand(int index) {
        this.index = index;
    }

    void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = tasks.done(index);

        //display successful message
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task.toString());
    }
}