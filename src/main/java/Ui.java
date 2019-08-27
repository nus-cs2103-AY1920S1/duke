import java.io.IOException;

public class Ui {

    public void initiate() {
        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
        System.out.println("___________________________________");
    }

    public void executeInstructions(String inputInstruction, String inputCommand, Storage storage, TaskList tasks) throws IOException {

        if (inputCommand.equals("list")) {
            System.out.println("__________________________________");
            System.out.println("Here are the tasks in your list:");
            TaskList.listTask();
            System.out.println("___________________________________");
        } else if (inputCommand.equals("done")) {
            String[] taskNumString = inputInstruction.split(" ");
            int taskNum = Integer.parseInt(taskNumString[1]);
            Task currentTask = TaskList.getTask(taskNum - 1);
            currentTask.markAsDone();
            System.out.println("___________________________________");
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(currentTask);
            System.out.println("___________________________________");
            storage.updateTaskToFile(TaskList.getList());
        } else if (inputCommand.equals("todo")) {
            try {
                if (inputInstruction.length() == 4 || inputInstruction.length() == 5) {
                    throw new DukeException("todo");
                }
                String subInput = inputInstruction.substring(5);
                Task newTask = new Todo(subInput);
                tasks.addTask(newTask);
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:\n" + newTask);
                System.out.println("Now you have " + Task.total + " tasks in the list.");
                System.out.println("___________________________________");
                storage.writeToFile(newTask + "\n");
            } catch (Exception e) {
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
            }
        } else if (inputCommand.equals("deadline")) {
            try {
                if (!inputInstruction.contains("/by") || inputInstruction.length() == 8 || inputInstruction.length() == 9) {
                    throw new DukeException("deadline");
                }
                String subInput1 = inputInstruction.substring(9, inputInstruction.lastIndexOf("/by") - 1);
                String subInput2 = inputInstruction.substring(inputInstruction.lastIndexOf("/by") + 4);
                Task newTask = new Deadline(subInput1, subInput2);
                tasks.addTask(newTask);
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:\n" + newTask);
                System.out.println("Now you have " + Task.total + " tasks in the list.");
                System.out.println("___________________________________");
                storage.writeToFile(newTask + "\n");
            } catch (Exception e) {
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
            }
        } else if (inputCommand.equals("event")) {
            try {
                if (!inputInstruction.contains("/at") || inputInstruction.length() == 5 || inputInstruction.length() == 6) {
                    throw new DukeException("event");
                }
                String subInput1 = inputInstruction.substring(6, inputInstruction.lastIndexOf("/at"));
                String subInput2 = inputInstruction.substring(inputInstruction.lastIndexOf("/at") + 4);
                Task newTask = new Event(subInput1, subInput2);
                tasks.addTask(newTask);
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:\n" + newTask);
                System.out.println("Now you have " + Task.total + " tasks in the list.");
                System.out.println("___________________________________");
                storage.writeToFile(newTask + "\n");
            } catch (Exception e) {
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
            }
        } else if (inputCommand.equals("delete")) {
            try {
                if (inputInstruction.length() == 6 || inputInstruction.length() == 7) {
                    throw new DukeException("delete");
                }
                String[] words = inputInstruction.split(" ");
                int taskNum = Integer.parseInt(words[1]);
                if (taskNum > tasks.noOfTask()) {
                    throw new DukeException("index");
                }
                Task currentTask = TaskList.getTask(taskNum - 1);
                tasks.removeTask(taskNum - 1);
                System.out.println("___________________________________");
                System.out.println("Noted. I've removed this task:");
                System.out.println(currentTask);
                Task.total--;
                System.out.println("Now you have " + Task.total + " tasks in the list.");
                System.out.println("___________________________________");
                storage.updateTaskToFile(tasks.getList());
            } catch (Exception e) {
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
            }
        } else {
            try {
                throw new DukeException("invalid");
            } catch (Exception e) {
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
            }
        }
    }
}
