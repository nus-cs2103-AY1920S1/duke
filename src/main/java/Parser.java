import java.io.FileWriter;
import java.io.IOException;

public class Parser {
    public void parse(String command, UI ui, TaskList tasks, String filePath){
        try {
            FileWriter fw = new FileWriter(filePath, true);
            while (!command.equals("bye")) {
                try {
                    String[] parts = command.split(" ", 2);
                    if (command.equals("list")) {
                        printList(tasks, ui);
                    } else if (parts[0].equals("done")) {
                        int taskNum = Integer.parseInt(parts[1]);
                        ui.printReply(tasks.get(taskNum - 1).markAsDone());
                    } else if (parts[0].equals("delete")) {
                        int taskNum = Integer.parseInt(parts[1]);
                        removeFromList(tasks, taskNum, ui);
                    } else if (parts[0].equals("todo")) {
                        addToList(new Todo(parts[1]), tasks, fw, ui);
                    } else if (parts[0].equals("deadline")) {
                        String[] subparts = parts[1].split(" /by ");
                        addToList(new Deadline(subparts[0], new Date(subparts[1])), tasks, fw, ui);
                    } else if (parts[0].equals("event")) {
                        String[] subparts = parts[1].split(" /at ");
                        addToList(new Event(subparts[0], new Date(subparts[1])), tasks, fw, ui);
                    } else {
                        throw new DukeException("");
                    }
                } catch (DukeException e) {
                    ui.printReply("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.printReply("OOPS!!! The description of a " + command.split(" ", 2)[0] + " cannot be empty");
                }
                command = ui.readCommand();
            }
            fw.close();
        }catch (IOException e) {
            System.out.println("File not found.");
        }
    }
    public static void printList(TaskList taskList, UI ui){
        String reply = "Here are the tasks in your list:\n\t ";
        for(int i=0; i<taskList.size(); i++) {
            reply += (i + 1) + "." + taskList.get(i);
            if (i != taskList.size() - 1)
                reply += "\n\t ";
        }
        ui.printReply(reply);
    }
    public static void addToList(Task task, TaskList taskList, FileWriter fw, UI ui) {
        taskList.add(task);
        String reply = "Got it. I've added this task:\n\t  " + task + "\n\tNow you have " + taskList.size()
                + ((taskList.size() == 1) ? " task" : " tasks") + " in the list.";
        ui.printReply(reply);
        String replyToFile = task.toFile();
        try {
            fw.write(replyToFile);
        } catch (IOException e) {
        }
    }

    public static void removeFromList(TaskList taskList, int taskIndex, UI ui) throws DukeException{
        if(taskIndex > taskList.size()) throw new DukeException("");
        String reply = "Noted. I've removed this task:\n\t  " + taskList.remove(taskIndex-1) + "\n\tNow you have " + taskList.size()
                + ((taskList.size() == 1)?" task":" tasks") + " in the list.";
        ui.printReply(reply);
    }
}
