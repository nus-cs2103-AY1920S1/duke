import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Duke {

    public static void runDuke(String filePath) throws IOException {
        String line = "    ____________________________________________________________";
        String currentCommand = "";
        ArrayList<Task> addedItems = new ArrayList<>();

        try {
            getTasksFromFile(addedItems, filePath);
        } catch (InvalidTaskArgumentDukeException e) {
            System.out.println(line);
            System.out.println("     " + e.getMessage());
            System.out.println(line);
        }


        System.out.println(line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        while (!currentCommand.equals("bye")) {
            currentCommand = scanner.nextLine();

            try {
                if (!currentCommand.equals("bye")) {

                    if (currentCommand.equals("list")) { // Showing list of tasks
                        System.out.println(line);
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < addedItems.size(); i++) {
                            System.out.println("     " + (i + 1) + "." + addedItems.get(i));
                        }
                        System.out.println(line);
                    } else if (currentCommand.length() >= 6 && currentCommand.substring(0, 4).equals("done")) { // Marking a task as done
                        int taskNumber = Integer.parseInt(currentCommand.substring(5)) - 1;

                        if (taskNumber >= 0 && taskNumber < addedItems.size()) {
                            addedItems.get(taskNumber).markAsDone();
                            System.out.println(line);
                            System.out.println("     Nice! I've marked this task as done:");
                            System.out.println("       " + addedItems.get(taskNumber));
                            System.out.println(line);
                        } else {
                            throw new InvalidCommandDukeException("☹ OOPS!!! There is no task labelled that number.");
                        }

                    } else if (currentCommand.length() >= 8 && currentCommand.substring(0, 6).equals("delete")) { // Deleting a task in the list
                        int taskNumber = Integer.parseInt(currentCommand.substring(7)) - 1;

                        if (taskNumber >= 0 && taskNumber < addedItems.size()) {
                            Task removedTask = addedItems.get(taskNumber);
                            addedItems.remove(taskNumber);
                            System.out.println(line);
                            System.out.println("     Noted. I've removed this task:");
                            System.out.println("       " + removedTask);
                            System.out.println("     Now you have " + addedItems.size() + (addedItems.size() > 1 ? " tasks in the list." : " task in the list."));
                            System.out.println(line);
                        } else {
                            throw new InvalidCommandDukeException("☹ OOPS!!! There is no task labelled that number.");
                        }


                    } else if (!currentCommand.equals("")){ // Adding new task to the list
                        String[] tempArray = currentCommand.split(" ");
                        Task currentTask = null;
                        String description = "";

                        if (tempArray[0].equals("todo")) {
                            description = currentCommand.substring(5);

                            if (!description.equals("")) {
                                currentTask = new ToDo(description);
                                addedItems.add(currentTask);
                            } else {
                                throw new InvalidTaskDescriptionDukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }

                        } else if (tempArray[0].equals("deadline")) {
                            String by = "";
                            boolean descriptionRecorded = false;

                            for (int i = 1; i < tempArray.length; i++) {
                                if (!tempArray[i].equals("/by") && !descriptionRecorded) {
                                    description += tempArray[i];
                                    if (i + 1 < tempArray.length && !tempArray[i + 1].equals("/by")) {
                                        description += " ";
                                    }
                                } else if (tempArray[i].equals("/by") && !descriptionRecorded){
                                    descriptionRecorded = true;
                                } else if (!tempArray[i].equals("/by") && descriptionRecorded){
                                    by += tempArray[i];
                                    if (i != tempArray.length - 1) {
                                        by += " ";
                                    }
                                }
                            }

                            if (!description.equals("") && !by.equals("")) {
                                currentTask = new Deadline(description, by);
                                addedItems.add(currentTask);
                            } else {
                                throw new InvalidTaskDescriptionDukeException("☹ OOPS!!! The description/timing of a deadline cannot be empty.");
                            }

                        } else if (tempArray[0].equals("event")) {
                            String at = "";
                            boolean descriptionRecorded = false;

                            for (int i = 1; i < tempArray.length; i++) {
                                if (!tempArray[i].equals("/at") && !descriptionRecorded) {
                                    description += tempArray[i];
                                    if (i + 1 < tempArray.length && !tempArray[i + 1].equals("/at")) {
                                        description += " ";
                                    }
                                } else if (tempArray[i].equals("/at") && !descriptionRecorded){
                                    descriptionRecorded = true;
                                } else if (!tempArray[i].equals("/at") && descriptionRecorded){
                                    at += tempArray[i];
                                    if (i != tempArray.length - 1) {
                                        at += " ";
                                    }
                                }
                            }

                            if (!description.equals("") && !at.equals("")) {
                                currentTask = new Event(description, at);
                                addedItems.add(currentTask);
                            } else {
                                throw new InvalidTaskDescriptionDukeException("☹ OOPS!!! The description/timing of an event cannot be empty.");
                            }

                        }

                        if (currentTask != null) {
                            System.out.println(line);
                            System.out.println("     Got it. I've added this task: ");
                            System.out.println("       " + currentTask);
                            System.out.println("     Now you have " + addedItems.size() + (addedItems.size() > 1 ? " tasks in the list." : " task in the list."));
                            System.out.println(line);
                        } else {
                            throw new InvalidCommandDukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }

                    } else {
                        throw new InvalidCommandDukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                }
            } catch (InvalidCommandDukeException e) {
                System.out.println(line);
                System.out.println("     " + e.getMessage());
                System.out.println(line);
            } catch (InvalidTaskDescriptionDukeException e) {
                System.out.println(line);
                System.out.println("     " + e.getMessage());
                System.out.println(line);
            } catch (InvalidTaskArgumentDukeException e) {
                System.out.println(line);
                System.out.println("     " + e.getMessage());
                System.out.println(line);
            }
        }

        loadTasksToFile(addedItems, filePath);

        System.out.println(line);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void getTasksFromFile(ArrayList<Task> taskList, String filePath) throws FileNotFoundException, InvalidTaskArgumentDukeException {
        File taskFile = new File(filePath);
        Scanner scanner = new Scanner(taskFile);
        while (scanner.hasNext()) {
            String textLine = scanner.nextLine();
            taskList.add(stringToTask(textLine));
        }
    }

    public static void loadTasksToFile(ArrayList<Task> taskList, String filePath) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskToString(taskList.get(i)));

                if (i != taskList.size() - 1) {
                    fileWriter.write(System.lineSeparator());
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }

    public static Task stringToTask(String text) throws InvalidTaskArgumentDukeException {
            String[] textSplit = text.split("\\|");
            Task resultTask;

            if (textSplit[0].equals("T")) {
                resultTask = new ToDo(textSplit[2]);
            } else if (textSplit[0].equals("D")) {
                resultTask = new Deadline(textSplit[2], textSplit[3]);
            } else {
                resultTask = new Event(textSplit[2], textSplit[3]);
            }

            if (textSplit[1].equals("1")) {
                resultTask.markAsDone();
            }

            return resultTask;
    }

    public static String taskToString(Task task) {
        String taskType = "";
        String description = task.getDescription();
        String isDone = "0";

        if (task.isDone()) {
            isDone = "1";
        }

        if (task instanceof ToDo) {
            taskType = "T";
            return taskType + "|" + isDone + "|" + description;
        } else { // event or deadline
            String time = "";

            if (task instanceof Event) {
                taskType = "E";
                time = ((Event) task).getAt();
            } else {
                taskType = "D";
                time = ((Deadline) task).getBy();
            }

            return taskType + "|" + isDone + "|" + description + "|" + time;

        }
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        runDuke("data/tasks.txt");
    }
}
