import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;

public class Duke {
    static ArrayList<Task> listOfInputs = new ArrayList<>(100);

    private static void todoCheck(String[] tasks) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void deadlineCheck(String[] tasks, String userInput) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!userInput.contains("/by")) {
            throw new DukeException("OOPS!!! Deadline must include /by (date to complete task).");
        } else if (userInput.substring(userInput.indexOf("/by") + 3).equals("")
                || userInput.substring(userInput.indexOf("/by") + 4).equals("")) {
            throw new DukeException("OOPS!!! Please include the date to complete task after /by command.");
        }
    }

    private static void eventCheck(String[] tasks, String userInput) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        } else if (!userInput.contains("/at")) {
            throw new DukeException("OOPS!!! Event must include /at (time of event).");
        } else if (userInput.substring(userInput.indexOf("/at") + 3).equals("")
                || userInput.substring(userInput.indexOf("/at") + 4).equals("")) {
            throw new DukeException("OOPS!!! Please include the time of event after /at.");
        }
    }

    // For list tasks
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
        s.close();
    }

    // For done tasks
    private static void replaceLine(String lineToReplace, String newLine, String filePath)
            throws FileNotFoundException {
        int index = 0;
        try {
            ArrayList<String> fileContent = new ArrayList<>(
                    Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(lineToReplace)) {
                    fileContent.set(i, newLine);
                    break;
                }
            }
            Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // just need to append new tasks into file
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.write("\n");
        fw.close();
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                try {
                    System.out.println("Here are the tasks in your list:");
                    // int counter = 0;
                    // for (Task item : listOfInputs) {
                    // counter++;
                    // System.out.println(counter + "." + item.toString());
                    // }
                    printFileContents("data/duke.txt");
                } catch (FileNotFoundException e) {
                    System.err.println("Something went wrong:" + e.getMessage());
                }

            } else {
                String[] task = userInput.split(" ");
                String instruction = task[0];
                if (instruction.equals("done")) {
                    int taskNumber = Integer.parseInt(task[1]);
                    listOfInputs.get(taskNumber - 1).markedAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(listOfInputs.get(taskNumber - 1));
                } else if (instruction.equals("delete")) {
                    int taskNumber = Integer.parseInt(task[1]);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(listOfInputs.remove(taskNumber - 1));
                    System.out.println("Now you have " + listOfInputs.size() + " tasks in the list.");
                } else {
                    try {
                        switch (instruction) {
                        case "todo": {
                            try {
                                todoCheck(task);
                                Task todo = new Todo(userInput.substring(5));
                                listOfInputs.add(todo);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(todo);
                                appendToFile("data/duke.txt", todo.toString());
                                System.out.println("Now you have " + listOfInputs.size() + " tasks in the list.");
                            } catch (DukeException ex) {
                                System.out.println(ex.getMessage());
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                break;
                            }
                        }
                        case "deadline": {
                            try {
                                deadlineCheck(task, userInput);
                                Task deadline = new Deadline(userInput.substring(9, userInput.indexOf("/by")),
                                        userInput.substring(userInput.indexOf("/by") + 4));
                                listOfInputs.add(deadline);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(deadline);
                                appendToFile("data/duke.txt", deadline.toString());
                                System.out.println("Now you have " + listOfInputs.size() + " tasks in the list.");
                            } catch (DukeException ex) {
                                System.out.println(ex.getMessage());
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                break;
                            }
                        }
                        case "event": {
                            try {
                                eventCheck(task, userInput);
                                Task event = new Event(userInput.substring(6, userInput.indexOf("/at")),
                                        userInput.substring(userInput.indexOf("/at") + 4));
                                listOfInputs.add(event);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(event);
                                appendToFile("data/duke.txt", event.toString());
                                System.out.println("Now you have " + listOfInputs.size() + " tasks in the list.");
                            } catch (DukeException ex) {
                                System.out.println(ex.getMessage());
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                break;
                            }
                        }
                        default: {
                            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        }
                    } catch (DukeException ex) {
                        System.out.println(ex.getMessage());
                    }
                    // try {
                    // for (int i = 0; i < listOfInputs.size(); i++) {
                    // writeToFile("duke.txt", listOfInputs.get(i).toString());
                    // }
                    // } catch (IOException e) {
                    // System.out.println("Something went wrong:" + e.getMessage());
                    // }
                }
            }
        }

    }
}
