import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DukeManager {
    private TaskList list;
    private FileWriter fileWriter;

    public DukeManager() {
        list = new TaskList();
    }

    public void startManager() {
        try {
            this.fillListStart();
        } catch (FileNotFoundException e) {
            System.out.println("No previous records found, starting a fresh list.");
        }
        this.evaluateCommand();
    }

    private void fillListStart() throws FileNotFoundException {
        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String text = s.nextLine();
            String[] itemArr = text.split(" [|] ");
            switch (itemArr[0]) {
            case "T":
                list.addPreviousTask(new ToDos(itemArr[2], itemArr[1]));
                break;

            case "D":
                list.addPreviousTask(new Deadlines(itemArr[2], itemArr[1], itemArr[3]));
                break;

            case "E":
                list.addPreviousTask(new Event(itemArr[2], itemArr[1], itemArr[3]));
                break;
            }
        }
        s.close();
    }

    private void fillListEnd() {
        try {
            fileWriter = new FileWriter("data/duke.txt", false);
            for (Task t : list.getTaskList()) {
                String task = t.getFormattedString() + "\n";
                fileWriter.write(task);
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void evaluateCommand() {
        Scanner sc = new Scanner(System.in);
        String command = "";
        boolean hasBye = false;

        while (sc.hasNextLine() && !hasBye) {
            try {
                command = sc.next();
                String errorMsg = String.format("The description of a %s cannot be empty.", command);

                switch (command) {
                case "list":
                    list.printTasks();
                    break;

                case "done":
                    int sizeOfList = list.getNumOfTasks();
                    String number = sc.nextLine().trim();
                    if (number.matches("^\\d+")) {
                        int taskNum = Integer.parseInt(number);
                        if (taskNum > sizeOfList || taskNum < 1) {
                            throw new InvalidDescriptionException(errorMsg);
                        } else {
                            list.tickTask(taskNum);
                        }
                    } else {
                        throw new InvalidDescriptionException(errorMsg);
                    }
                    break;

                case "delete":
                    int taskNo = Integer.parseInt(sc.nextLine().trim());
                    int listSize = list.getNumOfTasks();
                    if (taskNo > listSize || taskNo < 1) {
                        throw new InvalidDescriptionException(errorMsg);
                    } else {
                        list.removeTask(taskNo);
                    }
                    break;

                case "todo":
                    String input1 = sc.nextLine().trim();
                    if (input1.equals("")) {
                        throw new InvalidDescriptionException(errorMsg);
                    } else {
                        list.addTask(new ToDos(input1));
                    }
                    break;

                case "deadline":
                    String[] input2 = sc.nextLine().trim().split("/by");
                    if (input2.length != 2) {
                        throw new InvalidDescriptionException(errorMsg);
                    } else {
                        list.addTask(new Deadlines(input2[0], input2[1]));
                    }
                    break;

                case "event":
                    String[] input3 = sc.nextLine().trim().split("/at");
                    if (input3.length != 2) {
                        throw new InvalidDescriptionException(errorMsg);
                    } else {
                        list.addTask(new Event(input3[0], input3[1]));
                    }
                    break;

                case "bye":
                    hasBye = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                default:
                    throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!hasBye && sc.hasNext()) {
                continue;
            } else {
                break;
            }

        }
        fillListEnd();
    }
}
