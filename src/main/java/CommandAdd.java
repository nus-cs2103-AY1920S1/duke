public class CommandAdd extends Command {

    private String description;
    private String type;
    private Date date;
    private Task task;

    public Task getTask() {
        return task;
    }

    public CommandAdd(String str) {
        this.type = str.split(" ", 2)[0];
        if (type.equals("todo")) {
            this.description = str.split(" ", 2)[1];
            Todo temp = new Todo(description);
            this.task = temp;
        } else if (this.type.equals("deadline")){
            String[] firstSplit = new String[2];
            firstSplit = str.split(" /by ");
            // description
            String tempStr = firstSplit[0].split(" ")[1];
            for (int i = 2; i < firstSplit[0].split(" ").length; i++){
                tempStr = tempStr + " " + firstSplit[0].split(" ")[i];
            }
            description = tempStr;
            // timing
            Deadline temp = new Deadline(description, firstSplit[1]);
            this.task = temp;
            this.date = temp.getDate();
        } else {
            String[] firstSplit = new String[2];
            firstSplit = str.split(" /at ");
            // description
            String tempStr = firstSplit[0].split(" ")[1];
            for (int i = 2; i < firstSplit[0].split(" ").length; i++){
                tempStr = tempStr + " " + firstSplit[0].split(" ")[i];
            }
            description = tempStr;
            // timing
            Event temp = new Event(description, firstSplit[1]);
            this.task = temp;
            this.date = temp.getDate();
        }
        this.string = type + description + date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Got it. I've added this task:");
        tasks.add(task);
        String tempStr;
        tempStr = task.toString();
        ui.printString(tempStr);
        ui.printString("Now you have " + tasks.getNoOfTasks() + " tasks in the list.");
        storage.save(tasks.getList(), tasks.getNoOfTasks());
    }

    @Override
    public String toString() {
        return this.string;
    }

}
