import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private boolean running;
    private ArrayList<Task> tasks;

    public Duke() {
        scanner = new Scanner(System.in);
        running = true;
        tasks = new ArrayList<>();
    }

    public void start() {
        String input;
        say("Hello! I'm Duke\nWhat can I do for you?");
        while (running) {
            input = scanner.nextLine();
            String[] inputSequence = input.split(" ");
            switch(inputSequence[0]) {
                case "bye":
                    exit();
                    break;
                case "list":
                    listTasks();
                    break;
                case "done":
                    markDone(Integer.parseInt(inputSequence[1]));
                    break;
                default:
                    addTask(input);
                    break;
            }
        }
    }

    private void markDone(int index) {
        Task doneTask = tasks.get(index - 1);
        doneTask.markDone();
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n  ");
        sb.append(doneTask);
        say(sb.toString());
    }

    private void addTask(String task) {
        Task newTask = new Task(task);
        tasks.add(newTask);
        say("added: " + task);
    }

    private void listTasks() {
        int size = tasks.size();
        if(size == 0) {
            say("Empty list: no tasks added");
        } else {
            int index = 1;
            StringBuilder listOfTasks = new StringBuilder();
            for(Task task : tasks) {
                if(index == 1) {
                    listOfTasks.append(index);
                    listOfTasks.append(".");
                    listOfTasks.append(task);
                    index++;
                } else {
                    listOfTasks.append("\n");
                    listOfTasks.append(index);
                    listOfTasks.append(".");
                    listOfTasks.append(task);
                    index++;
                }
            }
            say(listOfTasks.toString());
        }
    }

    private void exit() {
        this.running = false;
        say("Bye. Hope to see you again soon!");
    }

    private void say(String sequence) {
        String indent = sequence.replaceAll("(?m)^", "\t");
        System.out.printf(
                "       ____________________________________________________________\n     %s\n       ____________________________________________________________\n",
                indent);
    }
}