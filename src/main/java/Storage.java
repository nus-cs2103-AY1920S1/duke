import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;


public class Storage {

    private String filePath;

    private File textFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.textFile = new File(filePath);

    }

    public ArrayList<Task> load() throws DukeException {

        try {
            Path path = this.textFile.toPath();

            List<String> lines = Files.readAllLines(path);
            ArrayList<Task> tasks = new ArrayList<>();


            for (String line : lines) {
                tasks.add(this.lineToTask(line));


            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException();
        }
    }




        public Task lineToTask (String line){
            String[] lineArray = line.split("\\|");

            switch (lineArray[0].trim()) {
                case "T":
                    //return a new toDo task with "whether it is done" and description
                    return new Todo(lineArray[1], lineArray[2].trim());

                case "E":
                    //return a new toDo task with "whether it is done" and description and event time
                    return new Event(lineArray[1], lineArray[2], lineArray[3]);

                case "D":
                    //return a new deadline task with "whether it is done" and description and deadline
                  return new Deadline(lineArray[1], lineArray[2], lineArray[3]);

            default:
                return null;
            }
        }


        public void writeToFile(String textToAdd) throws IOException {
            //Create a file writer object to represent the hard disk
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAdd);
            fw.close();
        }


        public void appendToFile(String type, Date date , String desc) throws IOException {

                if(date == null)
                    writeToFile(type + " | 1 | " + desc + "\n");
                else
                    writeToFile(type + " | 1 | " + desc + " | " + date +"\n");


        }

    public void updateText(int taskNum) throws IOException {
        int lineNumber = taskNum -1;
        Path path = Paths.get(filePath);
        //read all the line in the files
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String oldText = lines.get(lineNumber);
        lines.set(lineNumber, oldText.substring(0, 3) + " 0 " + oldText.substring(6, oldText.length()));
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public void deleteText(int taskNum) throws IOException {
        int lineNumber = taskNum - 1;
        Path path = Paths.get(filePath);
        //read all the line in the files
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.remove(lineNumber);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

  /*  public void writeToFile(String textToAdd) throws IOException {
        //Create a file writer object to represent the hard disk
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }


    public void appendToFile(String type, String desc, String date) {
        try {
            if(date == null)
                writeToFile(type + " | 1 | " + desc + "\n");
            else
                writeToFile(type + " | 1 | " + desc + " | " + date +"\n");

        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }

    }

    //create a method to update a specific line in file ( when the task is marked as done)
    public static void updateText(int lineNumber) throws IOException {
        Path path = Paths.get(filePath);
        //read all the line in the files
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String oldText = lines.get(lineNumber);
        lines.set(lineNumber, oldText.substring(0, 3) + " 0 " + oldText.substring(6, oldText.length()));
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static void deleteText(int lineNumber) throws IOException {
        Path path = Paths.get(filePath);
        //read all the line in the files
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.remove(lineNumber);
        Files.write(path, lines, StandardCharsets.UTF_8);
    } */

    }