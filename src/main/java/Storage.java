import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    protected File file;
    protected String input;
    protected String output;

    public Storage(String path) {
        try {
            this.file = new File(path);
            readInputFile(this.file);
            this.output = "";
        } catch (IOException e) {
            System.out.println("Input: Something serious happened here...");
        }
    }

    public String getInput() {
        return this.input;
    }

    public void readInputFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder("");
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        this.input = sb.toString();
    }

    public void save(ArrayList<Task> tasklist) {
        this.output = "";
        for (Task t : tasklist) {
            this.output += (t.saveString());
        }
        try {
            File outFile = new File("C:\\Users\\AngKa\\duke\\data\\duke.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
            out.write(output);
            out.close();
        } catch (IOException e) {
            System.out.println("Output: Something serious happened here...");
        }
    }

//    public static void main(String[] args) {
//        Storage storage = new Storage("C:\\Users\\AngKa\\duke\\src\\main\\java\\test.txt");
//        try {
//            storage.readInputFile();
//            storage.save(storage.input);
//        } catch (IOException e) {
//            System.out.println("broke");
//        }
//    }
}
