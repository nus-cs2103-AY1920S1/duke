public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            String desc = this.input.substring(4).trim();
            StringBuilder result = new StringBuilder();
            int i = 1;
            for (Task task : tasks.getTasks()) {
                if (task.contains(desc)) {
                    result.append("     " + i + "." + task + "\n");
                    i++;
                }
            }
            Duke.print("Here are the matching tasks in your list:" + "\n" + result);
        } catch(Exception e){
            Duke.print("☹ OOPS!!! The description of a find cannot be empty.");
        }
    }
}
