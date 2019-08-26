import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileWriter fileWriter;
        ArrayList<Task> taskList = new ArrayList<>(100);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        try {
            File file = new File("CurrentTaskList.txt");
            file.createNewFile();
            List<String> savedList = Files.readAllLines(file.toPath());
            for (String line : savedList) {
                String[] lineElements = line.split(" \\| ");
                String lineType = lineElements[0];
                if (lineType.equals("T")) {
                    Task currentTask = new ToDoTask(lineElements[2]);
                    if (lineElements[1].equals("+")) {
                        currentTask.markAsDone();
                    }
                    taskList.add(currentTask);
                } else if (lineType.equals("D")) {
                    String[] taskTimeParsed = lineElements[3].split("[ /]");
                    Calendar taskTime = new GregorianCalendar(Integer.parseInt(taskTimeParsed[2]), Integer.parseInt(taskTimeParsed[1]) - 1,
                            Integer.parseInt(taskTimeParsed[0]), Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                            Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
                    Task currentTask = new DeadlineTask(lineElements[2], taskTime);
                    if (lineElements[1].equals("+")) {
                        currentTask.markAsDone();
                    }
                    taskList.add(currentTask);
                } else if (lineType.equals("E")) {
                    String[] taskTimeParsed = lineElements[3].split("[ /]");
                    Calendar taskTime = new GregorianCalendar(Integer.parseInt(taskTimeParsed[2]), Integer.parseInt(taskTimeParsed[1]) - 1,
                            Integer.parseInt(taskTimeParsed[0]), Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                            Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
                    Task currentTask = new EventTask(lineElements[2], taskTime);
                    if (lineElements[1].equals("+")) {
                        currentTask.markAsDone();
                    }
                    taskList.add(currentTask);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            try {
                String input = scanner.nextLine();
                String instruction = input.split(" ", 2)[0];
                if (instruction.equals("bye")) { // First, check if 'bye' is called
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (instruction.equals("list")) { // Then, check if 'list' is called
                    if (taskList.size() == 0) { // if 'list' is called with no tasks currently stored
                        throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + ". " + taskList.get(i));
                    }
                } else if (instruction.equals("done")) { // Then, check if task is marked done
                    if (taskList.size() == 0) { // if 'list' is empty, 'done' cannot be called
                        throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
                    }
                    try {
                        int index = Integer.parseInt(input.split(" ", 2)[1]);
                        taskList.get(index - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n  " + taskList.get(index - 1));
                    } catch (NumberFormatException e) { // if not a number is entered after 'done'
                        throw new NotAnIntegerTaskListException("OOPS!!! Please enter an integer after 'done'!");
                    } catch (IndexOutOfBoundsException e) { // if an invalid number is entered after 'done'
                        throw new InvalidIntegerTaskListException("OOPS!!! Please enter a valid task number!");
                    }
                } else if (instruction.equals("delete")) { // Then, check if task is marked delete
                    if (taskList.size() == 0) { // if 'list' is empty, 'delete' cannot be called
                        throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
                    }
                    try {
                        int index = Integer.parseInt(input.split(" ", 2)[1]);
                        Task currentTask = taskList.remove(index - 1);
                        System.out.println("Noted. I've removed this task:\n  " + currentTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } catch (NumberFormatException e) { // if not a number is entered after 'delete'
                        throw new NotAnIntegerTaskListException("OOPS!!! Please enter an integer after 'delete'!");
                    } catch (IndexOutOfBoundsException e) { // if an invalid number is entered after 'delete'
                        throw new InvalidIntegerTaskListException("OOPS!!! Please enter a valid task number!");
                    }
                } else if (instruction.equals("todo") || instruction.equals("deadline") || instruction.equals("event")) {
                    try {
                        if (instruction.equals("todo")) {
                            String taskContent = input.split(" ", 2)[1]; // Remaining content of task
                            if (taskContent.matches("\\s*")) { // if the task's description is only whitespace
                                throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
                            }
                            Task currentTask = new ToDoTask((taskContent));
                            taskList.add(currentTask);
                            System.out.println("Got it. I've added this task:\n  " + currentTask);
                        } else {
                            String taskDescription = input.split("deadline|event", 2)[1];
                            if (!(taskDescription.contains(" /by ") || taskDescription.contains(" /at "))) { // if '/by' and '/at' are absent
                                throw new IncorrectTaskTimeFormatException("OOPS!!! No ' /by ' or ' /at ' detected! Please use the correct format!");
                            }
                            String taskContent = taskDescription.split("/by|/at", 2)[0].strip();
                            String taskTimeBeforeParse = taskDescription.split("/by|/at", 2)[1].strip(); // time must be parsed via '/by' or '/at'
                            String[] taskTimeParsed = taskTimeBeforeParse.split("[ /]");
                            Calendar taskTime = new GregorianCalendar(Integer.parseInt(taskTimeParsed[2]), Integer.parseInt(taskTimeParsed[1]) - 1,
                                    Integer.parseInt(taskTimeParsed[0]), Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                                    Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
                            if (taskContent.matches("\\s*")) { // if the task's description is only whitespace
                                throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
                            } /* else if (taskTime.matches("\\s*")) { // if the task's time listed is only whitespace
                                throw new EmptyTaskTimeException("OOPS!!! Please include a time for your task!");
                            }*/
                            if (instruction.equals("deadline")) {
                                Task currentTask = new DeadlineTask(taskContent, taskTime);
                                taskList.add(currentTask);
                                System.out.println("Got it. I've added this task:\n  " + currentTask);
                            } else {
                                Task currentTask = new EventTask(taskContent, taskTime);
                                taskList.add(currentTask);
                                System.out.println("Got it. I've added this task:\n  " + currentTask);
                            }
                        }
                    } catch (IndexOutOfBoundsException e) { // if the task description is empty
                        throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
                    }
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } else { // if an invalid instruction is entered
                    throw new InvalidInstructionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            fileWriter = new FileWriter("CurrentTaskList.txt");
            for (Task task : taskList) {
                fileWriter.write(task.formattedString());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
