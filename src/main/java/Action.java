import java.util.ArrayList;
import java.io.*;

public class Action {
    public static void welcomeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    public static void byeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }

    public static void doTask(String command, ArrayList<Task> list) throws IncompleteCommandException, InvalidCommandException {
        String[] commandStrArray = command.split(" ", 2);
        try {
            if (commandStrArray[0].equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("     " + i + "." + list.get(i - 1));
                }
                System.out.println("    ____________________________________________________________\n");
            } else {
                if (commandStrArray[0].equals("done")) {
                    list.get(Integer.parseInt(commandStrArray[1]) - 1).markAsDone();
                } else if (commandStrArray[0].equals("delete")) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Noted. I've removed this task: ");
                    System.out.println("       " + list.get(Integer.parseInt(commandStrArray[1]) - 1));
                    list.remove(Integer.parseInt(commandStrArray[1]) - 1);
                    System.out.println("     Now you have " + list.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________\n");
                } else {
                    if (commandStrArray[0].equals("todo")) {
                        if (commandStrArray.length == 1) {
                            throw new IncompleteCommandException("Incomplete Command");
                        }
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task: ");
                        Task task = new ToDo(commandStrArray[1]);
                        System.out.println("       " + task);
                        list.add(task);
                    } else if (commandStrArray[0].equals("deadline")) {
                        if (commandStrArray.length == 1) {
                            throw new IncompleteCommandException("Incomplete Command");
                        }
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task: ");
                        String[] descriptionStrArray = commandStrArray[1].split("/by");
                        Task task = new Deadline(descriptionStrArray[0], descriptionStrArray[1]);
                        System.out.println("       " + task);
                        list.add(task);
                    } else if (commandStrArray[0].equals("event")) {
                        if (commandStrArray.length == 1) {
                            throw new IncompleteCommandException("Incomplete Command");
                        }
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task: ");
                        String[] descriptionStrArray = commandStrArray[1].split("/at");
                        Task task = new Event(descriptionStrArray[0], descriptionStrArray[1]);
                        System.out.println("       " + task);
                        list.add(task);
                    } else {
                        throw new InvalidCommandException("Invalid Command");
                    }
                    System.out.println("     Now you have " + list.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________\n");
                }
            }
        } catch (IncompleteCommandException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     ☹ OOPS!!! The description of a " + commandStrArray[0] + " cannot be empty.");
            System.out.println("    ____________________________________________________________\n");
        } catch (InvalidCommandException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("    ____________________________________________________________\n");
        }
        saveList(list);
    }

    public static void saveList(ArrayList<Task> list) {
        File file = new File("/Users/sihao/Desktop/NUS AY19:20 Sem 1/CS2103/Duke/Data/Duke.txt");
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task t : list) {
                bw.write(t.taskSavedTextFormat() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTaskList(ArrayList<Task> list) {
        File file = new File("/Users/sihao/Desktop/NUS AY19:20 Sem 1/CS2103/Duke/Data/Duke.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String taskString;
            while ((taskString = br.readLine()) != null) {
                String[] taskStringArray = taskString.split("\\|");
                if (taskStringArray[0].trim().equals("T")) {
                    Task task = new ToDo(taskStringArray[2].trim());
                    if (taskStringArray[1].trim().equals("1")) {
                        task.isDone = true;
                    }
                    list.add(task);
                } else if (taskStringArray[0].trim().equals("D")) {
                    Task task = new Deadline(taskStringArray[2].trim(), taskStringArray[3].trim());
                    if (taskStringArray[1].trim().equals("1")) {
                        task.isDone = true;
                    }
                    list.add(task);
                } else {
                    Task task = new Event(taskStringArray[2].trim(), taskStringArray[3].trim());
                    if (taskStringArray[1].trim().equals("1")) {
                        task.isDone = true;
                    }
                    list.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
