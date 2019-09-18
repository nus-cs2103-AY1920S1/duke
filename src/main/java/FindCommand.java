public class FindCommand extends Command {
    String cmdDetails;

    public FindCommand(String firstPart, String everythingElse) {
        super(firstPart, everythingElse);
        cmdDetails = everythingElse;
    }

    @Override
    public String execute(TaskList list, Ui ui, SaveToFile store) throws DukeException {
        int numOfTasks = list.size();
        TaskList temp = new TaskList();
        for(int i = 0; i < numOfTasks; i++) {
            Tasks next = list.getTask(i);
            String[] parsed = next.getDetails().split(" ");
            for(String s: parsed) {
                if(s.equalsIgnoreCase(cmdDetails)) {
                    temp.addTask(next);
                    break;
                }
            }

        }
        return "Here are the matching tasks in your list: \n" + temp.printAll();
    }
}
