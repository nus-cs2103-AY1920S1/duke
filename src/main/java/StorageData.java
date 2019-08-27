import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StorageData {
    private File data;

    public StorageData(File newData) {
        try {
            boolean exists = newData.exists();
            if(exists) {
                this.data = newData;
            } else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            try {
                new File("data/tasks.txt").createNewFile();
                this.data = newData;
                System.out.println("New save data created");
            } catch (IOException ex) {
                System.out.println(e.getMessage());
            }
        }
    }

    public File getData() {
        return this.data;
    }

    public void addTodoData(String toDo) {
        try {
            FileWriter writer = new FileWriter(data, true);
            writer.write("T - not done - " + toDo + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDeadlineData(String description, String byWhen) {
        try {
            FileWriter writer = new FileWriter(data, true);
            writer.write("D - not done - " + description + " - " + byWhen + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEventData(String description, String duringWhen) {
        try {
            FileWriter writer = new FileWriter(data, true);
            writer.write("E - not done - " + description + " - " + duringWhen + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void markTaskDoneInData(int taskNumber) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(data));
            String line = reader.readLine();
            String old = "";
            int checker = 1;
            while (line != null) {
                if (checker != taskNumber) {
                    old += line + System.lineSeparator();
                    line = reader.readLine();
                    checker += 1;
                } else {

                        old += line.replace("not done", "done") + System.lineSeparator();
                        line = reader.readLine();
                        checker += 1;

                }
            }
            FileWriter writer = new FileWriter(data);
            writer.write(old);
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTaskInData(int taskNumber) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(data));
            boolean taskFound = false;
            String line = reader.readLine();
            String old = "";
            int checker = 1;
            while(line != null) {
                if(checker != taskNumber) {
                    old += line + System.lineSeparator();
                    line = reader.readLine();
                    checker += 1;
                } else {
                    old += "";
                    line = reader.readLine();
                    checker += 1;
                    taskFound = true;
                }
            }
            if(!taskFound) {
                throw new IndexOutOfBoundsException();
            }
            FileWriter writer = new FileWriter(data);
            writer.write(old);
            writer.close();
            reader.close();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Input is an invalid task number.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskArrayList = new ArrayList<>(100);

        Scanner fileInput = new Scanner(this.data);
        while(fileInput.hasNextLine()) {
            String task = fileInput.next();
            switch(task) {
                case "T":
                    String[] contents = fileInput.nextLine().trim().split("-");
                    Task current = new Todo(contents[2].trim());
                    if(contents[1].trim().equals("done")) {
                        current.markAsDone();
                    }
                    taskArrayList.add(current);
                    break;

                case "D":
                    contents = fileInput.nextLine().trim().split("-");
                    current = new Deadline(contents[2].trim(), contents[3].trim());
                    if(contents[1].trim().equals("done")) {
                        current.markAsDone();
                    }
                    taskArrayList.add(current);
                    break;

                case "E":
                    contents = fileInput.nextLine().trim().split("-");
                    current = new Event(contents[2].trim(), contents[3].trim());
                    if(contents[1].trim().equals("done")) {
                        current.markAsDone();
                    }
                    taskArrayList.add(current);
                    break;

                default:
                    break;
            }
        }
        return taskArrayList;
    }
}
