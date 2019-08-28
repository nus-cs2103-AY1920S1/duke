public class DeleteCommand extends Command {
    private int position;

    public DeleteCommand(int position) {
        super();
        this.position = position;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deleted = tasks.remove(position);
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + deleted);
        System.out.println(String.format("     Now you have %d tasks in the list.", tasks.size()));
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof DeleteCommand) && (((DeleteCommand) object).getPosition() == this.position);
    }
}
