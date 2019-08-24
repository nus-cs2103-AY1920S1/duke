import java.util.*;

public class Duke {

    public static void runDuke() {
        String line = "    ____________________________________________________________";
        String currentCommand = "";
        ArrayList<Task> addedItems = new ArrayList<>();

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

                            if (!description.equals("")) {
                                currentTask = new Deadline(description, by);
                                addedItems.add(currentTask);
                            } else {
                                throw new InvalidTaskDescriptionDukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
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

                            if (!description.equals("")) {
                                currentTask = new Event(description, at);
                                addedItems.add(currentTask);
                            } else {
                                throw new InvalidTaskDescriptionDukeException("☹ OOPS!!! The description of an event cannot be empty.");
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

        System.out.println(line);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        runDuke();
    }
}
