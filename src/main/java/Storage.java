import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private File file;
    private Scanner fileReader;
    private String savedPath;
    private FileWriter writer;

    public Storage(String savedPath) throws IOException {
        this.savedPath = savedPath;
        file = new File(savedPath);
    }

    public ArrayList<Task> loadFromFile() throws DukeException, FileNotFoundException {
        try {
            fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                String[] keywords = line.split(" ");
                boolean isDone = false;
                switch (keywords[1]) {
                    case "[+]":
                        isDone = true;
                    case "[-]":
                        if (keywords[0].equals("[T]")) {
                            keywords[0] = "";
                            keywords[1] = "";
                            String desc = String.join(" ", keywords).strip();
                            Todo temp = new Todo(desc);
                            if (isDone) {
                                temp.setDone();
                            }
                            taskList.add(temp);
                        } else if (keywords[0].equals("[D]")) {
                            keywords[0] = "";
                            String desc = "";
                            String time = "";
                            boolean flag = false;
                            for (int i = 2; i < keywords.length; i++) {
                                if (flag) {
                                    time = time + " " + keywords[i];
                                } else if (keywords[i].equals("(by:")) {
                                    flag = true;
                                } else {
                                    desc = desc + " " + keywords[i];
                                }
                            }
                            Deadline temp = new Deadline(desc.strip(), time.strip());
                            if (isDone) {
                                temp.setDone();
                            }
                            taskList.add(temp);
                        } else if (keywords[0].equals("[E]")) {
                            keywords[0] = "";
                            String desc = "";
                            String time = "";
                            boolean flag = false;
                            for (int i = 2; i < keywords.length; i++) {
                                if (flag) {
                                    time = time + " " + keywords[i];
                                } else if (keywords[i].equals("(at:")) {
                                    flag = true;
                                } else {
                                    desc = desc + " " + keywords[i];
                                }
                            }
                            Event temp = new Event(desc.strip(), time.strip());
                            if (isDone) {
                                temp.setDone();
                            }
                            taskList.add(temp);
                        } else {
                            throw new DukeException("OOPS!!! I cannot read your file! :(");
                        }
                }
            }
            fileReader.close();
            return taskList;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("YOoooUR fILe haS BeEN cORRupTEd.");
        }
    }

    public void writeToFile(ArrayList<Task> taskList) throws IOException {
        writer = new FileWriter(file);
        try {
            String temp = taskList.remove(0).toString();
            while (!taskList.isEmpty()) {
                temp = temp + "\n" + taskList.remove(0).toString();
            }
            writer.write(temp);
        } catch (IndexOutOfBoundsException ex) {

        }
        writer.close();
    }

}
