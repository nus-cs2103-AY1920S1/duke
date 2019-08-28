import java.text.ParseException;

public class AddCommand extends Command {
    private String[] arr;

    public AddCommand(String[] arr) {
        this.arr = arr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        String description = "";
        Task t;
        String next = arr[0];
        try {
            if (next.equals("todo") || next.equals("deadline") || next.equals("event")) {
                if (arr.length == 1) {
                    throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + next + " cannot be empty.");
                } else {
                    for (int i = 1; i < arr.length; i++) {
                        description += " " + arr[i];
                    }
                }
            } else {
                throw new UnknownTaskException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("Got it. I've added this task:");
            if (next.equals("todo")) {
                t = new Todos(description.trim());
            } else if (next.equals("deadline")) {
                int index = description.indexOf("/");
                String byWhen = description.substring(index + 4);
                String desc = description.substring(1, index - 1);
                t = new Deadline(desc, byWhen);
            } else {
                int index = description.indexOf("/");
                String at = description.substring(index + 4);
                String desc = description.substring(1, index - 1);
                t = new Event(desc, at);
            }
            tasks.getList().add(t);
            ui.printTask(tasks.getList().size(), t);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Date in wrong format");
        }
        storage.writeFile(tasks.getList());
    }
}
