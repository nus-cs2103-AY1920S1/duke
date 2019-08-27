import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Files {
    private static File f = new File("../data/duke.txt");

    public static void printFileContents() throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
    }

    public static ArrayList<Task> convertToArrayList() {
        ArrayList<Task> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f)))
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

    public static void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(f, true);
        fw.write(text);
        fw.close();
    }

    public static void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(f);
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
