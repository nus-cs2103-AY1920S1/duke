public class FindCommand extends Command{
    private String word;

    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int count = 1;
        for (Task task : tasks.getWholeList()) {
            if (task.getTask().contains(this.word)) {
                System.out.println(count + "." + task);
                count++;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
