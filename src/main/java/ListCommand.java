class ListCommand extends Command {
    ListCommand() {}

    void execute(TaskList tasks, UserInterface ui, Storage storage) {
        tasks.list();
    }
}