package duke.storage;

import duke.DukeException;
import duke.parser.Task;
import duke.ui.UiText;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;




public class Storage {
    private static File file;
    private UiText ui = new UiText();

    public Storage(String filePath) {
        this.file = new File(filePath);
        System.out.println((file.exists()));
        System.out.println(file.canRead());
        System.out.println(file.getPath());
    }

    public void printFileContents() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            ui.printlnMsg(sc.nextLine());
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] input = sCurrentLine.split(" \\| ");
                arr.add(new Task(input));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

//    public static void writeToFile(String filePath, String text) throws IOException {
//        FileWriter fw = new FileWriter(filePath);
//        fw.write(text);
//        fw.close();
//    }

    public void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(text);
        fw.close();
    }

    public void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (Task task : tasks) {
            String input = "";

            switch (task.getTaskType()) {
                case TODO:
                    input = String.format("T | %d | %s\n",
                            task.getStatusBit(),
                            task.getDescription());
                    break;

                case DEADLINE:
                    input = String.format("D | %d | %s | %s\n",
                            task.getStatusBit(),
                            task.getDescription(),
                            task.getInformation());
                    break;
                case EVENT:
                    input = String.format("E | %d | %s | %s\n",
                            task.getStatusBit(),
                            task.getDescription(),
                            task.getInformation());


            }
            writer.write(input);
        }
        writer.close();
    }
}
