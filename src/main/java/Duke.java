import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private File state = new File("data/state.txt");

    public void writeFileContents(String filePath) {
        try {
            Scanner sc = new Scanner(state);
            while (sc.hasNextLine()) {
                parseLine(sc.nextLine());
            }
            sc.close();
        } catch(IOException ex){
            System.out.println("IO exception caught!");
        } catch(ParseFileException ex) {
            System.out.println("Exception while reading contents of state file");
        }
    }
    public void parseLine(String line) throws ParseFileException {
        if(line.equals("")) {
            return;
        }
        String[] splited = line.split("//");
        if(splited[0].equals("T")) {
            ToDo curr_task = new ToDo(splited[1], Boolean.parseBoolean(splited[2]));
            tasks.add(curr_task);
        } else if(splited[0].equals("D")) {
            Deadline curr_task = new Deadline(splited[1], Boolean.parseBoolean(splited[2]), splited[3]);
            tasks.add(curr_task);
        } else if(splited[0].equals("E")) {
            Event curr_task = new Event(splited[1], Boolean.parseBoolean(splited[2]), splited[3]);
            tasks.add(curr_task);
        } else {
            throw new ParseFileException("Exception while reading contents of state file!");
        }
    }
    public void introduction() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void list() {
        if(this.tasks.size() == 0) {
            System.out.println("No tasks!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= this.tasks.size(); i++) {
            Task curr_task = this.tasks.get(i-1);
            System.out.println(i + "." + curr_task);
        }
    }
    public void add(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        try {
            updateState();
        } catch (IOException ex) {
            System.out.println("IO exception caught!");
        } catch(UpdateStateException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void done(int task_num) {
        Task curr_task = this.tasks.get(task_num - 1);
        curr_task.setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + curr_task);
        try {
            updateState();
        } catch (IOException ex) {
            System.out.println("IO exception caught!");
        } catch(UpdateStateException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void delete(int task_num) {
        if(task_num > this.tasks.size()) {
            System.out.println("No task at that number! (Deletion unsuccessful)");
            return;
        }
        Task curr_task = this.tasks.get(task_num - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + curr_task);
        this.tasks.remove(task_num - 1);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        try {
            updateState();
        } catch (IOException ex) {
            System.out.println("IO exception caught!");
        } catch(UpdateStateException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void updateState() throws IOException, UpdateStateException {
        FileWriter fw = new FileWriter("data/state.txt");
        StringBuilder textToAddSB = new StringBuilder();
        for(Task curr_task:tasks) {
            if(curr_task instanceof ToDo) {
                textToAddSB.append(fileUpdateToDo((ToDo) curr_task));
                textToAddSB.append(System.lineSeparator());
            } else if(curr_task instanceof Deadline) {
                textToAddSB.append(fileUpdateDeadline((Deadline) curr_task));
                textToAddSB.append(System.lineSeparator());
            } else if(curr_task instanceof Event) {
                textToAddSB.append(fileUpdateEvent((Event) curr_task));
                textToAddSB.append(System.lineSeparator());
            } else {
                throw new UpdateStateException("Exception while updating state!");
            }
        }
        fw.write(textToAddSB.toString());
        fw.close();
    }
    public String fileUpdateToDo(ToDo todo) {
        StringBuilder SB =  new StringBuilder();
        SB.append("T//");
        SB.append(todo.getName());
        SB.append("//");
        SB.append(todo.isDone);
        return SB.toString();
    }
    public String fileUpdateDeadline(Deadline deadline) {
        StringBuilder SB =  new StringBuilder();
        SB.append("D//");
        SB.append(deadline.getName());
        SB.append("//");
        SB.append(deadline.isDone);
        SB.append("//");
        SB.append(deadline.getStringBy());
        return SB.toString();
    }
    public String fileUpdateEvent(Event event) {
        StringBuilder SB =  new StringBuilder();
        SB.append("E//");
        SB.append(event.getName());
        SB.append("//");
        SB.append(event.isDone);
        SB.append("//");
        SB.append(event.getStringAt());
        return SB.toString();
    }
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke();
        if (duke.state.createNewFile()) {
            System.out.println("No file detected, state file created!");
        } else {
            System.out.println("State file detected, loading state!");
            duke.writeFileContents("data/state.txt");
        }
        Scanner sc = new Scanner(System.in);
        duke.introduction();
        try {
            while(sc.hasNextLine()) {
                String command = sc.nextLine();
                if (command.equals("bye")) {
                    duke.exit();
                    return;
                } else if (command.equals("list")) {
                    duke.list();
                } else if (command.startsWith("done ")) {
                    String[] splited = command.split(" ");
                    if (splited[1].matches("^[0-9]*[1-9][0-9]*$") && splited.length == 2) {
                        duke.done(Integer.parseInt(splited[1]));
                    } else {
                        throw new DoneParameterException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else if (command.startsWith("todo")) {
                    if(command.equals("todo") || command.equals("todo ")) {
                        throw new EmptyToDoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    ToDo curr_task = new ToDo(command.replaceFirst("todo ", ""));
                    duke.add(curr_task);
                } else if (command.startsWith("deadline ")) {
                    String remove_command = command.replaceFirst("deadline ", "");
                    String[] splited = remove_command.split(" /by ");
                    Deadline curr_task = new Deadline(splited[0], splited[1]);
                    duke.add(curr_task);
                } else if (command.startsWith("event ")) {
                    String remove_command = command.replaceFirst("event ", "");
                    String[] splited = remove_command.split(" /at ");
                    Event curr_task = new Event(splited[0], splited[1]);
                    duke.add(curr_task);
                } else if (command.startsWith("delete ")) {
                    String[] splited = command.split(" ");
                    if (splited[1].matches("^[0-9]*[1-9][0-9]*$") && splited.length == 2) {
                        duke.delete(Integer.parseInt(splited[1]));
                    } else {
                        throw new DeleteParameterException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch(InvalidInputException | EmptyToDoDescriptionException | DoneParameterException | DeleteParameterException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
